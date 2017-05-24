package foolkey.pojo.root.DAO.order_course;

import foolkey.pojo.root.DAO.base.UpdateBaseDAO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("updateOrderCourseDAO")
public class UpdateOrderCourseDAO extends UpdateBaseDAO<OrderBuyCourseDTO>{
    @Override
    public OrderBuyCourseDTO update(OrderBuyCourseDTO orderBuyCourseDTO) {
        return super.update(orderBuyCourseDTO);
    }
}
