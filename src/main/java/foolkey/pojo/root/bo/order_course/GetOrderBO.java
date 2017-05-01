package foolkey.pojo.root.bo.order_course;

import foolkey.pojo.root.DAO.order_course.GetOrderCourseDAO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional(readOnly = true)
public class GetOrderBO {
    @Autowired
    private GetOrderCourseDAO getOrderCourseDAO;

    /**
     * 获取订单资料
     * @param courseId 订单id
     * @return
     */
    public OrderBuyCourseDTO getCourseOrder(String courseId){
        Long id = Long.parseLong(courseId);
        return getOrderCourseDAO.get(OrderBuyCourseDTO.class, id);
    }
}
