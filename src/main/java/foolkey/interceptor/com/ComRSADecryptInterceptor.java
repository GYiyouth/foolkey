package foolkey.interceptor.com;

import foolkey.interceptor.AbstractInterceptor;
import foolkey.pojo.root.bo.security.RSAKeyBO;
import foolkey.pojo.root.vo.assistObject.RSAKeyDTO;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * 普遍试用的RSA解密
 * Created by geyao on 2017/4/28.
 */
@Controller(value = "comRSADecryptInterceptor")
public class ComRSADecryptInterceptor extends AbstractInterceptor {

    @Resource(name = "rsaKeyBO")
    private RSAKeyBO rsaKeyBO;

    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object o
    )throws Exception {
//        try {
            request.setCharacterEncoding("UTF-8");

            //获取JSON
            String cipherText = request.getParameter("cipherText");

            //需要前端提供以下字段，分别加密
            JSONObject jsonObject = JSONObject.fromObject(cipherText);

            //拿取私钥
            RSAKeyDTO rsaKeyDTO= rsaKeyBO.getServerRSAKeyDTO();
            String priKeyStr = rsaKeyDTO.getPriBase64Str();

            //遍历该json对象，每一项都解密，放置在request里
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()){
                //拿到key
                String key = iterator.next().toString();
                //拿到密文
                String rawCipher = jsonObject.getString(key);
                //对密文进行预处理
                rawCipher = cipherPreHandler(rawCipher);
                //解密，获取明文
                String clearText = rsaKeyBO.decrypyBase64StrByPri(rawCipher, priKeyStr);
                //放置在request里
                request.setAttribute(key, clearText);
            }


            return true;
//
//        }catch (Exception e){
//            e.printStackTrace();
////            jsonHandler.sendFailJSON(response);
//        }
//        return false;
    }

}
