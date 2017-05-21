package foolkey.controller.course.judge;

import foolkey.controller.AbstractController;
import foolkey.handler.course.judge.EvaluateCourseHandler;
import foolkey.handler.course.judge.EvaluateStudentHandler;
import foolkey.handler.course.judge.EvaluateTeacherHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 给予评价
 * Created by geyao on 2017/5/6.
 */
@Controller
@RequestMapping("/aes/judge")
public class JudgeController extends AbstractController{
    @Autowired
    private EvaluateCourseHandler evaluateCourseHandler;
    @Autowired
    private EvaluateStudentHandler evaluateStudentHandler;
    @Autowired
    private EvaluateTeacherHandler evaluateTeacherHandler;

    @RequestMapping("/course")
    public void course(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        evaluateCourseHandler.execute(request, response, jsonObject);
    }

    @RequestMapping("/student")
    public void student(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        evaluateStudentHandler.execute(request, response, jsonObject);
    }





}
