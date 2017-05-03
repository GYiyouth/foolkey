package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.CourseStudent.CourseStudentBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseStudentStateEnum;
import foolkey.pojo.root.vo.assistObject.RoleEnum;
import foolkey.pojo.root.vo.assistObject.UserStateEnum;
import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import foolkey.pojo.root.vo.dto.CourseStudentDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对于学生悬赏任务下订单，主语要求是老师(RoleEnum != student)
 * 申请以后，会把老师的资料发送学生
 * 需要token, courseId
 *
 * 后续的事情：老师可以撤销（迭代）
 * 学生在处理申请后，需要付款
 * 只够进入到上课、评价的流程
 * Created by geyao on 2017/5/3.
 */
@Service
@Transactional
public class PlaceOrderStudentCourseHandler extends AbstractBO {
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;
    @Autowired
    private CourseStudentBO courseStudentBO;
    @Autowired
    private ApplicationInfoBO applicationInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{

        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        //获取token,courseId
        String token = clearJSON.getString("token");
        Long courseId = clearJSON.getLong("courseId");

        //获取个人信息验证RoleEnum字段
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        if (studentDTO.getRoleEnum().compareTo(RoleEnum.student) == 0)
            jsonHandler.sendJSON(jsonObject, response);
        TeacherDTO teacherDTO = teacherInfoBO.getTeacherDTO(studentDTO.getId());


        //获取课程信息
        CourseStudentDTO courseDTO = courseStudentBO.getCourseStudentDTO( courseId );
        //验证课程状态
        if (courseDTO.getCourseStudentStateEnum().compareTo(CourseStudentStateEnum.已解决) == 0)
            jsonHandler.sendJSON(jsonObject, response);

        //发送给课程创始人
        StudentDTO sendStudentDTO = new StudentDTO();
        sendStudentDTO.myClone(sendStudentDTO, studentDTO);

        //生成申请
        ApplicationStudentRewardDTO application = applicationInfoBO
                .createApplicationForStudentReward(
                        teacherDTO.getId(), // 申请人
                        null,               //messageId
                        null,               //orderId
                        courseDTO.getCreatorId()//处理人
        );

        applicationInfoBO.save(application);


        //发送消息


        //返回
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);
    }
}
