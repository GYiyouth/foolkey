package foolkey.pojo;

import foolkey.pojo.root.bo.register.UserRegisterBO;
import foolkey.pojo.root.bo.security.RSAKeyBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.tool.BeanFactory;
import foolkey.tool.ConverterByteBase64;
import foolkey.tool.security.RSACoder;
import foolkey.tool.security.StringMatchRate;
import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by geyao on 2017/4/24.
 */
public class Test {



    public static void main(String[] args) throws Exception{

//        KeyPair keyPair = rsaCoder.getKeyPair();
//        String pubstr = rsaCoder.getStringPublicKey(keyPair);
//        System.out.println("公钥是 \n" + pubstr);
//        String pristr = rsaCoder.getStringPrivateKey(keyPair);
//        System.out.println("私钥是 \n" + pristr);
//
//        byte[] Ciphertext = rsaCoder.publicEncrypt(raw.getBytes(), (RSAPublicKey)keyPair.getPublic());
//
//        System.out.println("加密后 \n " + ConverterByteBase64.byte2Base64(Ciphertext));
//        System.out.println("公钥加密， 私钥解密");
//        byte[] clearText = rsaCoder.privateDecrypt(Ciphertext, rsaCoder.getPrivateKey(pristr));
//        String clearStr = new String(clearText);
//        System.out.println(clearStr);
//
//        RSACoder rsaCoderOld = new RSACoder();
//        byte[] pubbytes = rsaCoderOld.getPublicKey();
//        byte[] pribytes = rsaCoderOld.getPrivateKey();
//        System.out.println("公钥是" + ConverterByteBase64.byte2Base64(pubbytes));
//        System.out.println("密钥是" + ConverterByteBase64.byte2Base64(pribytes));
//
//        Ciphertext = rsaCoderOld.encryptByPublicKey(raw.getBytes(), pubbytes);
//
//        System.out.println("密文 " + new String(Ciphertext));
//        clearText = rsaCoderOld.decryptByPrivateKey(Ciphertext, pribytes);
//        System.out.println("明文" + new String(clearText));
//
//        System.out.println("换密钥加锁，公钥解密");
//
//        Ciphertext = rsaCoderOld.encryptByPrivateKey(raw.getBytes(), pribytes);
//
//        System.out.println("密文 " + ConverterByteBase64.byte2Base64(Ciphertext));
//        clearText = rsaCoderOld.decryptByPublicKey(Ciphertext, pubbytes);
//        System.out.println("明文 " + new String(clearText));


//        System.out.println("=======");
//        System.out.println();
//        System.out.println();
//        AESCoder aesCoder = new AESCoder();
//        String aesKey = aesCoder.getAESKeyBase64();
//        System.out.println("对称加密");
//        System.out.println(aesKey);
//        byte[] keys = ConverterByteBase64.base642Byte(aesKey);
//        System.out.println( keys.length);
//        SecretKey key = aesCoder.loadKeyAES(aesKey);
//        byte[] ciper = aesCoder.encryptAES(raw.getBytes("UTF-8"), key);
//        byte[] clear = aesCoder.decryptAES(ciper, key);
//
//        System.out.println("密文\n" + ConverterByteBase64.byte2Base64(ciper));
//        System.out.println("明文\n" + new String(clear));
        String str1 = "1234567890-=abc";
        String str2 = "1234557890-=abc";
        System.out.println(StringMatchRate.getMatchRate(str1, str2));
    }


}
