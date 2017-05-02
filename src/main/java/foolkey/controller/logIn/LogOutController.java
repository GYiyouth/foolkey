package foolkey.controller.logIn;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.logIn_logOut.LogOutBO;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登出，需要提供token
 * 采用AES对称加密方式，使得每人只能控制自己登出
 * Created by geyao on 2017/4/28.
 */
@Controller
@RequestMapping("/aes/logOut")
public class LogOutController extends AbstractController {
    @Resource(name = "logOutBO")
    private LogOutBO logOutBO;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception{
        //获取clearJSON，这是个JSON对象，再获取token
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(
                clearText
        );
        String token = clearJSON.getString("token");
        //根据token找到对应的AESKey
            //拦截器已经完成验证
        //清空用户的缓存
        logOutBO.removeUserCache(token);
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);
    }
}
