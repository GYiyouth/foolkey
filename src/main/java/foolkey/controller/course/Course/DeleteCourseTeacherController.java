package foolkey.controller.course.Course;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 删除课程
 * token courseId
 * Created by geyao on 2017/5/2.
 */
@Controller
@RequestMapping(value = "/aes/course/delete")
public class DeleteCourseTeacherController extends AbstractController {

    @Autowired
    private CourseBO courseTeacherBO;
    @Autowired
    private StudentInfoBO studentInfoBO;
    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        try {
            String clearText = request.getAttribute("clearText").toString();
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            String token = clearJSON.getString("token");
            Long courseId = clearJSON.getLong("courseId");

            CourseDTO courseTeacherDTO = courseTeacherBO.getCourseTeacherDTOById(courseId);
            StudentDTO studentDTO = studentInfoBO.getStudentDTO( token );

            if ( !courseTeacherDTO.getCreatorId().equals( studentDTO.getId() )){
                //如果这个课程的主人并非这个人
                jsonObject.put("reason", "You are not owner of this course!");
                jsonHandler.sendJSON(jsonObject, response);
                return;
            }

            courseTeacherBO.delete(courseTeacherDTO);

            jsonObject.put("result","success");
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
