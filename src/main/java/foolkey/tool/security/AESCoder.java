package foolkey.tool.security;

import foolkey.tool.ConverterByteBase64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by geyao on 2017/4/26.
 */
@Component("aesCoder")
public class AESCoder {

    //对称密钥算法
    public static final String KEY_ALGORITHM="AES";


    /**
     * 密钥长度
     * */
    private static final int KEY_SIZE = 128;

    public String getAESKeyBase64() throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(KEY_SIZE);
        SecretKey key = keyGenerator.generateKey();
        String base64Str = ConverterByteBase64.byte2Base64(key.getEncoded());
        return  base64Str;
    }

    public SecretKey loadKeyAES(String base64Key) throws Exception{
        byte[] bytes = ConverterByteBase64.base642Byte(base64Key);
        SecretKey key = new SecretKeySpec(bytes, KEY_ALGORITHM);
        return key;
    }

    public byte[] encryptAES(byte[] source, SecretKey secretKey) throws Exception{
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        byte[] bytes = cipher.doFinal(source);
//        return  bytes;


        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);// 创建密码器
        byte[] byteContent = source;
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(byteContent);
        return result; // 加密
    }

    public byte[] decryptAES(byte[] source, SecretKey secretKey) throws Exception{
//        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        byte[] bytes = cipher.doFinal(source);
//        return bytes;

        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(source);
        return result; // 加密
    }
}
