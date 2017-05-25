package foolkey.controller.course.Reward;

import foolkey.controller.AbstractController;
import foolkey.handler.reward.DeleteRewardHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GR on 2017/5/25.
 */

@Controller
@RequestMapping("/aes/deleteReward")
public class DeleteController extends AbstractController {
    @Autowired
    private DeleteRewardHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        handler.execute(request, response, jsonObject);
    }
}
