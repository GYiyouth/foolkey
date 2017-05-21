package foolkey.controller.pay;

import foolkey.controller.AbstractController;
import foolkey.handler.pay.PayTeacherCourseOrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/1.
 */
@Controller
@RequestMapping("/aes/payOrder/teacherCourse")
public class PayTeacherCourseOrderController extends AbstractController{
    @Autowired
    private PayTeacherCourseOrderHandler payTeacherCourseOrderHandler;

    @RequestMapping
    public void execute(
            HttpServletResponse response,
            HttpServletRequest request
    )throws Exception{
        payTeacherCourseOrderHandler.execute(request, response, jsonObject);
    }
}
