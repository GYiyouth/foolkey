package foolkey.handler.teacher;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.RoleEnum;
import foolkey.pojo.root.vo.assistObject.VerifyStateEnum;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 如果学生用户试图发布课程，或者主动点击【我的-切换教师-申请认证】
 * 就会提示他需要认证, role会变成 alreadyApplied，verified字段变成processing
 * 需要去完成来自【5】个【不同学生】的悬赏【任务】
 * 且中间不能有【3星】以下的评分，如果有，则认证失败, verified变成refused,
 * 如果一路高分评价，则顺利成为老师，他的verifyState 字段，将会变成verified， role会变成 teacher
 * 可以正常进行 发布课程、接受提问、发表文章的功能。
 *
 * 之后通过对于悬赏任务的AOP，来完成认证的功能
 *
 * 明文，需要token
 * Created by geyao on 2017/5/3.
 */
@Service
@Transactional
public class ApplyToVerifyHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        // 获取信息，验证role
        String clearText = request.getParameter("clearText");
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");

        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        TeacherDTO teacherDTO;
        // 如果role为student 新建teacher角色
        if (studentDTO.getRoleEnum().compareTo(RoleEnum.student) == 0){
            teacherDTO = new TeacherDTO();
            teacherDTO.setId( studentDTO.getId() );
            teacherDTO.setFollowerNumber(0);
            teacherDTO.setTeacherAverageScore(0.0F);
            teacherDTO.setTeachingTime(0.0F);
            teacherDTO.setTeachingTime(0.0F);
        }else { // 否则，从数据库获取
            teacherDTO = teacherInfoBO.getTeacherDTO( studentDTO.getId() );
        }

        //verified置为processing，role置为alreadyApplied
        teacherDTO.setVerifyState(VerifyStateEnum.processing);
        studentDTO.setRoleEnum(RoleEnum.alreadyApplied);

        //保存用户信息
        studentInfoBO.updateStudent(studentDTO);
        teacherInfoBO.updateTeacherDTO(teacherDTO);

        //用新的dto，抹去密码，明文传输
        StudentDTO sendStudent = new StudentDTO();
        sendStudent.myClone(sendStudent, studentDTO);
        sendStudent.setPassWord("");

        jsonObject.put("studentDTO", sendStudent);
        jsonObject.put("teacherDTO", teacherDTO);
        jsonObject.put("result", "success");

        jsonHandler.sendJSON(jsonObject, response);
    }
}
