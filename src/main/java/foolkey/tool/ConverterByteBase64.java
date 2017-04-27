package foolkey.tool;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by geyao on 2017/4/25.
 */
public class ConverterByteBase64 {
    public static String byte2Base64(byte[] bytes){
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return  base64Encoder.encode(bytes);
    }

    public static  byte[] base642Byte(String base64) throws IOException{
        BASE64Decoder base64Decoder = new BASE64Decoder();
        return  base64Decoder.decodeBuffer(base64);
    }
}
