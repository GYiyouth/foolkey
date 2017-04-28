package foolkey.interceptor.com;

import foolkey.interceptor.AbstractInterceptor;
import foolkey.pojo.root.bo.security.RSAKeyBO;
import foolkey.pojo.root.vo.assistObject.RSAKeyDTO;
import foolkey.tool.ConverterByteBase64;
import foolkey.tool.cache.Cache;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.net.URLDecoder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * 非对称加密的信息，解谜，密文
 * 【cipherText】
 * 密文由于长度限制，发送时，最好是每个字段分别加密，不要再一段密文里加太多东西
 * Created by geyao on 2017/4/26.
 */
@Controller(value = "rsaDecryptInterceptor")
public class RSADecryptInterceptor extends AbstractInterceptor {
    @Resource(name = "rsaKeyBO")
    private RSAKeyBO rsaKeyBO;


    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object o
    )throws Exception {
        try {
            request.setCharacterEncoding("UTF-8");

            //获取JSON
            String cipherText = request.getParameter("cipherText").toString();

            //需要前端提供以下字段，分别加密
            JSONObject jsonObject = JSONObject.fromObject(cipherText);
            String userNameCipher = jsonObject.get("userName").toString();
            String passWordCipher = jsonObject.get("passWord").toString();
            String aesKeyCipher = jsonObject.get("AESKey").toString();
            //对密文预处理
            userNameCipher = cipherPreHandler(userNameCipher);
            passWordCipher = cipherPreHandler(passWordCipher);
            aesKeyCipher = cipherPreHandler(aesKeyCipher);

            //拿取私钥
            RSAKeyDTO rsaKeyDTO= rsaKeyBO.getServerRSAKeyDTO();
            String priKeyStr = rsaKeyDTO.getPriBase64Str();

            //解密
            String userName = rsaKeyBO.decrypyBase64StrByPri(userNameCipher, priKeyStr);
            String passWord = rsaKeyBO.decrypyBase64StrByPri(passWordCipher, priKeyStr);
            String aesKey = rsaKeyBO.decrypyBase64StrByPri(aesKeyCipher, priKeyStr);

            //传给control
            request.setAttribute("userName", userName);
            request.setAttribute("passWord", passWord);
            request.setAttribute("AESKey", aesKey);

            return true;

        }catch (Exception e){
            e.printStackTrace();
//            jsonHandler.sendFailJSON(response);
        }
        return false;
    }



}
