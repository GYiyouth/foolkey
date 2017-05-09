package foolkey.controller.course.Course;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.vo.dto.CourseDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ustcg on 2017/5/2.
 */
//@Controller
//@RequestMapping(value = "/courseTeacher")
public class DeleteCourseTeacherController extends AbstractController {

    @Autowired
    private CourseBO courseTeacherBO;

    @Autowired
    private CourseDTO courseDTO;

    @RequestMapping(value = "/deleteCourseTeacher")
    public void execute(
            HttpServletRequest request,
//            @RequestParam("id")Long id,
            HttpServletResponse response
    ) throws Exception {
        try {
            String clearText = request.getParameter("clearText");
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            Long id = clearJSON.getLong("id");

            System.out.println("删除程信息");
            CourseDTO courseTeacherDTO = courseTeacherBO.getCourseTeacherDTOById(id);
            courseTeacherDTO.setId(id);
            courseTeacherBO.updateCourseTeacherCache(courseTeacherDTO);
            courseTeacherBO.updateCourseTeacherDTO(courseTeacherDTO);
            jsonObject.put("result","success");
            jsonObject.put("courseDTO",courseTeacherDTO);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
