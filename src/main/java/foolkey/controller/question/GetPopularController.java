package foolkey.controller.question;

import foolkey.controller.AbstractController;
import foolkey.handler.question.GetPopularHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 按页获取问答
 * <p>
 * 参数：
 * token：个人标识token
 * pageNO：页码
 * technicTagEnum：技术类别
 * <p>
 * 返回：
 * result：结果：success、fail
 * questionAskerAnswerSTCDTOS：封装问题-提问者-回答者DTO的list：List<QuestionAskerAnswerSTCDTO>
 * isHavePermissionToViewS：每个问题对于当前查看人来说，是否可以围观，即是否已经交钱围观：List<Boolean>
 * <p>
 * Created by GR on 2017/5/21.
 */
@Controller
@RequestMapping("/question/getPopular")
public class GetPopularController extends AbstractController {

    @Autowired
    private GetPopularHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        handler.execute(request, response, jsonObject);
    }
}