package foolkey.pojo.root.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.order_course.GetOrderBO;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 获取做为学生身份拥有的订单
 * aes加密，需要传输token，以及订单类型
 * Created by geyao on 2017/5/2.
 */
@Service
@Transactional(readOnly = true)
public class GetStudentOrderHandler extends AbstractBO{

    @Autowired
    private GetOrderBO getOrderBO;
    @Autowired
    private StudentInfoBO studentInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        String orderStateStr = clearJSON.getString("orderState");

        OrderStateEnum orderState = OrderStateEnum.valueOf(orderStateStr);

        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);

        List<OrderBuyCourseDTO> list = getOrderBO.getCourseOrderAsStudent(studentDTO.getId(), orderState);
        jsonObject.put("orderList", list);
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);
    }
}
