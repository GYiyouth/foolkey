package foolkey.controller.order_buy_course;

import foolkey.controller.AbstractController;
import foolkey.handler.order.CancelOrderTeacherCourseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/1.
 */
@RequestMapping ("/aes/cancelOrder")
@Controller
public class CancelOrderController extends AbstractController{

    @Autowired
    private CancelOrderTeacherCourseHandler cancelOrderTeacherCourseHandler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        cancelOrderTeacherCourseHandler.execute(request, response,jsonObject);
    }
}
