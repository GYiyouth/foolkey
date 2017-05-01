package foolkey.pojo.root.bo.palce_order;

import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 向老师课程下订单的BO
 * 后台逻辑是：验证课程开课状态、验证老师与课程是否匹配、验证老师的账号状态（AOP)
 * 生成订单，生成相应的申请、消息，并发送给老师
 * Created by geyao on 2017/4/30.
 */
@Service(value = "placeOrderTeacherCourseBO")
@Transactional
public class PlaceOrderTeacherCourseBO {

    @Autowired
    private StudentInfoBO studentInfoBO;

    @Autowired
    private TeacherInfoBO teacherInfoBO;

    @Autowired
    private CourseTeacherBO courseBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        // 从明文中获取以下token，课程id，老师id
        String token = clearJSON.getString("token");
        String courseId = clearJSON.getString("courseId");
//        String teacherId = clearJSON.getString("teacherId");

        // 获取学生DTO，课程DTO，老师DTO
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        CourseTeacherDTO courseDTO = null;
        TeacherDTO teacherDTO = teacherInfoBO.getTeacherDTO(courseDTO.getId());


    }
}
