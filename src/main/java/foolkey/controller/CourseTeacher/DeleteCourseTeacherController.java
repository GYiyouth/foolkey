package foolkey.controller.CourseTeacher;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.vo.assistObject.CourseTeacherStateEnum;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by ustcg on 2017/5/2.
 */
@Controller
@RequestMapping(value = "/courseTeacher")
public class DeleteCourseTeacherController extends AbstractController {

    @Autowired
    private CourseTeacherBO courseTeacherBO;

    @Autowired
    private CourseTeacherDTO courseTeacherDTO;

    @RequestMapping(value = "/updateCourseTeacher")
    public void execute(
            @RequestParam("id")Long id,
            HttpServletResponse response
    ) throws Exception {
        try {
            System.out.println("修改课程信息");
            CourseTeacherDTO courseTeacherDTO = courseTeacherBO.getCourseTeacherDTOById(id);
            courseTeacherDTO.setId(id);
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
