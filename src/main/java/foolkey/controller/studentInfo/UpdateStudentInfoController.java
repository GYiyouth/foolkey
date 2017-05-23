package foolkey.controller.studentInfo;

import foolkey.controller.AbstractController;
import foolkey.handler.student.UpdateStudentInfoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GR on 2017/5/23.
 */
@Controller
@RequestMapping(value = "/aes/studentInfo/updateInfo")
public class UpdateStudentInfoController extends AbstractController {

    @Autowired
    private UpdateStudentInfoHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        handler.execute(request, response, jsonObject);
    }
}

