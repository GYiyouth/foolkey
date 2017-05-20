package foolkey.pojo.root.bo.security;

import foolkey.pojo.root.CAO.key.KeyCAO;
import foolkey.pojo.root.CAO.userInfo.UserCAO;
import foolkey.tool.security.AESOperator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 不管健壮，追求性能
 * Created by geyao on 2017/4/26.
 */
@Service("aesKeyBO")
public class AESKeyBO {


    private static final String token1 = "key";
    private static final String token2 = "aesKey";

    @Resource(name = "keyCAO")
    private KeyCAO keyCAO;
    @Resource(name = "userCAO")
    private UserCAO userCAO;


    @Resource(name = "aesOperator")
    private AESOperator aesOperator;

    /**
     * 从缓存中获取该用户的key，没有的话，返回false
     * @return base64编码的key
     */
    public String getKeybase64Str(String userToken) throws Exception{
        return keyCAO.getUserAESKeyBase64( userToken );
    }

    /**
     * 加密
     * @param RowStr 明文
     * @param base64KeyStr base64格式的密钥
     * @return base64格式的密文
     * @throws Exception
     */
    public String encrypt(String RowStr, String KeyStr) throws Exception{
//        byte[] cipherBytes = aesCoder.encryptAES(RowStr.getBytes(),
//                aesCoder.loadKeyAES(base64KeyStr)
//                );
//        return ConverterByteBase64.byte2Base64(cipherBytes);
        return aesOperator.encrypt(RowStr, KeyStr);
    }
    /**
     * 解密
     * @param cipherBase64Str base64格式的密文
     * @param base64KeyStr base64格式的密钥
     * @return 明文
     * @throws Exception
     */
    public String decrypt(String cipherBase64Str, String KeyStr) throws Exception{
//        byte[] cipherBytes = ConverterByteBase64.base642Byte(cipherBase64Str);
//        byte[] clearBytes = aesCoder.decryptAES( cipherBytes,
//                aesCoder.loadKeyAES(base64KeyStr)
//                );
//        return new String(clearBytes);
        return aesOperator.decrypt(cipherBase64Str, KeyStr);
    }

    /**
     * 更新、保存用户的AESKey 缓存
     * 如果缓存区不存在用户的缓存，则会开辟一块
     * @param token
     * @param aesBase64Key
     */
    public void saveUserAESKey(String token, String aesBase64Key){
        if (!userCAO.containsUser(token)){
            userCAO.initUserCache(token);
        }
        keyCAO.updateUserAESKey(aesBase64Key, token);
    }
}
