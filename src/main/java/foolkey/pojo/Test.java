package foolkey.pojo;

import foolkey.pojo.root.bo.security.RSAKeyBO;
import foolkey.tool.BeanFactory;
import foolkey.tool.ConverterByteBase64;
import foolkey.tool.security.RSACoder;

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
        String raw = "愚人科技 水==\n 电费水电费飞 ";
        String raw16 = encode(raw);
        System.out.println(raw16);
        System.out.println(decode(raw16));
    }
    /*
* 将字符串编码成16进制数字,适用于所有字符（包括中文）
*/
    public static String encode(String str)
    {
// 根据默认编码获取字节数组
        byte[] bytes=str.getBytes();
        StringBuilder sb=new StringBuilder(bytes.length*2);
// 将字节数组中每个字节拆解成2位16进制整数
        for(int i=0;i<bytes.length;i++)
        {
            sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
            sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
        }
        return sb.toString();
    }
    private static String hexString="0123456789ABCDEF";
    /*
    * 将16进制数字解码成字符串,适用于所有字符（包括中文）
    */
    public static String decode(String bytes)
    {
        ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);
// 将每2位16进制整数组装成一个字节
        for(int i=0;i<bytes.length();i+=2)
            baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1))));
        return new String(baos.toByteArray());
    }


}
