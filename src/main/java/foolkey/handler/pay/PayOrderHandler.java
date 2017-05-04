package foolkey.handler.pay;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.coupon.CouponInfoBO;
import foolkey.pojo.root.bo.coupon.UseCouponBO;
import foolkey.pojo.root.bo.message.MessageOrderBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.pay_order.PayForOrderBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.dto.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付订单
 * AES加密
 * 获取token，订单id，优惠券id
 * 修改订单状态，生成消息，申请，发送给老师
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class PayOrderHandler extends AbstractBO{

    @Autowired
    private OrderInfoBO getOrderBO;
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private CouponInfoBO couponInfoBO;
    @Autowired
    private UseCouponBO useCouponBO;
    @Autowired
    private PayForOrderBO payForOrderBO;
    @Autowired
    private OrderInfoBO updateOrderBO;
    @Autowired
    private MessageOrderBO messageOrderBO;
    @Autowired
    private ApplicationInfoBO applicationBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        String orderId = clearJSON.getString("orderId");
        String couponId = clearJSON.getString("couponId");
        Double totalPrice = clearJSON.getDouble("totalPrice");

        //获取OrderDTO，获取studentDTO，获取couponId
        OrderBuyCourseDTO orderDTO = getOrderBO.getCourseOrder(orderId);
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        CouponDTO couponDTO = couponInfoBO.getCouponDTO(couponId);

        //使用优惠券
        Double price = useCouponBO.userCoupon(studentDTO, orderDTO, couponDTO);
        if ( price == null || !price.equals(totalPrice) ){
            // 优惠券非法
            jsonObject.put("result", "fail");
            jsonObject.put("reason", "coupon");
            jsonHandler.sendJSON(jsonObject, response);
            return;
        }

        //扣款，改变订单状态，更新用户信息
        Boolean payFlag = payForOrderBO.pay(studentDTO, orderDTO, couponDTO, price);
        if ( !payFlag ){ // 扣款失败
            jsonObject.put("reason", "pay");
            jsonHandler.sendJSON(jsonObject, response);
            throw new Exception("优惠券信息不符");
        }
        updateOrderBO.updateOrderSateAfterPay(orderDTO);
        studentInfoBO.updateStudent(studentDTO);

        //反馈给客户端
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);

        //生成消息与申请，发送

        TeacherDTO teacherDTO = teacherInfoBO.getTeacherDTO(orderDTO.getTeacherId());
//        CourseTeacherDTO courseDTO =

        //生成申请、消息
        MessageOrderDTO message = messageOrderBO.saveOrderMessage(teacherDTO.getId(), orderDTO.getId());
        ApplicationTeacherCourseDTO application = applicationBO.createApplicationForTeacherCourse(
                studentDTO.getId(), orderDTO.getId(), message.getId(), teacherDTO.getId());
        applicationBO.save(application);
        //给老师发送申请、消息
    }
}
