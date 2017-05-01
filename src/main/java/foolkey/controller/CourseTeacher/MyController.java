package foolkey.controller.CourseTeacher;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import org.apache.http.HttpResponse;
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
public class MyController extends AbstractController{

    @Resource(name = "courseTeacherBO")
    private CourseTeacherBO courseTeacherBO;


    @RequestMapping(value = "/ttt")
    public void execute(
            @RequestParam("id")Long id,
            HttpServletResponse response,
            HttpServletRequest request
    ) throws Exception{
        System.out.println("111111");
//        courseTeacherBO.test(2L);
//        courseTeacherBO.test2();
        jsonObject.put("result","success");
        jsonHandler.sendJSON(jsonObject,response);
    }

}
