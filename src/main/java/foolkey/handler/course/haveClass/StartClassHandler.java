package foolkey.handler.course.haveClass;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 上课，aes加密
 * token，orderId，studentId
 * 计时的功能交给app端，这里只负责修改状态
 * 订单状态改变为onClass
 * Created by geyao on 2017/5/6.
 */
@Service
@Transactional
public class StartClassHandler extends AbstractBO {
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;


    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);
        //获取原始数据
        String token = clearJSON.getString("token");
        Long orderId = clearJSON.getLong("orderId");
        Long studentId = clearJSON.getLong("studentId");

        //获取各种DTO
//        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
//        StudentDTO student = studentInfoBO.getStudentDTO(studentId);
        OrderBuyCourseDTO orderDTO = orderInfoBO.getCourseOrder(orderId + "");

        //修改订单状态
        orderDTO.setOrderStateEnum(OrderStateEnum.onClass);
        orderInfoBO.update(orderDTO);

        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);
    }
}
