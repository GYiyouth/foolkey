package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.CourseStudent.CourseStudentBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.*;
import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import foolkey.pojo.root.vo.dto.CourseStudentDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import foolkey.tool.push_message.MessagePusher;
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
 * 之后进入到上课、评价的流程
 * Created by geyao on 2017/5/3.
 */
@Service
@Transactional
public class ApplyStudentCourseHandler extends AbstractBO {
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;
    @Autowired
    private CourseStudentBO courseStudentBO;
    @Autowired
    private ApplicationInfoBO applicationInfoBO;
    @Autowired
    private MessageBO messageBO;

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

        //获取个人信息
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        TeacherDTO teacherDTO = teacherInfoBO.getTeacherDTO(studentDTO.getId());
        //获取课程信息
        CourseStudentDTO courseDTO = courseStudentBO.getCourseStudentDTO( courseId );

        //验证资格
        //学生、认证失败的老师也不能接单
        if (studentDTO.getRoleEnum().compareTo(RoleEnum.student) == 0
                || teacherDTO.getVerifyState().compareTo(VerifyStateEnum.refused) == 0                )
            jsonHandler.sendJSON(jsonObject, response);
        //如果学生要求仅认证老师
        if (courseDTO.getTeacherRequirementEnum().compareTo( TeacherRequirementEnum.认证老师) == 0
                &&  (studentDTO.getRoleEnum().compareTo( RoleEnum.teacher) != 0
                    || teacherDTO.getVerifyState().compareTo(VerifyStateEnum.verified) != 0
                    )
                ){
            //那么未认证老师无法接单
            jsonHandler.sendFailJSON(response);
        }

        //验证课程状态
        if (courseDTO.getCourseStudentStateEnum().compareTo(CourseStudentStateEnum.已解决) == 0)
            jsonHandler.sendJSON(jsonObject, response);

        //如果已经申请了，则不应再申请，交由AOP来做


        //发送给课程创始人
        StudentDTO sendStudentDTO = new StudentDTO();
        sendStudentDTO.myClone(sendStudentDTO, studentDTO);

        ApplicationStudentRewardDTO application = applicationInfoBO
                .createApplicationForStudentReward(
                        teacherDTO.getId(), // 申请人
                        courseId,               //courseId
                        null,               //messageId
                        courseDTO.getCreatorId()//处理人
        );
        //这里会被AOP，如果已经有了，则会返回null
        ApplicationStudentRewardDTO applicationStudentRewardDTO =
                applicationInfoBO.save(application);

        if(applicationStudentRewardDTO != null) {
            messageBO.sendForApplication(application, studentDTO, courseDTO);
            //返回正常的结果
            jsonObject.put("result", "success");
        }else {
            //已经有了重复的申请
            jsonObject.put("result", "wrong");
        }


        jsonHandler.sendJSON(jsonObject, response);
    }
}
