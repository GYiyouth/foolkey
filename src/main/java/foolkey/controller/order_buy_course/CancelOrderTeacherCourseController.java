package foolkey.controller.order_buy_course;

import foolkey.pojo.root.bo.palce_order.CancelOrderTeacherCourseBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/1.
 */
@RequestMapping ("/aes/cancelOrderTeacherCourse")
@Controller
public class CancelOrderTeacherCourseController {

    @Autowired
    private CancelOrderTeacherCourseBO cancelOrderTeacherCourseBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        cancelOrderTeacherCourseBO.execute(request, response);
    }
}
