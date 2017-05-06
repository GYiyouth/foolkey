package foolkey.controller.course.CourseTeacher;

import foolkey.controller.AbstractController;
import foolkey.handler.application.AcceptTeacherCourseApplicationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 接受学生对老师课程的申请
 * Created by geyao on 2017/5/4.
 */
@Controller
@RequestMapping("/aes/acceptTeacherCourseApplication")
public class AcceptTeacherCourseApplicationController extends AbstractController{

    @Autowired
    private AcceptTeacherCourseApplicationHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        handler.execute(request, response, jsonObject);
    }
}
