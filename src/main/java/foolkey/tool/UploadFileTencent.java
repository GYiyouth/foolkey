package foolkey.tool;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.exception.AbstractCosException;
import com.qcloud.cos.meta.InsertOnly;
import com.qcloud.cos.request.GetFileLocalRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import com.qcloud.cos.sign.Sign;

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
    static {



    }

    /**
     * 为一名用户生成一个上传的一次性签名
     * @param userName
     * @return
     */
    public static String getOneTimeSign(String userName) throws AbstractCosException {
//        Credentials cred = new Credentials(appId, secretId, secretKey);
//        String signStr = Sign.getOneEffectiveSign(bucketName, getOnlinePhoto(userName), cred);
//        return signStr;
        signUtils = new SignUtils(getOnlinePhoto( userName ), System.currentTimeMillis() / 1000 + 600);
        return signUtils.getSignForUser();
    }

    public static String getManyTimeSign(String userName) throws AbstractCosException {
        Credentials cred = new Credentials(appId, secretId, secretKey);
        long expired = System.currentTimeMillis() / 1000 + 600;
        String signStr = Sign.getPeriodEffectiveSign(bucketName, getOnlinePhoto(userName), cred, expired);
        return signStr;
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

    public static boolean download(){
        // 初始化秘钥信息
        Credentials cred = new Credentials(appId, secretId, secretKey);
        // 初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
        clientConfig.setRegion("sh");
        // 初始化cosClient
        COSClient cosClient = new COSClient(clientConfig, cred);

        GetFileLocalRequest request = new GetFileLocalRequest(bucketName, "/photo/test.png", "/Users/geyao/Downloads/tencent.png");
        System.out.println(cosClient.getFileLocal(request));
        return true;
    }

    public static void main(String[] args) {
        upload();
    }

}
