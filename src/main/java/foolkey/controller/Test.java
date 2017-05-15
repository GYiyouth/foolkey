package foolkey.controller;

import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.tool.JSONHandler;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by GR on 2017/5/13.
 */
@RequestMapping("/test")
@Controller
public class Test {

    @Autowired
    private OrderInfoBO orderInfoBO;

//    @RequestMapping
//    public void execute(HttpServletResponse response){
//        JSONHandler jsonHandler = new JSONHandler();
//        JSONObject jsonObject = new JSONObject();
//        Long time1 = System.currentTimeMillis();
//        List list = orderInfoBO.test();
//        jsonObject.put("1", orderInfoBO.test());
//        jsonHandler.sendJSON(jsonObject, response);
//    }
}
