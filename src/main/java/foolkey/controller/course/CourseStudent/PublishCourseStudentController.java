package foolkey.controller.course.CourseStudent;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.CourseStudent.CourseStudentBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.*;
import foolkey.pojo.root.vo.dto.CourseStudentDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.Time;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ustcg on 2017/4/30.
 */
@Controller
@RequestMapping(value = "/courseStudent")
public class PublishCourseStudentController extends AbstractController {

    @Resource(name = "courseStudentBO")
    private CourseStudentBO courseStudentBO;

    @Resource(name = "courseStudentDTO")
    private CourseStudentDTO courseStudentDTO;

    @Autowired
    private StudentInfoBO studentInfoBO;

    @RequestMapping(value = "/publishRewardCourse")
    public void execute(
            HttpServletRequest request,
//            @RequestParam("token") String token,
//            @RequestParam("technicTagEnum") TechnicTagEnum technicTagEnum,
//            @RequestParam("topic") String topic,
//            @RequestParam("description") String description,
//            @RequestParam("price") Double price,
//            @RequestParam("courseTimeDayEnum") CourseTimeDayEnum courseTimeDayEnum,
//            @RequestParam("teachMethodEnum") TeachMethodEnum teachMethodEnum,
//            @RequestParam("teacherRequirementEnum") TeacherRequirementEnum teacherRequirementEnum,
//            @RequestParam("studentBaseEnum") StudentBaseEnum studentBaseEnum,
            HttpServletResponse response
    ) throws Exception {
        try {
            System.out.println("00000");
            // 获取-分析JSON传递的明文信息
            String clearText = request.getParameter("clearText");
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            String token = clearJSON.getString("token");
            String technicTagStr = clearJSON.getString("technicTagEnum");
            TechnicTagEnum technicTagEnum = TechnicTagEnum.valueOf(technicTagStr);
            String topic = clearJSON.getString("topic");
            String description = clearJSON.getString("description");
            Double price = clearJSON.getDouble("price");
            String courseTimeDayStr = clearJSON.getString("courseTimeDayEnum");
            CourseTimeDayEnum courseTimeDayEnum = CourseTimeDayEnum.valueOf(courseTimeDayStr);
            String teachMethodStr = clearJSON.getString("teachMethodEnum");
            TeachMethodEnum teachMethodEnum = TeachMethodEnum.valueOf(teachMethodStr);
            String teacherRequirementStr = clearJSON.getString("teachRequirementEnum");
            TeacherRequirementEnum teacherRequirementEnum = TeacherRequirementEnum.valueOf(teacherRequirementStr);
            String studentBase = clearJSON.getString("studentBaseEnum");
            StudentBaseEnum studentBaseEnum = StudentBaseEnum.valueOf(studentBase);

            // 创建一个curseStudentDTO(悬赏)，并赋值
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
            courseStudentDTO.setCreatorId(studentDTO.getId());

//            courseStudentDTO.setCreatorId(20001L);

            courseStudentDTO.setTechnicTagEnum(technicTagEnum);
            courseStudentDTO.setTopic(topic);
            courseStudentDTO.setDescription(description);
            courseStudentDTO.setPrice(price);
            courseStudentDTO.setCourseTimeDayEnum(courseTimeDayEnum);
            courseStudentDTO.setTeachMethodEnum(teachMethodEnum);
            courseStudentDTO.setTeacherRequirementEnum(teacherRequirementEnum);
            courseStudentDTO.setStudentBaseEnum(studentBaseEnum);
            courseStudentDTO.setCourseStudentStateEnum(CourseStudentStateEnum.待接单);
            courseStudentDTO.setCreateTime(Time.getCurrentDate());

            //发布悬赏
            courseStudentBO.publishCourseStudent(courseStudentDTO);
            jsonObject.put("result", "success");
            jsonObject.put("courseStudentDTO", courseStudentDTO);
            jsonHandler.sendJSON(jsonObject, response);
        } catch (Exception e) {
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
