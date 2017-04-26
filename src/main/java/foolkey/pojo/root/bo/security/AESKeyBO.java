package foolkey.pojo.root.bo.security;

import foolkey.pojo.root.CAO.key.KeyCAO;
import foolkey.tool.ConverterByteBase64;
import foolkey.tool.cache.Cache;
import foolkey.tool.security.AESCoder;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 不管健壮，追求性能
 * Created by geyao on 2017/4/26.
 */
@Service("aesKeyBO")
public class AESKeyBO {


    private static final String token1 = "key";
    private static final String token2 = "aesKey";

    @Resource(name = "aesCoder")
    private AESCoder aesCoder;
    @Resource(name = "localCache")
    private Cache cache;
    @Resource(name = "keyCAO")
    private KeyCAO keyCAO;

    private String base64key;

    /**
     * 先从缓存中获取该用户的key，没有的话，建一个
     * @return base64编码的key
     */
    public String getKeybase64Str(String userToken) throws Exception{
        if (keyCAO.containsAESKey(userToken))
            return keyCAO.getUserAESKeyDTO(userToken);
        return aesCoder.getAESKeyBase64();
    }

    /**
     * 加密
     * @param RowStr 明文
     * @param base64KeyStr base64格式的密钥
     * @return base64格式的密文
     * @throws Exception
     */
    public String encrypt(String RowStr, String base64KeyStr) throws Exception{
        byte[] cipherBytes = aesCoder.encryptAES(RowStr.getBytes(),
                aesCoder.loadKeyAES(base64KeyStr)
                );
        return ConverterByteBase64.byte2Base64(cipherBytes);
    }

    public String encrypt(String RowStr) throws Exception{
        byte[] cipherBytes = aesCoder.encryptAES(RowStr.getBytes(),
                aesCoder.loadKeyAES(base64key)
        );
        return ConverterByteBase64.byte2Base64(cipherBytes);
    }

    /**
     * 解密
     * @param cipherBase64Str base64格式的密文
     * @param base64KeyStr base64格式的密钥
     * @return 明文
     * @throws Exception
     */
    public String decrypt(String cipherBase64Str, String base64KeyStr) throws Exception{
        byte[] cipherBytes = ConverterByteBase64.base642Byte(cipherBase64Str);
        byte[] clearBytes = aesCoder.decryptAES( cipherBytes,
                aesCoder.loadKeyAES(base64KeyStr)
                );
        return new String(clearBytes);
    }
    public String decrypt(String cipherBase64Str ) throws Exception{
        byte[] cipherBytes = ConverterByteBase64.base642Byte(cipherBase64Str);
        byte[] clearBytes = aesCoder.decryptAES( cipherBytes,
                aesCoder.loadKeyAES(base64key)
        );
        return new String(clearBytes);
    }
}
