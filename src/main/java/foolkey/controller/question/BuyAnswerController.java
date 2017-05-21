package foolkey.controller.question;

import foolkey.controller.AbstractController;
import foolkey.handler.question.BuyAnswerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GR on 2017/5/21.
 */
@Controller
@RequestMapping("/question/buyAnswer")
public class BuyAnswerController extends AbstractController{

    @Autowired
    private BuyAnswerHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        handler.execute(request, response, jsonObject);
    }
}
