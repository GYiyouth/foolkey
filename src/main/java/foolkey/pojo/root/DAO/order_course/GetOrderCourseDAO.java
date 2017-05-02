package foolkey.pojo.root.DAO.order_course;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getOrderCourseDAO")
public class GetOrderCourseDAO extends GetBaseDAO<OrderBuyCourseDTO>{

    /**
     * 获取学生角色的 某种类别 订单
     * @param studentId
     * @return
     */
    public List<OrderBuyCourseDTO> getCourseOrderAsStudent(Long studentId, OrderStateEnum orderState){
        List<OrderBuyCourseDTO> list = (List<OrderBuyCourseDTO>)
                hibernateTemplate.find("from foolkey.pojo.root.vo.dto.OrderBuyCourseDTO t " +
                        "where t.userId = ? and t.orderStateEnum = ?", studentId, orderState);
        return list;
    }
}
