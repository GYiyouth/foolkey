package foolkey.controller.order;

import foolkey.controller.AbstractController;
import foolkey.handler.order.GetStudentOrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/2.
 */
@RequestMapping("/getOrderAsStudent")
@Controller
public class GetStudentOrderController extends AbstractController{

    @Autowired
    private GetStudentOrderHandler getStudentOrderHandler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception{
        getStudentOrderHandler.execute(request, response, jsonObject);
    }
}
