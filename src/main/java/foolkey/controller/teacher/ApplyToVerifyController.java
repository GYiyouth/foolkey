package foolkey.controller.teacher;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.handler.teacher.ApplyToVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/3.
 */
@Controller
@RequestMapping("/applyToVerifyTeacher")
public class ApplyToVerifyController extends AbstractController {

    @Autowired
    private ApplyToVerifyHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        handler.execute(request, response, jsonObject);
    }
}
