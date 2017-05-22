package foolkey.controller.question;

import foolkey.controller.AbstractController;
import foolkey.handler.question.CreateQuestionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 提问问题
 * 参数：
 * token：用户标识：String
 * answerId：回答者的id：Long
 * price：设置的这个问题的价格：Double
 * title：问题的主题：String
 * questionContent：问题的内容：String
 * couponId：优惠券的id：Long
 * technicTagEnum：问题所属的技术类别：String
 * <p>
 * 返回：
 * result：结果：success、fail(String)
 * questionAnswerDTO：问题的DTO：QuestionAnswerDTO
 * orderAskQuestionDTO：提问的订单DTO：OrderAskQuestionDTO
 * <p>
 * Created by GR on 2017/5/21.
 */
@Controller
@RequestMapping("/question/createQuestion")
public class CreateQuestionController extends AbstractController {

    @Autowired
    private CreateQuestionHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        handler.execute(request, response, jsonObject);
    }

}
