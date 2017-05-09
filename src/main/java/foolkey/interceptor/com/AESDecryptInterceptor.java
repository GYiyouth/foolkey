package foolkey.interceptor.com;

import foolkey.interceptor.AbstractInterceptor;
import foolkey.pojo.root.bo.security.AESKeyBO;
import foolkey.tool.JSONHandler;
import foolkey.tool.security.StringMatchRate;
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
 *
 * request.setAttribute("clearText", clearText);
 * request.setAttribute("cipherText", cipherText);
   并不在意这两者是什么格式，不会转换
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
            String clearText = request.getParameter("clearText");
            JSONObject clearJSON = JSONObject
                    .fromObject(clearText);
            String token = clearJSON.getString("token");

            //获取缓存中，用户的对称密钥
            String aesKey = aesKeyBO.getKeybase64Str(token);

            //解密验证字段，并和1做比较
            String validationCipherStr = request.getParameter("validation");
            //预处理
            validationCipherStr = validationCipherStr.substring(0, validationCipherStr.length() -1);
            validationCipherStr = validationCipherStr.replaceAll("愚", "\n");
//            System.out.println(validationCipherStr);
            //解密
            String validationClearStr = aesKeyBO.decrypt(validationCipherStr, aesKey);

            JSONObject validation = JSONObject
                    .fromObject(validationClearStr);
            //匹配率小于0.8，则说明不相等
            if (StringMatchRate.getMatchRate(validationClearStr, clearText) < 0.8) {
                System.out.println("明密文不相等");
                System.out.println(validationClearStr);
                return false;
            }
            //获取第三部分，密文，并解密
            String cipherStr = request.getParameter("cipherText");;

            //如果第三部分为空，则处理一下，预防抛出异常
            if (cipherStr == null || cipherStr.equals(""))
                cipherStr = "{}";
            else
                cipherStr = aesKeyBO.decrypt(cipherStr, aesKey);
            JSONObject cipherText = JSONObject
                    .fromObject(cipherStr);

            //在request里放置1，3 JSONObject
            request.setAttribute("clearText", clearJSON);
            request.setAttribute("cipherText", cipherText);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
            return false;
        }
    }
}
