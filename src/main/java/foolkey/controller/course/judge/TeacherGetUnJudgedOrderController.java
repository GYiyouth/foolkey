package foolkey.controller.course.judge;

import foolkey.controller.AbstractController;
import foolkey.handler.course.judge.TeacherGetUnJudgedOrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 教师获取待评价订单
 * Created by 葛尧 on 2017/5/13.
 */
@Controller
@RequestMapping("/aes/getOrderToJudge/teacher")
public class TeacherGetUnJudgedOrderController extends AbstractController{
    @Autowired
    private TeacherGetUnJudgedOrderHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        handler.execute(request, response, jsonObject);
    }
}
