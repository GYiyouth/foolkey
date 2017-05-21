package foolkey.controller.course.Course;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseTeacherStateEnum;
import foolkey.pojo.root.vo.assistObject.CourseTimeDayEnum;
import foolkey.pojo.root.vo.assistObject.TeachMethodEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ustcg on 2017/5/1.
 */
@Controller(value = "CourseUpdate")
@RequestMapping(value = "/aes/courseTeacher/updateCourseTeacher")
public class UpdateController extends AbstractController {

    @Autowired
    private CourseBO courseTeacherBO;

    @Autowired
    private StudentInfoBO studentInfoBO;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
//            @RequestParam("id")Long id,
//            @RequestParam("technicTagEnum")TechnicTagEnum technicTagEnum,
//            @RequestParam("topic")String topic,
//            @RequestParam("description")String description ,
//            @RequestParam("price")Double price,
//            @RequestParam("courseTimeDayEnum")CourseTimeDayEnum courseTimeDayEnum,
//            @RequestParam("teachMethodEnum")TeachMethodEnum teachMethodEnum,
//            @RequestParam("duration")Float duration,
            HttpServletResponse response
    ) throws Exception {
        try {
            //获取-解析JSON明文数据
            String clearText = request.getAttribute("clearText").toString();
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            String token =clearJSON.getString("token");
            Long id = clearJSON.getLong("courseId");
            String technicTagStr = clearJSON.getString("technicTagEnum");
            TechnicTagEnum technicTagEnum = TechnicTagEnum.valueOf(technicTagStr);
            String topic = clearJSON.getString("topic");
            String description = clearJSON.getString("description");
            Double price = clearJSON.getDouble("price");
            String courseTimeDayStr = clearJSON.getString("courseTimeDayEnum");
            CourseTimeDayEnum courseTimeDayEnum = CourseTimeDayEnum.valueOf(courseTimeDayStr);
            String teachMethodStr = clearJSON.getString("teachMethodEnum");
            TeachMethodEnum teachMethodEnum = TeachMethodEnum.valueOf(teachMethodStr);
            Float duration = Float.parseFloat( clearJSON.getString("duration") );

            //根据id获取旧的悬赏信息
            CourseDTO courseTeacherDTO = courseTeacherBO.getCourseTeacherDTOById(id);


            courseTeacherDTO.setTechnicTagEnum(technicTagEnum);
            courseTeacherDTO.setTopic(topic);
            courseTeacherDTO.setDescription(description);
            courseTeacherDTO.setPrice(price);
            courseTeacherDTO.setCourseTimeDayEnum(courseTimeDayEnum);
            courseTeacherDTO.setTeachMethodEnum(teachMethodEnum);
//            courseTeacherDTO.setCourseTeacherStateEnum(CourseTeacherStateEnum.可上课);
            courseTeacherDTO.setDuration(duration);
//            courseTeacherDTO.setSales(0);
//            courseTeacherDTO.setAverageScore(0.0F);
            courseTeacherBO.update(courseTeacherDTO);

            //封装-传送JSON
            jsonObject.put("result","success");
            jsonObject.put("courseTeacherDTO",courseTeacherDTO);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
