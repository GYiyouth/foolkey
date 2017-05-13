package foolkey.handler.course.haveClass;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.CourseAbstract;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
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
    @Autowired
    private MessageBO messageBO;
    @Autowired
    private CourseBO courseBO;
    @Autowired
    private RewardBO rewardBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getParameter("clearText").toString();
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
        if (orderDTO.getOrderStateEnum().compareTo(OrderStateEnum.同意上课) != 0
                || orderDTO.getOrderStateEnum().compareTo(OrderStateEnum.上课中) !=0 ){
            jsonHandler.sendFailJSON(response);
            return;
        }
        orderDTO.setOrderStateEnum(OrderStateEnum.上课中);
        orderInfoBO.update(orderDTO);

        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);

        //发送消息
        CourseAbstract courseAbstract = null;
        StudentDTO student = studentInfoBO.getStudentDTO(studentId);
        switch ( orderDTO.getCourseTypeEnum() ){
            case 老师课程:{
                courseAbstract = courseBO.getCourseTeacherDTOById( orderDTO.getCourseId() );
            }break;
            case 学生悬赏:{
                courseAbstract = rewardBO.getCourseStudentDTO( orderDTO.getCourseId() );
            }break;
        }

        messageBO.sendForStartClass( student, courseAbstract );
    }
}
