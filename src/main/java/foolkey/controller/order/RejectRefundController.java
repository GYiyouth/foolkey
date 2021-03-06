package foolkey.controller.order;

import foolkey.controller.AbstractController;
import foolkey.handler.order.RejectRefundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/7.
 */
@Controller
@RequestMapping("/aes/rejectRefund")
public class RejectRefundController extends AbstractController {
    @Autowired
    private RejectRefundHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        handler.execute(request, response, jsonObject);
    }
}
