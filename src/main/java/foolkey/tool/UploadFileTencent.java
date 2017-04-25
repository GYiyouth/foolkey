package foolkey.tool;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.meta.InsertOnly;
import com.qcloud.cos.request.GetFileLocalRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

/**
 * Created by geyao on 2017/4/25.
 */
public class UploadFileTencent {
    private static final long appId = 1252706879;
    private static final String secretId = "AKIDlU4rgPAJTXbaTDTDVuKuKh2GJDwbgPkw";
    private static final String secretKey = "AD5mKsOjtsiP8kCTaWOE1e6NSO9jASZW";
    // 设置要操作的bucket
    private static final String bucketName = "foolkey";

    static {



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

        UploadFileRequest fileRequest = new UploadFileRequest(bucketName, "/photo/test.png",
                "/Users/geyao/Downloads/最帅的人.jpg");
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
        download();
    }

}
