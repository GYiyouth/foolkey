package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 取消订单，仅适用于未付款订单，对老师课程、悬赏均适用
 * 需要提交订单号
 * AES加密，将订单的状态改变为cancel、
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class CancelOrderTeacherCourseHandler extends AbstractBO{

    @Autowired
    private OrderInfoBO orderInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        String orderid = clearJSON.getString("orderId");

        OrderBuyCourseDTO order = orderInfoBO.getCourseOrder(orderid);
        if (order.getOrderStateEnum().compareTo(OrderStateEnum.unpay) == 0){
            //仅当处于未付款的时候，可以使用
            order.setOrderStateEnum(OrderStateEnum.cancel);
            orderInfoBO.update(order);
            jsonObject.put("result", "success");
            jsonHandler.sendJSON(jsonObject, response);
            return;
        }
        jsonHandler.sendJSON(jsonObject, response);
    }
}
