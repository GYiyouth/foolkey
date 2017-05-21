package foolkey.controller.course.Reward;

import foolkey.controller.AbstractController;
import foolkey.handler.application.RefuseTeacherApplicationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拒绝老师对于悬赏任务的申请
 * aes加密
 * Created by geyao on 2017/5/4.
 */
@Controller(value = "RewardRefuse")
@RequestMapping("/aes/reward/refuse")
public class RefuseController extends AbstractController{

    @Autowired
    private RefuseTeacherApplicationHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        handler.execute(request, response, jsonObject);
    }
}
