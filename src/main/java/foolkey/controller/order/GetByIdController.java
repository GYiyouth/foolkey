package foolkey.controller.order;

import foolkey.controller.AbstractController;
import foolkey.handler.order.GetOrderBuyCourseInfoByOrderIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GR on 2017/5/12.
 */
@Controller(value = "OrderGetByIdController")
@RequestMapping(value = "/aes/order/getById")
public class GetByIdController extends AbstractController{

    @Autowired
    private GetOrderBuyCourseInfoByOrderIdHandler getOrderBuyCourseInfoByOrderIdHandler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        getOrderBuyCourseInfoByOrderIdHandler.execute(request,response,jsonObject);
    }

}
