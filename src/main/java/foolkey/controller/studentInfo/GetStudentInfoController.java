package foolkey.controller.studentInfo;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.student.GetStudentInfoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/2.
 */
@RequestMapping("/aes/getMyInfo")
@Controller
public class GetStudentInfoController extends AbstractController {
    @Autowired
    private GetStudentInfoHandler getStudentInfoHandler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        getStudentInfoHandler.execute(request, response, jsonObject);
    }
}
