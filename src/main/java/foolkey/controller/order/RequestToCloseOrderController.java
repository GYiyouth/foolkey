package foolkey.controller.order;

import foolkey.controller.AbstractController;
import foolkey.handler.order.RequestToCloseOrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/7.
 */
@Controller
@RequestMapping("/aes/closeOrder")
public class RequestToCloseOrderController extends AbstractController {

    @Autowired
    private RequestToCloseOrderHandler handler;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        handler.execute(request, response, jsonObject);
    }
}
