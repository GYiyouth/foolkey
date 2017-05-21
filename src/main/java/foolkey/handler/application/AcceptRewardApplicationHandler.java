package foolkey.handler.application;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.coupon.CouponInfoBO;
import foolkey.pojo.root.bo.coupon.UseCouponBO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.pay_order.PayBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.RewardStateEnum;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

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
    @Autowired
    private MessageBO messageBO;
    @Autowired
    private RewardBO rewardBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        //从json里获取原始数据
        String token = clearJSON.getString("token");

        Long applicationId = clearJSON.getLong("applicationId");
//        Long orderId = clearJSON.getLong("orderId");
//        Long rewardId = clearJSON.getLong("rewardId");
        String couponStr = clearJSON.get("couponId").toString();
        Long couponId = null;
        CouponDTO couponDTO = null;
        if ( couponStr != null && !couponStr.equals("")) {
            couponId = Long.parseLong(couponStr);
            couponDTO = couponInfoBO.getCouponDTO(couponId + "");
        }else {

        }
        Double price = clearJSON.getDouble("price");

        //获取各种DTO
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        ApplicationStudentRewardDTO applicationDTO = applicationInfoBO.getRewardApplicationDTO(applicationId);
        Long rewardId = applicationDTO.getRewardId();
        RewardDTO rewardDTO = courseBO.getCourseStudentDTO( rewardId );

        //新建Order
        OrderBuyCourseDTO orderDTO = new OrderBuyCourseDTO();
        orderDTO.setUserId( studentDTO.getId() );
        orderDTO.setCourseId( rewardId );
        orderDTO.setAmount( rewardDTO.getPrice() );
        orderDTO.setCreatedTime( new Date() );

        orderDTO.setPayTime( new Date() );
        orderDTO.setCouponId( couponId );
        orderDTO.setCourseTypeEnum( CourseTypeEnum.学生悬赏 );
        orderDTO.setCutOffPercent( 1.0 );
        orderDTO.setNumber( 1.0 );
        orderDTO.setTeachMethodEnum( rewardDTO.getTeachMethodEnum() );
        orderDTO.setTeacherId( applicationDTO.getApplicantId() );

        orderInfoBO.save( orderDTO );


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

        orderDTO.setExistingTime( new Date() );
        orderDTO.setOrderStateEnum( OrderStateEnum.已付款);

        //课程状态更改
        rewardDTO.setRewardStateEnum(RewardStateEnum.已解决);
        courseBO.update(rewardDTO);

        //订单状态更改
        orderDTO.setOrderStateEnum(OrderStateEnum.同意上课);
        orderInfoBO.update(orderDTO);

        //个人扣款
        studentInfoBO.updateStudent(studentDTO);

        //删除其他人的申请
        applicationInfoBO.deleteAllApplicationByOrderId(orderDTO.getId(), CourseTypeEnum.学生悬赏);


        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);

        //发送消息
        StudentDTO teacher = studentInfoBO.getStudentDTO( applicationDTO.getApplicantId() );
        messageBO.sendForPayReward(studentDTO, teacher, rewardDTO);

        //缓存中删除这个热门悬赏
        rewardBO.deleteRewardFromCache(rewardDTO);
    }
}
