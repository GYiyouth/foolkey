package foolkey.controller.studentInfo;

import foolkey.controller.AbstractController;
import foolkey.handler.student.GetOneTimeSign_uploadHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/22.
 */
@Controller
@RequestMapping("/upload/getSign")
public class GetOneTimeSign_uploadController extends AbstractController{

    @Autowired
    private GetOneTimeSign_uploadHandler handler;
    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        handler.execute(
                request,
                response,
                jsonObject
        );
    }
}
