package foolkey.interceptor.com;

import foolkey.interceptor.AbstractInterceptor;
import foolkey.pojo.root.bo.security.AESKeyBO;
import foolkey.tool.JSONHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用aes加密的后，提交到三个name
 * clearText:JSON格式，明文，必须包含token
 * validation:由clearText 对称加密得来
 * cipherText:一些保密数据，密文
 * Created by geyao on 2017/4/28.
 */
@Controller("aesDecryptInterceptor")
public class AESDecryptInterceptor extends AbstractInterceptor{
    @Resource(name = "aesKeyBO")
    private AESKeyBO aesKeyBO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        try {
            request.setCharacterEncoding("UTF-8");
            JSONObject clearText = JSONObject
                    .fromObject(request.getParameter("clearText"));
            String token = clearText.getString("token");

            //获取缓存中，用户的对称密钥
            String aesKey = aesKeyBO.getKeybase64Str(token);
            //解密验证字段，并和1做比较
            String validationCipherStr = request.getParameter("validation");
            String validationClearStr = aesKeyBO.decrypt(validationCipherStr, aesKey);
            JSONObject validation = JSONObject
                    .fromObject(validationClearStr);
            if (!validation.equals(clearText))
                return false;

            //获取第三部分，密文，并解密
            String cipherStr = request.getParameter("cipherText");
            cipherStr = aesKeyBO.decrypt(cipherStr, aesKey);
            JSONObject cipherText = JSONObject
                    .fromObject(cipherStr);

            //在request里放置1，3 JSONObject
            request.setAttribute("clearText", clearText);
            request.setAttribute("cipherText", cipherText);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            JSONHandler.sendFailJSON(response);
            return false;
        }
    }
}
