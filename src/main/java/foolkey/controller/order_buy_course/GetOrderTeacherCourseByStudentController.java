package foolkey.controller.order_buy_course;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.JSONHandler;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ustcg on 2017/5/6.
 */
@Controller
@RequestMapping(value = "/order")
public class GetOrderTeacherCourseByStudentController extends AbstractController {

    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private StudentInfoBO studentInfoBO;

    @RequestMapping(value = "/getOrderTeacherCourseByStudent")
    public void execute(
            HttpServletRequest request,
//            @RequestParam("token") String token,
            HttpServletResponse response
    ) {
        //获取-解析明文JSON数据
        String clearText = request.getParameter("clearText");
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        String orderStateStr = clearJSON.getString("orderStateEnum");
        OrderStateEnum orderStateEnum = OrderStateEnum.valueOf(orderStateStr);


        /**
         unPay, payed, applyRefund, agreeRefund, refundCompete,
         cancel, agreed, onClass, endClass,
         judged
         */
        // 按照订单状态分类的各种订单
        List<OrderBuyCourseDTO> orderBuyCourseDTOS = new ArrayList<>();

        //根据token，获取当前用户的id
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        Long studentId = studentDTO.getId();
        try {
            //按照订单状态，获取该学生所有的订单
            orderBuyCourseDTOS = orderInfoBO.getCourseOrderAsStudent(studentId, orderStateEnum);
//            for()

            //封装、传送JSON
            jsonObject.put("result", "success");
            jsonObject.put("orderBuyCourseDTOS", orderBuyCourseDTOS);
            jsonHandler.sendJSON(jsonObject, response);
        } catch (Exception e) {
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }

}
