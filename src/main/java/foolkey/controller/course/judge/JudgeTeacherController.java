package foolkey.controller.course.judge;

import foolkey.controller.AbstractController;
import foolkey.handler.course.judge.EvaluateTeacherHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/13.
 */
@RequestMapping("/aes/judge")
@Controller
public class JudgeTeacherController extends AbstractController{

    @Autowired
    private EvaluateTeacherHandler evaluateTeacherHandler;
    @RequestMapping("/teacher")
    public void teacher(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        evaluateTeacherHandler.execute(request, response, jsonObject);
    }
}
