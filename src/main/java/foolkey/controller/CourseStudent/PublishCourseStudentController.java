package foolkey.controller.CourseStudent;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.CourseStudent.CourseStudentBO;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.vo.assistObject.*;
import foolkey.pojo.root.vo.dto.CourseStudentDTO;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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

    @RequestMapping(value = "/publishCourseTeacher")
    public void execute(
            @RequestParam("technicTagEnum")TechnicTagEnum technicTagEnum,
            @RequestParam("topic")String topic,
            @RequestParam("description")String description ,
            @RequestParam("price")Double price,
            @RequestParam("courseTimeDayEnum")CourseTimeDayEnum courseTimeDayEnum,
            @RequestParam("teachMethodEnum")TeachMethodEnum teachMethodEnum,
            @RequestParam("teacherRequirementEnum")TeacherRequirementEnum teacherRequirementEnum,
            @RequestParam("studentBaseEnum")StudentBaseEnum studentBaseEnum,
            HttpServletResponse response
    ) throws Exception {
        try {
            System.out.println("========");
            courseStudentDTO.setTechnicTagEnum(technicTagEnum);
            courseStudentDTO.setTopic(topic);
            courseStudentDTO.setDescription(description);
            courseStudentDTO.setPrice(price);
            courseStudentDTO.setCourseTimeDayEnum(courseTimeDayEnum);
            courseStudentDTO.setTeachMethodEnum(teachMethodEnum);
            courseStudentDTO.setTeacherRequirementEnum(teacherRequirementEnum);
            courseStudentDTO.setStudentBaseEnum(studentBaseEnum);
            courseStudentDTO.setCourseStudentStateEnum(CourseStudentStateEnum.待接单);
            courseStudentBO.publishCourseStudent(courseStudentDTO);
            jsonObject.put("result","success");
            jsonObject.put("courseStudentDTO",courseStudentDTO);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
