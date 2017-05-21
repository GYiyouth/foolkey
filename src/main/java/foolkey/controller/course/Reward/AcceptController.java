package foolkey.controller.course.Reward;

import foolkey.controller.AbstractController;
import foolkey.handler.application.AcceptRewardApplicationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 学生为自己的悬赏课程付款
 * 接收某位老师的申请，删除其他所有申请（但不发送消息）
 * 扣款，生成订单，进入上课的阶段
 * 需要 token，applicationId, couponId, price
 * 返回order
 * Created by geyao on 2017/5/4.
 */
@Controller
@RequestMapping("/aes/acceptRewardApplication")
public class AcceptController extends AbstractController{

    @Autowired
    private AcceptRewardApplicationHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        handler.execute(request, response, jsonObject);
    }
}
