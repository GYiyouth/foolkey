package foolkey.controller.pay;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.palce_order.PayOrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/1.
 */
@Controller
@RequestMapping("aes/payOrder")
public class PayOrderController extends AbstractController{
    @Autowired
    private PayOrderHandler payOrderHandler;

    @RequestMapping
    public void execute(
            HttpServletResponse response,
            HttpServletRequest request
    )throws Exception{
        payOrderHandler.execute(request, response, jsonObject);
    }
}
