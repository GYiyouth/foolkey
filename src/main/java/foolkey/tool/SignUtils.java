package foolkey.tool;



import org.apache.http.util.TextUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by geyao on 2017/5/23.
 */
public class SignUtils {
    private String mAPPID = 1252706879 + "";
    private String mBucket = "foolkey";
    private String mFileID = null;
    private long mExpiredTime = 0;
    private long mCurrentTime = 0;
    private String mScrectKey = "AD5mKsOjtsiP8kCTaWOE1e6NSO9jASZW";
    private String mScrectID = "AKIDlU4rgPAJTXbaTDTDVuKuKh2GJDwbgPkw";


    /**
     public SignUtils(String mAPPID, String mBucket, String mFileID, long mExpiredTime){
     //其中 nFileId 的格式签名 带“/”
     this.mAPPID = mAPPID;
     this.mBucket = mBucket;
     this.mFileID = mFileID;
     this.mExpiredTime = mExpiredTime;
     }
     */
    public SignUtils( String mFileID, long mExpiredTime){
        //其中 nFileId 的格式签名 带“/”

        this.mFileID = mFileID;
        this.mExpiredTime = mExpiredTime;

    }

    public SignUtils(){}

    public String getSignForUser(){
        String sign = null;
        String signUrl = getSignUrl();
        sign = getSign(signUrl,this.mScrectKey);
        sign = sign.replaceAll("\n","");
        return  sign;
    }

    private String getSignUrl(){
        String signUrl = null;
        /**
         * 形如：1) a=[appid]&b=[bucket]&k=[SecretID]&e=[expiredTime]&t=[currentTime]&r=[rand]&f=；
         *       2) a=[appid]&b=[bucket]&k=[SecretID]&e=[expiredTime]&t=[currentTime]&r=[rand]&f=[fileid]；
         *       3) 其中检测签名时与各个字段的排序顺序无关；
         *
         * 签名类型：
         *        1）下载（不开启token防盗链）	不验证签名
         *        2） 上传	多次有效签名
         3）查询目录、文件	多次有效签名
         4）创建目录	多次有效签名
         5）下载（开启token防盗链）	多次有效签名
         6）删除目录、文件	单次有效签名
         7）更新目录、文件	单次有效签名
         */

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("a=" + mAPPID);
        stringBuilder.append("&b=" + mBucket);
        stringBuilder.append("&k=" + mScrectID);

        long random = Math.abs(new Random().nextLong());
        stringBuilder.append("&r=" + random);
        if(mFileID == null){
            mCurrentTime = System.currentTimeMillis()/1000;
            mExpiredTime = mExpiredTime*24*3600 + mCurrentTime;
            stringBuilder.append("&e=" + mExpiredTime);
            stringBuilder.append("&t=" + mCurrentTime);
            stringBuilder.append("&f=");
        }else{
            try {
                mCurrentTime = System.currentTimeMillis()/1000;
                mExpiredTime = 0;
                stringBuilder.append("&e=" + mExpiredTime);
                stringBuilder.append("&t=" + mCurrentTime);
                //针对fileid 进行编码；
                //格式为： /appid/bucket/fileid;
                mFileID = "/"+ mAPPID + "/" +mBucket +mFileID;
                mFileID = urlEncoder(mFileID);
                stringBuilder.append("&f="+mFileID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        signUrl = stringBuilder.toString();
        return signUrl;
    }

    /**
     * HAMCSHA1签名
     */

    private byte[] getHmacSha1(String signUrl, String scretKey){
        byte[] hmacSha1 = null;
        try {
            byte[] byteKey = scretKey.getBytes("utf-8");
            SecretKey hmacKey = new SecretKeySpec(byteKey, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(hmacKey);
            hmacSha1 = mac.doFinal(signUrl.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return hmacSha1;
    }

    /**
     * join byte
     */
    private byte[] joiner(byte[] hmacSHA1, byte[] signUrlToByte ){
        int len1 = hmacSHA1.length;
        int len2 = signUrlToByte.length;
        byte[] allByte = new byte[len1 + len2];
        for(int i = 0; i< len1; i++){
            allByte[i] = hmacSHA1[i];
        }
        for(int i = 0; i< len2; i ++){
            allByte[len1+i] = signUrlToByte[i];
        }
        return allByte;
    }

    /**
     *对fileID进行URLEncoder编码
     */
    private String  urlEncoder(String fileID){
        if(fileID == null){
            return fileID;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String[] strFiled = fileID.trim().split("/");
        int length = strFiled.length;
        for(int i = 0; i< length; i++){
            if("".equals(strFiled[i]))continue;
            stringBuilder.append("/");
            try{
                String str = URLEncoder.encode(strFiled[i], "utf-8").replace("+","%20");
                stringBuilder.append(str);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(fileID.endsWith("/")) stringBuilder.append("/");
        fileID = stringBuilder.toString();
        return fileID;
    }

    /**
     *
     * @param signUrl
     * @param ScrectKey
     * @return
     */
    private String  getSign(String signUrl, String ScrectKey){
        String sign = null;
        try {
            byte[] hmacSha1 = getHmacSha1(signUrl, ScrectKey);
            byte[] all = joiner(hmacSha1, signUrl.getBytes("utf-8"));
//            sign = Base64.encodeToString(all, Base64.DEFAULT);
            sign = ConverterByteBase64.byte2Base64( all );
        }catch (Exception e){
            e.printStackTrace();
        }
        return sign;
    }

    public static String getContent(String sign, String key){
        if(TextUtils.isEmpty(sign) || TextUtils.isEmpty(key)){
            return null;
        }
        String result =null;
        byte[] cotent =
//                Base64.decode(sign,Base64.DEFAULT)
                new byte[0];
        try {
            cotent = ConverterByteBase64.base642Byte( sign );
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
        try {
            String str = new String(cotent,20,cotent.length - 20);
            result = parserUrlParam(str,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String parserUrlParam(String signUrl, String key) {
        String value = "";
        key = key + "=";
        int start = signUrl.indexOf(key);
        if(start < 0)
            return "";
        int valuestart = start + key.length();
        int end = 0;
        if((end = signUrl.indexOf("&", valuestart)) > valuestart)
            value = signUrl.substring(valuestart, end);
        else if((end = signUrl.indexOf("#", valuestart)) > valuestart)
            value = signUrl.substring(valuestart, end);
        else
            value = signUrl.substring(start + key.length());
        return value;
    }
}
