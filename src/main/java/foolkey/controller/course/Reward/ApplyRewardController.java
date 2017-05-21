package foolkey.controller.course.Reward;

import foolkey.controller.AbstractController;
import foolkey.handler.order.ApplyStudentCourseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/3.
 */
@RequestMapping("/aes/applyReward")
@Controller
public class ApplyRewardController extends AbstractController{
    @Autowired
    private ApplyStudentCourseHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        handler.execute(request, response, jsonObject);
    }
}
