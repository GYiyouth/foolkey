package foolkey.handler.pay;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.CourseStudent.CourseStudentBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.coupon.CouponInfoBO;
import foolkey.pojo.root.bo.coupon.UseCouponBO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.pay_order.PayBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseStudentStateEnum;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.assistObject.TeachMethodEnum;
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
 * 学生为自己的悬赏课程付款
 * 接收某位老师的申请，删除其他所有申请（但不发送消息）
 * 扣款，生成订单，进入上课的阶段
 * 需要 token，applicationId, couponId, price
 * 返回order
 * Created by geyao on 2017/5/6.
 */
@Service
@Transactional
public class PayStudentCourseOrderHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private ApplicationInfoBO applicationInfoBO;
    @Autowired
    private CourseStudentBO courseStudentBO;
    @Autowired
    private CouponInfoBO couponInfoBO;
    @Autowired
    private UseCouponBO useCouponBO;
    @Autowired
    private MessageBO messageBO;
    @Autowired
    private PayBO payBO;
    @Autowired
    private OrderInfoBO orderInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        //获取原始信息
        String token = clearJSON.getString("token");
        Long applicationId = clearJSON.getLong("applicationId");
        Long couponId = clearJSON.getLong("couponId");
        Double price = clearJSON.getDouble("price");


        //获取DTO
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token); // 学生，付款者
        ApplicationStudentRewardDTO applicationDTO = applicationInfoBO.getRewardApplicationDTO(applicationId);
        StudentDTO teacher = studentInfoBO.getStudentDTO(applicationDTO.getApplicantId()); // 老师，申请人
        CouponDTO couponDTO =  (!couponId.equals(0L)) // 如果couponId为空
                ? couponInfoBO.getCouponDTO(couponId) : null;  //则返回null
        CourseStudentDTO courseDTO = courseStudentBO.getCourseStudentDTO(applicationDTO.getCourseId());

        //检验优惠券付款
        Double expectedPrice = useCouponBO.userCouponForReward(studentDTO, courseDTO, couponDTO);
        //检验未通过的话，则中止
        if (expectedPrice == null || !expectedPrice.equals(price)){
            jsonObject.put("result", "coupon");
            jsonHandler.sendJSON(jsonObject, response);
            return;
        }
        //付款，更新用户信息
        if ( payBO.payForReward(studentDTO, courseDTO, couponDTO) ){
            studentInfoBO.updateStudent(studentDTO);
        }else {
            jsonObject.put("reason", "money is not enough");
            throw new Exception("余额不足");
        }

        //课程的状态改为 已解决，
        courseDTO.setCourseStudentStateEnum(CourseStudentStateEnum.已解决);
        courseStudentBO.update(courseDTO);
        //生成订单，orderState设置为 agreed
        OrderBuyCourseDTO order =
            orderInfoBO.createOrder(
                    expectedPrice,
                    1.0, // 购买节数，0.5，1h这样子
                    studentDTO, // 购买学生
                    teacher.getId(), // 相关老师
                    couponId, // 课程id
                    1.0, // 折扣
                    courseDTO.getTeachMethodEnum(), // 授课方法
                    CourseTypeEnum.学生悬赏 // 课程种类
            );
        order.setOrderStateEnum(OrderStateEnum.agreed);
        order.setCouponId(couponId);
        order.setPayTime(new Date());

        orderInfoBO.save(order);

        //返回result
        jsonObject.put("result", "success");
        jsonObject.put("order", order);
        jsonHandler.sendJSON(jsonObject, response);

        //发送消息给老师
        messageBO.sendForPayReward(studentDTO, teacher, courseDTO);

        //删除其他申请
        applicationInfoBO.deleteAllApplicationByOrderId(order.getId(), CourseTypeEnum.学生悬赏);
    }
}
