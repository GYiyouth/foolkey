package foolkey.handler.application;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.coupon.CouponInfoBO;
import foolkey.pojo.root.bo.coupon.UseCouponBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.pay_order.PayBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseStudentStateEnum;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.CouponDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * aes加密
 * 需要token，applicantId（申请人），order, couponId, price
 * 必须付款才算同意，同意后会删除其他所有人的申请
 * Created by geyao on 2017/5/4.
 */
@Service
@Transactional
public class AcceptRewardApplicationHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;

    @Autowired
    private RewardBO courseBO;

    @Autowired
    private CouponInfoBO couponInfoBO;

    @Autowired
    private UseCouponBO useCouponBO;

    @Autowired
    private OrderInfoBO orderInfoBO;

    @Autowired
    private PayBO payBO;

    @Autowired
    private ApplicationInfoBO applicationInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        //从json里获取原始数据
        String token = clearJSON.getString("token");
        Long applicantId = clearJSON.getLong("applicantId");
        Long orderId = clearJSON.getLong("orderId");
        Long couponId = clearJSON.getLong("couponId");
        Double price = clearJSON.getDouble("price");

        //获取各种DTO
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        StudentDTO applicantDTO = studentInfoBO.getStudentDTO(applicantId);
        CouponDTO couponDTO = couponInfoBO.getCouponDTO(couponId + "");
        OrderBuyCourseDTO orderDTO = orderInfoBO.getCourseOrder(orderId + "");
        RewardDTO courseDTO = courseBO.getCourseStudentDTO(orderDTO.getCourseId());

        //余额扣款
        Double needToPay = useCouponBO.userCoupon(studentDTO, orderDTO, couponDTO);
        if (!needToPay.equals(price)){
            jsonObject.put("result", "fail");
            jsonObject.put("reason", "price is wrong");
            jsonHandler.sendJSON(jsonObject, response);
            return;
        }

        boolean flag = payBO.pay(studentDTO, orderDTO, couponDTO, needToPay);
        if (!flag){
            //付款出错
            jsonObject.put("result", "fail");
            jsonObject.put("reason", "money is not enough");
            jsonHandler.sendJSON(jsonObject, response);
            return;
        }

        //课程状态更改
        courseDTO.setCourseStudentStateEnum(CourseStudentStateEnum.已解决);
        courseBO.update(courseDTO);

        //订单状态更改
        orderDTO.setOrderStateEnum(OrderStateEnum.payed);
        orderInfoBO.update(orderDTO);

        //个人扣款
        studentDTO.setVirtualCurrency( studentDTO.getVirtualCurrency() - needToPay);
        studentInfoBO.updateStudent(studentDTO);

        //删除其他人的申请
        applicationInfoBO.deleteAllApplicationByOrderId(orderId, CourseTypeEnum.学生悬赏);

        //发送消息
    }
}
