package foolkey.controller.teacher;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.handler.teacher.SwitchToTeacherHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/2.
 */
@Controller
@RequestMapping("/switchToTeacher")
public class SwitchToTeacherController extends AbstractController{
    @Autowired
    private SwitchToTeacherHandler switchToTeacherHandler;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        switchToTeacherHandler.execute(request, response, jsonObject);
    }
}
