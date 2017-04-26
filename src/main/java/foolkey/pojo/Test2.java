package foolkey.pojo;

import foolkey.tool.ConverterByteBase64;
import foolkey.tool.security.AESCoder;
import foolkey.tool.security.RSACoder;

import javax.crypto.SecretKey;

/**
 * Created by geyao on 2017/4/26.
 */
public class Test2 {

    public static void main(String[] args) throws Exception{
        String raw = "对称加密的密钥";

        //对称加密
        AESCoder aesCoder = new AESCoder();
        String aesKey = aesCoder.getAESKeyBase64();
        System.out.println("对称加密 " +raw);
        System.out.println(aesKey);
        byte[] keys = ConverterByteBase64.base642Byte(aesKey);
        System.out.println( keys.length);
        SecretKey key = aesCoder.loadKeyAES(aesKey);
        byte[] ciper = aesCoder.encryptAES(raw.getBytes("UTF-8"), key);
        byte[] clear = aesCoder.decryptAES(ciper, key);

        System.out.println("密文\n" + ConverterByteBase64.byte2Base64(ciper));
        System.out.println("明文\n" + new String(clear));

        RSACoder rsaCoder = new RSACoder();
        byte[] rsaPriBytes = rsaCoder.getPrivateKey();
        byte[] ciperbytes = rsaCoder.encryptByPrivateKey(aesKey.getBytes(), rsaPriBytes);
        byte[] rsaPubBytes = rsaCoder.getPublicKey();
        byte[] clearBytes = rsaCoder.decryptByPublicKey(ciperbytes, rsaPubBytes);
        String newAESKeyStr = new String(clearBytes);
        System.out.println("对称加密的密钥是 + " + newAESKeyStr);
        System.out.println("与加密前是否相等？ " + newAESKeyStr.equals(aesKey));
        System.out.println("再用它解密密文看看？ ");
        byte[] finalbytes = aesCoder.decryptAES(ciper, key);
        System.out.println(new String(finalbytes));
    }
}
