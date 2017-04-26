package foolkey.controller.register;

import foolkey.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by geyao on 2017/4/26.
 */
@Controller
@RequestMapping("/rsa/register")
public class SaveUserController extends AbstractController{

    @RequestMapping
    public void execute(
            @RequestParam(name = "cipherText")String cipherText,
            HttpServletRequest request
    ){

    }
}
