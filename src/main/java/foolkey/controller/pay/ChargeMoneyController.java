package foolkey.controller.pay;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.recharge.ChargeMoneyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 充值
 * Created by geyao on 2017/5/1.
 */
@Controller
@RequestMapping("/aes/recharge")
public class ChargeMoneyController extends AbstractController{

    @Autowired
    private ChargeMoneyHandler chargeMoneyHandler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception{
        chargeMoneyHandler.execute(request, response, jsonObject);
    }
}
