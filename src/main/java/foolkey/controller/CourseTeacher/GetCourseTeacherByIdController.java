package foolkey.controller.CourseTeacher;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ustcg on 2017/5/1.
 */
@Controller
@RequestMapping(value = "/courseTeacher")
public class GetCourseTeacherByIdController extends AbstractController {

    @Resource(name = "courseTeacherBO")
    private CourseTeacherBO courseTeacherBO;

    @RequestMapping(value = "/getCourseTeacherById")
    public void execute(
            HttpServletRequest request,
//            @RequestParam("id")Long id,
            HttpServletResponse response
    ){
        try {
            String clearText = request.getParameter("clearText");
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            Long id = clearJSON.getLong("id");
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
