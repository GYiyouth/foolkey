package foolkey.pojo.root.bo.security;

import foolkey.pojo.root.CAO.key.KeyCAO;
import foolkey.pojo.root.DAO.com.RSAKeyDAO;
import foolkey.pojo.root.vo.assistObject.RSAKeyDTO;
import foolkey.tool.ConverterByteBase64;
import foolkey.tool.cache.Cache;
import foolkey.tool.security.RSACoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 获取公私钥
 * Created by geyao on 2017/4/25.
 */
@Service("rsaKeyBO")
@Transactional
public class RSAKeyBO {
//    private static final String token1 = "key";
//    private static final String token2 = "rsaPubKey";
//    private static final String token3 = "rsaPriKey";


    @Resource(name = "rsaKeyDAO")
    private RSAKeyDAO rsaKeyDAO;

    @Resource(name = "keyCAO")
    private KeyCAO keyCAO;

    @Resource(name = "localCache")
    private Cache cache;

    @Resource(name = "rsaCoder")
    private RSACoder rsaCoder;

    private byte[] priBytes;
    private byte[] pubBytes;


    public RSAKeyBO() {
        super();
    }

    /**
     * 获取服务器公私钥对
     * @return RSAKey DTO
     * @throws Exception
     */
    public RSAKeyDTO getServerRSAKeyDTO() throws Exception{
        RSAKeyDTO rsaKeyDTO;
        if (keyCAO.containsServerRSAKey())
            rsaKeyDTO = keyCAO.getServerRSAKey();
        else { // 缓存中没有，则从数据库取，且存到缓存里
            rsaKeyDTO = rsaKeyDAO.getServerRSAKeyDTO();
        }
        return rsaKeyDTO;
    }

    /**
     * 私钥加密
     * @param raw
     * @return base64编码的密文
     * @throws Exception
     */
    private String encryptByPri(String raw) throws Exception{
        byte[] cipherBytes = rsaCoder.encryptByPrivateKey(raw.getBytes(), priBytes);
        String cipherBase64Str = ConverterByteBase64.byte2Base64(cipherBytes);
        return cipherBase64Str;
    }

    /**
     * 公钥解密
     * @param cipherBase64Str
     * @return
     * @throws Exception
     */
    private String decrypyBase64StrByPub(String cipherBase64Str) throws Exception{
        byte[]cipherBytes = ConverterByteBase64.base642Byte(cipherBase64Str);
        byte[] clearBytes = rsaCoder.decryptByPublicKey(cipherBytes, pubBytes);
        return new String(clearBytes);
    }

    /**
     * 公钥加密
     * @param raw
     * @return
     * @throws Exception
     */
    public String encryptByPub(String raw, String pubKeyStrBase64) throws Exception{
        byte[] cipherBytes = rsaCoder.encryptByPublicKey(
                raw.getBytes(), ConverterByteBase64.base642Byte(pubKeyStrBase64)
        );
        String cipherBase64Str = ConverterByteBase64.byte2Base64(cipherBytes);
        return cipherBase64Str;
    }

    /**
     * 私钥解密
     * @param cipherBase64Str
     * @return
     * @throws Exception
     */
    public String decrypyBase64StrByPri(String cipherBase64Str, String priKeyStrBase64) throws Exception{
        byte[] cipherBytes = ConverterByteBase64.base642Byte(cipherBase64Str);
        byte[] priKeyBytes = ConverterByteBase64.base642Byte(priKeyStrBase64);
        byte[] clearBytes = rsaCoder.decryptByPrivateKey(cipherBytes, priKeyBytes);
        return new String(clearBytes);
    }

    public byte[] getPriBytes() {
        return priBytes;
    }

    public void setPriBytes(byte[] priBytes) {
        this.priBytes = priBytes;
    }

    public byte[] getPubBytes() {
        return pubBytes;
    }

    public void setPubBytes(byte[] pubBytes) {
        this.pubBytes = pubBytes;
    }
}
