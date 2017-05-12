package foolkey.controller.order;

import foolkey.controller.AbstractController;
import foolkey.handler.order.GetOrderBuyCourseInfoByOrderIdHandler;
import foolkey.handler.order.GetOrderBuyRewardInfoByOrderIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GR on 2017/5/12.
 */
@Controller
@RequestMapping(value = "/order/getOrderBuyRewardInfoByOrderIdController")
public class GetOrderBuyRewardInfoByOrderIdController  extends AbstractController {

    @Autowired
    private GetOrderBuyRewardInfoByOrderIdHandler getOrderBuyRewardInfoByOrderIdHandler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        getOrderBuyRewardInfoByOrderIdHandler.execute(request,response,jsonObject);
    }

}
