package foolkey.controller.application;

import foolkey.controller.AbstractController;
import foolkey.handler.application.GetMyApplicationRewardAsTeacherHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 老师身份，获取我申请的悬赏
 * Created by GR on 2017/5/24.
 */
@Controller
@RequestMapping("/aes/reward/getApplicantAsTeacher")
public class GetMyApplicationRewardAsTeacherController extends AbstractController {

    @Autowired
    private GetMyApplicationRewardAsTeacherHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        handler.execute(request, response, jsonObject);
    }
}
