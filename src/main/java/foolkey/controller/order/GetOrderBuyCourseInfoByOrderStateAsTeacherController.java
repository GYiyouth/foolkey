package foolkey.controller.order;

import foolkey.controller.AbstractController;
import foolkey.handler.order.GetOrderCourseByOrderStateAsTeacherHandler;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GR on 2017/5/14.
 */
@Controller
@RequestMapping(value = "/order/getOrderBuyCourseInfoByOrderStateAsTeacher")
public class GetOrderBuyCourseInfoByOrderStateAsTeacherController extends AbstractController{


    @Autowired
    private GetOrderCourseByOrderStateAsTeacherHandler getOrderCourseByOrderStateAsTeacherHandler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ){
        getOrderCourseByOrderStateAsTeacherHandler.execute(request,response,jsonObject);
    }

}
