package foolkey.pojo.root.bo.order_course;

import foolkey.pojo.root.DAO.order_course.UpdateOrderCourseDAO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class UpdateOrderBO {
    @Autowired
    private UpdateOrderCourseDAO updateOrderCourseDAO;

    public void update(OrderBuyCourseDTO order){
        updateOrderCourseDAO.update(order);
    }

    public void updateOrderSateAfterPay(OrderBuyCourseDTO order){
        order.setOrderStateEnum(OrderStateEnum.payed);
        update(order);
    }
}
