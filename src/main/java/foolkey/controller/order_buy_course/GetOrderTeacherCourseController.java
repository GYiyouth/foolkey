package foolkey.controller.order_buy_course;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.JSONHandler;
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
public class GetOrderTeacherCourseController extends AbstractController{

    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private StudentInfoBO studentInfoBO;

    @RequestMapping(value = "/getOrderTeacherCourse")
    public void execute(
            HttpServletRequest request,
            @RequestParam("token") String token,
            HttpServletResponse response
    ){
        //获取-解析明文JSON数据
//            String clearText = request.getParameter("clearText");
//            JSONObject clearJSON = JSONObject.fromObject(clearText);
//
//            String token =clearJSON.getString("token");

        /**
         unPay, payed, applyRefund, agreeRefund, refundCompete,
         cancel, agreed, onClass, endClass,
         judged
         */
        // 按照订单状态分类的各种订单
        List<OrderBuyCourseDTO> orderCourseUnPay = new ArrayList<>();
        List<OrderBuyCourseDTO> orderCoursePayed = new ArrayList<>();
        List<OrderBuyCourseDTO> orderCourseApplyRefund = new ArrayList<>();
        List<OrderBuyCourseDTO> orderCourseAgreeRefund = new ArrayList<>();
        List<OrderBuyCourseDTO> orderCourseRefundCompete = new ArrayList<>();
        List<OrderBuyCourseDTO> orderCourseCancel = new ArrayList<>();
        List<OrderBuyCourseDTO> orderCourseAgreed = new ArrayList<>();
        List<OrderBuyCourseDTO> orderCourseOnClass = new ArrayList<>();
        List<OrderBuyCourseDTO> orderCourseEndClass = new ArrayList<>();
        List<OrderBuyCourseDTO> orderCourseJudged = new ArrayList<>();
        List<OrderBuyCourseDTO> orderCourseAll = new ArrayList<>();

        //根据token，获取当前用户的id
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        Long studentId = studentDTO.getId();
        try {
            //按照订单状态，获取该学生所有的订单
            orderCourseUnPay = orderInfoBO.getCourseOrderAsStudent(studentId, OrderStateEnum.unPay);
            orderCoursePayed = orderInfoBO.getCourseOrderAsStudent(studentId, OrderStateEnum.payed);
            orderCourseApplyRefund = orderInfoBO.getCourseOrderAsStudent(studentId, OrderStateEnum.applyRefund);
            orderCourseAgreeRefund = orderInfoBO.getCourseOrderAsStudent(studentId, OrderStateEnum.agreeRefund);
            orderCourseRefundCompete = orderInfoBO.getCourseOrderAsStudent(studentId, OrderStateEnum.refundCompete);
            orderCourseCancel = orderInfoBO.getCourseOrderAsStudent(studentId, OrderStateEnum.cancel);
            orderCourseAgreed = orderInfoBO.getCourseOrderAsStudent(studentId, OrderStateEnum.agreed);
            orderCourseOnClass = orderInfoBO.getCourseOrderAsStudent(studentId, OrderStateEnum.onClass);
            orderCourseEndClass = orderInfoBO.getCourseOrderAsStudent(studentId, OrderStateEnum.endClass);
            orderCourseJudged = orderInfoBO.getCourseOrderAsStudent(studentId, OrderStateEnum.judged);
            orderCourseAll = orderInfoBO.getCourseOrderAsStudent(studentId);

            //封装、传送JSON
            jsonObject.put("result","success");
            jsonObject.put("orderCourseUnPay",orderCourseUnPay);
            jsonObject.put("orderCoursePayed", orderCoursePayed);
            jsonObject.put("orderCourseApplyRefund", orderCourseApplyRefund);
            jsonObject.put("orderCourseAgreeRefund", orderCourseAgreeRefund);
            jsonObject.put("orderCourseRefundCompete", orderCourseRefundCompete);
            jsonObject.put("orderCourseCancel", orderCourseCancel);
            jsonObject.put("orderCourseAgreed", orderCourseAgreed);
            jsonObject.put("orderCourseOnClass", orderCourseOnClass);
            jsonObject.put("orderCourseEndClass", orderCourseEndClass);
            jsonObject.put("orderCourseJudged", orderCourseJudged);
            jsonObject.put("orderCourseAll", orderCourseAll);
            jsonHandler.sendJSON(jsonObject,response);
        }catch(Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }

}
