package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.course.CourseBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.coupon.CouponInfoBO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.order.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 申请关闭交易，aes加密
 * 由学生提出，订单状态改为 申请退款
 * 需要token，orderId
 * 如果此时订单还没有被老师查看同意，则立刻退款
 * 否则需要老师申请
 * Created by geyao on 2017/5/7.
 */
@Service
@Transactional
public class RequestToCloseOrderHandler extends AbstractBO {
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private MessageBO messageBO;
    @Autowired
    private CourseBO courseTeacherBO;
    @Autowired
    private RewardBO courseStudentBO;
    @Autowired
    private ApplicationInfoBO applicationInfoBO;
    @Autowired
    private CouponInfoBO couponInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        Long orderId = clearJSON.getLong("orderId");

        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        OrderBuyCourseDTO orderDTO = orderInfoBO.getCourseOrder(orderId + "");
        StudentDTO teacherDTO = studentInfoBO.getStudentDTO(orderDTO.getTeacherId());


        // 验证订单状态
        if (orderDTO.getUserId().equals( studentDTO.getId() ))
            jsonHandler.sendFailJSON(response);
        //如果此时订单还没有被老师查看同意，则立刻退款
        if (orderDTO.getOrderStateEnum().compareTo( OrderStateEnum.已付款) == 0){
            //退款，同时删除优惠券
            orderInfoBO.courseRefund(orderDTO, studentDTO);
            //发送
            jsonObject.put("result", "success");
            jsonHandler.sendJSON(jsonObject, response);
            return;
        }

        //否则需要老师申请，此时需要订单状态在agreed
        if (orderDTO.getOrderStateEnum().compareTo( OrderStateEnum.同意上课) == 0) {
            orderDTO.setOrderStateEnum(OrderStateEnum.申请退款);
            orderInfoBO.update(orderDTO);

            jsonObject.put("result", "success");
            jsonHandler.sendJSON(jsonObject, response);
            // 向老师发送消息
            CourseDTO courseTeacherDTO = courseTeacherBO.getCourseTeacherDTOById(orderDTO.getCourseId());
            String name;
            if (courseTeacherDTO != null)
                name = courseTeacherDTO.getTopic();
            else {
                RewardDTO courseStudentDTO = courseStudentBO.getCourseStudentDTO(orderDTO.getCourseId());
                name = courseStudentDTO.getTopic();
            }
            messageBO.sendForRefundToTeacher(studentDTO, teacherDTO, name);
        }else
            jsonHandler.sendFailJSON(response);
    }
}
