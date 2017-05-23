package foolkey.controller.order_buy_course;

import foolkey.controller.AbstractController;
import foolkey.handler.order.GetOrderAsTeacherByOrderStatesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 老师身份，根据课程类型参数（课程？悬赏），获取到某个状态的订单
 * 参数：
 * token
 * pageNo
 * courseTypeEnum：CourseTypeEnum：请求的课程类别（课程？悬赏）
 * orderStateEnum：OrderStateEnum：订单状态
 * <p>
 * 返回：
 * result：success,fail
 * orderList：List<OrderBuyCourseAsStudentDTO>
 * <p>
 * Created by GR on 2017/5/23.
 */
@Controller
@RequestMapping(value = "/aes/order/GetOrderAsTeacherByOrderStates")
public class GetOrderAsTeacherByOrderStatesController extends AbstractController {

    @Autowired
    private GetOrderAsTeacherByOrderStatesHandler handler;


    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        handler.execute(request, response, jsonObject);
    }
}
