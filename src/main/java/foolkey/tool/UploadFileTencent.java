package foolkey.tool;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.exception.AbstractCosException;
import com.qcloud.cos.meta.InsertOnly;
import com.qcloud.cos.request.GetFileLocalRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import com.qcloud.cos.sign.Sign;
import com.sun.istack.internal.Nullable;

import java.io.File;

/**
 * Created by geyao on 2017/4/25.
 */
public class UploadFileTencent {
    private static final long appId = 1252706879;
    private static final String secretId = "AKIDlU4rgPAJTXbaTDTDVuKuKh2GJDwbgPkw";
    private static final String secretKey = "AD5mKsOjtsiP8kCTaWOE1e6NSO9jASZW";
    // 设置要操作的bucket
    private static final String bucketName = "foolkey";

    private static SignUtils signUtils;

    //下载一些默认图片到本地
    private static Integer defaultPicNums = 14;
    static {



    }

    /**
     * 为一名用户生成一个上传的一次性签名
     * @param userName
     * @return
     */
    public static String getOneTimeSign(@Nullable String userName) throws AbstractCosException {
//        Credentials cred = new Credentials(appId, secretId, secretKey);
//        String signStr = Sign.getOneEffectiveSign(bucketName, getOnlinePhoto(userName), cred);
//        return signStr;
        signUtils = new SignUtils(null, 2);
        return signUtils.getSignForUser();
    }

    public static String getManyTimeSign(@Nullable String userName) throws AbstractCosException {
//        Credentials cred = new Credentials(appId, secretId, secretKey);
//        long expired = System.currentTimeMillis() / 1000 + 600;
//        String signStr = Sign.getPeriodEffectiveSign(bucketName, getOnlinePhoto(userName), cred, expired);
//        return signStr;
        signUtils = new SignUtils(null, 2);
        return signUtils.getSignForUser();
    }

    /**
     * 生成一名用户的头像链接
     * @param userName
     * @return
     */
    private static String getOnlinePhoto(String userName){
        return  "/photo/user/head/"
                + "photo/" + userName + ".png"
                ;
    }

    public static boolean upload(){
        // 初始化秘钥信息
        Credentials cred = new Credentials(appId, secretId, secretKey);
        // 初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
        clientConfig.setRegion("sh");
        // 初始化cosClient
        COSClient cosClient = new COSClient(clientConfig, cred);

        UploadFileRequest fileRequest = new UploadFileRequest(bucketName, "/photo/test3.png",
                "/Users/geyao/Desktop/mmexport1446891929539.jpg");
        fileRequest.setInsertOnly(InsertOnly.OVER_WRITE);

        System.out.println(cosClient.uploadFile(fileRequest));
        return true;
    }

    public static boolean upload(String cosPath, String localPath){
        // 初始化秘钥信息
        Credentials cred = new Credentials(appId, secretId, secretKey);
        // 初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
        clientConfig.setRegion("sh");
        // 初始化cosClient
        COSClient cosClient = new COSClient(clientConfig, cred);

        localPath = System.getenv("HOME") + localPath;
        UploadFileRequest fileRequest = new UploadFileRequest(bucketName, cosPath,
                localPath);
        fileRequest.setInsertOnly(InsertOnly.OVER_WRITE);

        System.out.println(cosClient.uploadFile(fileRequest));
        return true;
    }

    public static boolean download(String cosPath){
        // 初始化秘钥信息
        Credentials cred = new Credentials(appId, secretId, secretKey);
        // 初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
        clientConfig.setRegion("sh");
        // 初始化cosClient
        COSClient cosClient = new COSClient(clientConfig, cred);

        String localPath = System.getenv("HOME") + cosPath;

        //如果本地文件夹不存在，则新建
        File file = new File( localPath );
        if ( !file.getParentFile().exists() ) {
            file.getParentFile().mkdirs();
        }else {
            //文件已存在，则删除
            if (file.exists())
                file.delete();
        }
        GetFileLocalRequest request = new GetFileLocalRequest(bucketName, cosPath, localPath);
        System.out.println(cosClient.getFileLocal(request));
        return true;
    }

    public static void main(String[] args) {
        upload();
    }


    /**
     * 根据用户名，获取在COS上的相对路径
     * @param userName
     * @return
     */
    public static String getUserPhotoCosPath(String userName){
        return "/photo/user/head/" + userName + ".png";
    }

    public static String getDefaultPhotoCostPath(Integer num){
        return "/photo/default/" + num + ".png";
    }



}
