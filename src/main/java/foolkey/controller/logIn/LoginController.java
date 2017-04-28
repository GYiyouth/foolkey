package foolkey.controller.logIn;

import foolkey.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户名、密码登录
 * 这种情况的产生有以下原因：
 * 客户端丢失了token
 * 服务器丢失了该用户的缓存
 *
 * 需要用户提交RSA加密的 用户名、密码、AESkey
 * 返回用户token，使用aes加密
 *
 * Created by geyao on 2017/4/28.
 */
@Controller
@RequestMapping("/rsa/login")
public class LoginController extends AbstractController {

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        //request中获取JSONObject，拿取三项信息

        //从
    }
}
