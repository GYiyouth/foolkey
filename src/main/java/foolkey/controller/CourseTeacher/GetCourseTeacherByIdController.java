package foolkey.controller.CourseTeacher;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
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
@RequestMapping(value = "/course")
public class GetCourseTeacherByIdController extends AbstractController {

    @Resource(name = "courseTeacherBO")
    private CourseTeacherBO courseTeacherBO;

    @RequestMapping(value = "/getCourseTeacherById")
    public void execute(
            @RequestParam("id")Long id,
            HttpServletResponse response
    ){
        try {
            CourseTeacherDTO courseTeacherDTO = courseTeacherBO.getCourseTeacherDTOById(id);
            jsonObject.put("result","success");
            jsonObject.put("courseTeacherDTO",courseTeacherDTO);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
