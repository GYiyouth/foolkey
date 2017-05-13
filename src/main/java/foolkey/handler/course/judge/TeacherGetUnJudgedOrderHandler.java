package foolkey.handler.course.judge;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.OrderBuyCourseAsStudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 老师获取待评价的悬赏、课程
 * 需要token，pageNo
 * Created by 葛尧 on 2017/5/13.
 */
@Service
@Transactional(readOnly = true)
public class TeacherGetUnJudgedOrderHandler extends AbstractBO {
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getParameter("clearText");
        JSONObject clearJSON = JSONObject.fromObject( clearText );

        String token = clearJSON.getString("token");
        StudentDTO studentDTO = studentInfoBO.getStudentDTO( token );
        Integer pageNo = clearJSON.getInt( "pageNo" );
//        StudentDTO studentDTO = studentInfoBO.getStudentDTO( id );

        //获取订单消息
        List<OrderBuyCourseAsStudentDTO> result = orderInfoBO.getOrderToJudgeAsTeacher( studentDTO, pageNo );

        //发送结果
        jsonObject.put("orderList", result);
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);
    }
}
