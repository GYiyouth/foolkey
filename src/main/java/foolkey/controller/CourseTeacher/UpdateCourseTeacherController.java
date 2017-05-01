package foolkey.controller.CourseTeacher;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.vo.assistObject.CourseTeacherStateEnum;
import foolkey.pojo.root.vo.assistObject.CourseTimeDayEnum;
import foolkey.pojo.root.vo.assistObject.TeachMethodEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ustcg on 2017/5/1.
 */
@Controller
@RequestMapping(value = "/courseTeacher")
public class UpdateCourseTeacherController extends AbstractController {

    @Resource(name = "courseTeacherBO")
    private CourseTeacherBO courseTeacherBO;

    @Resource(name = "courseTeacherDTO")
    private CourseTeacherDTO courseTeacherDTO;

    @RequestMapping(value = "/updateCourseTeacher")
    public void execute(
            @RequestParam("id")Long id,
            @RequestParam("technicTagEnum")TechnicTagEnum technicTagEnum,
            @RequestParam("topic")String topic,
            @RequestParam("description")String description ,
            @RequestParam("price")Double price,
            @RequestParam("courseTimeDayEnum")CourseTimeDayEnum courseTimeDayEnum,
            @RequestParam("teachMethodEnum")TeachMethodEnum teachMethodEnum,
            @RequestParam("duration")Double duration,
            @RequestParam("classAmount")Integer classAmount,
            HttpServletResponse response
    ) throws Exception {
        try {
            System.out.println("修改课程信息");
            courseTeacherDTO.setId(id);
            courseTeacherDTO.setTechnicTagEnum(technicTagEnum);
            courseTeacherDTO.setTopic(topic);
            courseTeacherDTO.setDescription(description);
            courseTeacherDTO.setPrice(price);
            courseTeacherDTO.setCourseTimeDayEnum(courseTimeDayEnum);
            courseTeacherDTO.setTeachMethodEnum(teachMethodEnum);
            courseTeacherDTO.setCourseTeacherStateEnum(CourseTeacherStateEnum.可上课);
            courseTeacherDTO.setDuration(duration);
            courseTeacherDTO.setClassAmount(classAmount);
            courseTeacherDTO.setSales(0);
            courseTeacherDTO.setAverageScore(0.0);
            courseTeacherBO.updateCourseTeacherCache(courseTeacherDTO);
            courseTeacherBO.updateCourseTeacherDTO(courseTeacherDTO);
            jsonObject.put("result","success");
            jsonObject.put("courseTeacherDTO",courseTeacherDTO);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
