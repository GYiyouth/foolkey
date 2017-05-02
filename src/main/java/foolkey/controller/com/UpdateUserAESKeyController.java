package foolkey.controller.com;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.security.AESKeyBO;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 更换新的 对称密钥
 * request中含有2个json对象
 * clearText，cipherText
 * 更换完成后，要求重新登录
 * Created by geyao on 2017/4/28.
 */
@Controller
@RequestMapping(value = "/aes/updateAESKey")
public class UpdateUserAESKeyController extends AbstractController{
    @Resource(name = "aesKeyBO")
    private AESKeyBO aesKeyBO;

    @RequestMapping
    public void execute(
            HttpServletResponse response,
            HttpServletRequest request
    ){
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject clearText = (JSONObject) request.getAttribute("clearText");
            JSONObject cipherText = (JSONObject) request.getAttribute("cipherText");

            //从cipherText中获取该用户的aesKey
            String aesKey = cipherText.getString("AESKey");

            //在缓存中更新用户的aesKey
            String token = clearText.getString("token");
            aesKeyBO.saveUserAESKey(token, aesKey);
            //返回结果
            jsonObject.put("result", "success");
        }catch (Exception e){
            e.printStackTrace();
        }
        jsonHandler.sendJSON(jsonObject, response);

    }
}
