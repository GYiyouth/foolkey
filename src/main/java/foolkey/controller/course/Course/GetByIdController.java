package foolkey.controller.course.Course;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.vo.dto.CourseDTO;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ustcg on 2017/5/1.
 */
@Controller
@RequestMapping(value = "/course/getById")
public class GetByIdController extends AbstractController {

    @Resource(name = "courseTeacherBO")
    private CourseBO courseTeacherBO;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
//            @RequestParam("id")Long id,
            HttpServletResponse response
    ){
        try {
            String clearText = request.getParameter("clearText");
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            Long id = clearJSON.getLong("courseId");
            CourseDTO courseTeacherDTO = courseTeacherBO.getCourseTeacherDTOById(id);
            jsonObject.put("result","success");
            jsonObject.put("courseTeacherDTO",courseTeacherDTO);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
