package foolkey.controller.course.haveClass;

import foolkey.controller.AbstractController;
import foolkey.handler.course.haveClass.StartClassHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 上课
 * aes加密
 * Created by geyao on 2017/5/6.
 */
@Controller
@RequestMapping("/startClass")
public class StartClassController extends AbstractController{
    @Autowired
    private StartClassHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        handler.execute(request, response, jsonObject);
    }
}
