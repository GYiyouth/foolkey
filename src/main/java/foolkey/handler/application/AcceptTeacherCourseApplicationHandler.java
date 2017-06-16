package foolkey.handler.application;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.order.OrderInfoBO;
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
 * 接受学生对于课程申请
 * aes加密
 * 需要token，orderId
 * 它跟接受悬赏课程的不同在于，前者，就算是同一门课，也可能会有多个order
 * Created by geyao on 2017/5/4.
 */
@Service
@Transactional
public class AcceptTeacherCourseApplicationHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;



    @Autowired
    private OrderInfoBO orderBO;

    @Autowired
    private ApplicationInfoBO applicationInfoBO;


    public void
    execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        Long orderId = clearJSON.getLong("orderId");

        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        OrderBuyCourseDTO orderBuyCourseDTO = orderBO.getCourseOrder(orderId + "");
        StudentDTO applicantDTO = studentInfoBO.getStudentDTO(orderBuyCourseDTO.getUserId());

        //处理各种状态
        orderBuyCourseDTO.setOrderStateEnum(OrderStateEnum.同意上课);
        orderBO.update(orderBuyCourseDTO);

        //返回json数据
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);

        //删除申请
        applicationInfoBO.deleteTeacherCourseApplication(applicantDTO.getId(), orderBuyCourseDTO.getId());

    }
}
