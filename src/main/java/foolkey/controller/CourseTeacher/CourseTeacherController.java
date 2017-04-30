package foolkey.controller.CourseTeacher;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by admin on 2017/4/28.
 */
@Controller
@RequestMapping(value = "/course")
public class CourseTeacherController extends AbstractController{

    @Resource(name = "courseTeacherBO")
    private CourseTeacherBO courseTeacherBO;

    @RequestMapping(value = "/getCourseTeacherPopular")
    public void execute(
            @RequestParam("pageNo") Integer pageNo,
            @RequestParam("technicTagEnum")TechnicTagEnum technicTagEnum,
            HttpServletResponse response
            ){
        try {
            ArrayList<CourseTeacherDTO> courseTeacherDTOArrayList = courseTeacherBO.getPopularCourseTeacher(technicTagEnum, pageNo, 2);
            for (CourseTeacherDTO courseTeacherDTO : courseTeacherDTOArrayList) {
                System.out.println("------" + courseTeacherDTO + "--id:" + courseTeacherDTO.getId());
            }
            jsonObject.put("result","success");
            jsonObject.put("courseTeacherDTOS",courseTeacherDTOArrayList);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}