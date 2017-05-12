package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拒绝学生的退款申请，这个只能发生在orderState在 agreed状态时
 * aes加密
 * 需要token， orderId
 * Created by geyao on 2017/5/7.
 */
@Service
@Transactional
public class RejectRefundHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private MessageBO messageBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        Long orderId = clearJSON.getLong("orderId");

        StudentDTO teacher = studentInfoBO.getStudentDTO(token);
        OrderBuyCourseDTO orderDTO = orderInfoBO.getCourseOrder(orderId + "");
        StudentDTO student = studentInfoBO.getStudentDTO(orderDTO.getUserId());

        //修改订单状态
        orderDTO.setOrderStateEnum(OrderStateEnum.同意上课);
        orderInfoBO.update( orderDTO );

        //返回消息
        jsonObject.put("result", "success");
        jsonHandler.sendJSON( jsonObject, response );

        //给学生发送消息
        messageBO.sendForRefundFailedToStudent( student );
    }
}
