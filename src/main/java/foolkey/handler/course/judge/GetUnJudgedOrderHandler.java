package foolkey.handler.course.judge;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.OrderBuyCourseAsStudentDTO;
import net.sf.json.JSONObject;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取未评价的课程订单，作为学生
 * 需要token， pageNo
 * Created by geyao on 2017/5/12.
 */
@Service
@Transactional(readOnly = true)
public class GetUnJudgedOrderHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
//            Long id,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getParameter("clearText");
        JSONObject clearJSON = JSONObject.fromObject( clearText );

        String token = clearJSON.getString("token");
        StudentDTO studentDTO = studentInfoBO.getStudentDTO( token );
        Integer pageNo = clearJSON.getInt( "pageNo" );
//        StudentDTO studentDTO = studentInfoBO.getStudentDTO( id );

        //获取订单消息
        List<OrderBuyCourseAsStudentDTO> result = orderInfoBO.getOrderBuyCourseToJudge( studentDTO, pageNo );

        //发送结果
        jsonObject.put("orderList", result);
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);

    }
}
