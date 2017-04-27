package foolkey.pojo;

import foolkey.pojo.root.bo.security.RSAKeyBO;
import foolkey.tool.BeanFactory;
import foolkey.tool.ConverterByteBase64;
import foolkey.tool.security.AESCoder;
import foolkey.tool.security.RSACoder;

import javax.crypto.SecretKey;

/**
 * Created by geyao on 2017/4/26.
 */
public class Test2 {

    public static void main(String[] args) throws Exception{
//        String raw = "对称加密的密钥";

        //对称加密
//        AESCoder aesCoder = new AESCoder();
//        String aesKey = aesCoder.getAESKeyBase64();
//        System.out.println("对称加密 " +raw);
//        System.out.println(aesKey);
//        byte[] keys = ConverterByteBase64.base642Byte(aesKey);
//        System.out.println( keys.length);
//        SecretKey key = aesCoder.loadKeyAES(aesKey);
//        byte[] ciper = aesCoder.encryptAES(raw.getBytes("UTF-8"), key);
//        byte[] clear = aesCoder.decryptAES(ciper, key);
//
//        System.out.println("密文\n" + ConverterByteBase64.byte2Base64(ciper));
//        System.out.println("明文\n" + new String(clear));

        RSAKeyBO keyBO = BeanFactory.getBean("rsaKeyBO", RSAKeyBO.class);
        String raw = "愚人科技";
        System.out.println("原文是 \n+ " + raw);
        String cipherText = keyBO.encryptByPub(raw, keyBO.getServerRSAKeyDTO().getPubBase64Str());

        String str = keyBO.decrypyBase64StrByPri(cipherText, keyBO.getServerRSAKeyDTO().getPriBase64Str());

        System.out.println("密钥解密 ");
        System.out.println(str.equals(raw));
        System.out.println(str);
    }
}
