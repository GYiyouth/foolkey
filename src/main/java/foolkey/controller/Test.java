package foolkey.controller;

import com.alibaba.fastjson.JSON;
import foolkey.pojo.root.bo.order.OrderInfoBO;
import foolkey.pojo.root.vo.assistObject.RoleEnum;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import foolkey.pojo.send_to_client.OrderBuyCourseAsStudentDTO;

import java.util.Date;

/**
 * Created by GR on 2017/5/13.
 */

public class Test {

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

    public static void main(String[] args) throws Exception{
        System.out.println(System.getenv("HOME"));
    }
}
