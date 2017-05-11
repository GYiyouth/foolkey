package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 同意退款，只能发生在订单状态在agreed的时候，此时将会退款
 * 需要token，orderId
 * Created by geyao on 2017/5/7.
 */
@Service
@Transactional
public class AgreeRefundHandler extends AbstractBO {


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


    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getParameter("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        Long orderId = clearJSON.getLong("orderId");

        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        OrderBuyCourseDTO orderDTO = orderInfoBO.getCourseOrder(orderId + "");
        StudentDTO student = studentInfoBO.getStudentDTO(orderDTO.getUserId());

        //退款，会更新订单状态，退款，删除优惠券
        orderInfoBO.courseRefund(orderDTO, student);

        jsonObject.put("result", "success");
        jsonHandler.sendJSON( jsonObject, response );

        //发送消息
        messageBO.sendForRefundSuccessToStudent( student );
    }
}
