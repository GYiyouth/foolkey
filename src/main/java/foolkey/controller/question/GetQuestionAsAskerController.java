package foolkey.controller.question;

import foolkey.controller.AbstractController;
import foolkey.handler.question.GetQuestionAsAskerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取我提问的问题（学生身份提问）
 * 参数：
 * token：用户的标识:String
 * pageNo：请求的页码:Integer
 * questionStateEnum：问题的状态（已回答、待回答）:String
 * 返回：
 * questionAskerAnswerSTCDTOS：问题-提问者-回答者封装的DTO：List<QuestionAskerAnswerSTCDTO>
 * Created by GR on 2017/5/21.
 */
@Controller
@RequestMapping("/question/getQuestionAsAsker")
public class GetQuestionAsAskerController extends AbstractController {

    @Autowired
    private GetQuestionAsAskerHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        handler.execute(request, response, jsonObject);
    }
}
