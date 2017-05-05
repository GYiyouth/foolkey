package foolkey.controller.CourseTeacher;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.*;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
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
@RequestMapping(value = "/courseTeacher")
public class PublishCourseTeacherController extends AbstractController{

    @Resource(name = "courseTeacherBO")
    private CourseTeacherBO courseTeacherBO;

    @Autowired
    private StudentInfoBO studentInfoBO;

    @Resource(name = "courseTeacherDTO")
    private CourseTeacherDTO courseTeacherDTO;

    @RequestMapping(value = "/publishCourseTeacher")
    public void execute(
            HttpServletRequest request,
//            @RequestParam("technicTagEnum")TechnicTagEnum technicTagEnum,
//            @RequestParam("token")String token,
//            @RequestParam("topic")String topic,
//            @RequestParam("description")String description ,
//            @RequestParam("price")Double price,
//            @RequestParam("courseTimeDayEnum")CourseTimeDayEnum courseTimeDayEnum,
//            @RequestParam("teachMethodEnum")TeachMethodEnum teachMethodEnum,
//            @RequestParam("duration")Double duration,
//            @RequestParam("classAmount")Integer classAmount,
            HttpServletResponse response
    ) throws Exception {
        try {
            String clearText = request.getParameter("clearText");
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            String token =clearJSON.getString("token");
            String technicTagStr = clearJSON.getString("technicTagEnum");
                TechnicTagEnum technicTagEnum = TechnicTagEnum.valueOf(technicTagStr);
            String topic = clearJSON.getString("topic");
            String description = clearJSON.getString("description");
            Double price = clearJSON.getDouble("price");
            String courseTimeDayStr = clearJSON.getString("courseTimeDayEnum");
                CourseTimeDayEnum courseTimeDayEnum = CourseTimeDayEnum.valueOf(courseTimeDayStr);
            String teachMethodStr = clearJSON.getString("teachMethodEnum");
                TeachMethodEnum teachMethodEnum = TeachMethodEnum.valueOf(teachMethodStr);
            Double duration = clearJSON.getDouble("duration");
            Integer classAmount = clearJSON.getInt("classAmount");

            courseTeacherDTO.setCreatorId(studentInfoBO.getStudentDTO(token).getId());
            courseTeacherDTO.setTechnicTagEnum(technicTagEnum);
            courseTeacherDTO.setTopic(topic);
            courseTeacherDTO.setDescription(description);
            courseTeacherDTO.setPrice(price);
            courseTeacherDTO.setCourseTimeDayEnum(courseTimeDayEnum);
            courseTeacherDTO.setTeachMethodEnum(teachMethodEnum);
            courseTeacherDTO.setCourseTeacherStateEnum(CourseTeacherStateEnum.可上课);
            courseTeacherDTO.setDuration(duration);
//            courseTeacherDTO.setCl
            courseTeacherDTO.setSales(0);
            courseTeacherDTO.setAverageScore(0.0);
            courseTeacherBO.publishCourseTeacher(courseTeacherDTO);
            jsonObject.put("result","success");
            jsonObject.put("courseTeacherDTO",courseTeacherDTO);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
