package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.CourseAbstract;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
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
    private OrderInfoBO orderInfoBO;
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;
    @Autowired
    private CourseBO courseBO;
    @Autowired
    private RewardBO rewardBO;

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

        List<OrderBuyCourseDTO> list = orderInfoBO.getCourseOrderAsStudent(studentDTO.getId(), orderState);

        //便利上面的那个列表，获取老师的信息
        List<StudentDTO> t1 = new ArrayList<>();
        List<TeacherDTO> t2 = new ArrayList<>();
        List<CourseAbstract> courseList = new ArrayList<>();

        for (OrderBuyCourseDTO order : list){
            Long tid = order.getTeacherId();
            StudentDTO studentDTO1 = new StudentDTO();
            StudentDTO studentDTO2 = new StudentDTO();
            studentDTO2 = studentInfoBO.getStudentDTO(tid);
            studentDTO1.myClone(studentDTO1, studentDTO2);
            studentDTO1.setPassWord("");

            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO = teacherInfoBO.getTeacherDTO(tid);

            t1.add(studentDTO1);
            t2.add(teacherDTO);
            switch ( order.getCourseTypeEnum() ){
                case 老师课程:{
                    courseList.add( courseBO.getCourseTeacherDTOById(order.getCourseId()) );
                }break;
                case 学生悬赏:{
                    courseList.add( rewardBO.getCourseStudentDTO( order.getCourseId()) );
                }break;
            }
        }

        jsonObject.put("orderList", list);
        jsonObject.put("studentList", t1);
        jsonObject.put("teacherList", t2);
        jsonObject.put("courseList", courseList);
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);
    }
}
