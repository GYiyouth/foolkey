package foolkey.controller.course.judge;

import foolkey.controller.AbstractController;
import foolkey.handler.course.judge.GetUnJudgedOrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/12.
 */
@Controller
@RequestMapping("/获取未评价的订单")
public class GetUnJudgedOrderController extends AbstractController{

    @Autowired
    private GetUnJudgedOrderHandler handler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
//            @RequestParam Long id
    )throws Exception{
        handler.execute(request, response, jsonObject);
    }
}
