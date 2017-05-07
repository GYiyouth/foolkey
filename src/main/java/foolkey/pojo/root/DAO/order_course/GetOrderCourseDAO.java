package foolkey.pojo.root.DAO.order_course;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.OrderBuyAnswerDTO;
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

    /**
     * 获取学生悬赏的订单
     * @param rewardId 悬赏课程的Id
     * @return
     */
    public OrderBuyCourseDTO getRewardOrderByCourseId(Long rewardId, OrderStateEnum state){
        List <OrderBuyCourseDTO> list =
                (List<OrderBuyCourseDTO>)
                hibernateTemplate.find(
                "from OrderBuyCourseDTO ob where ob.courseId = ? " +
                        "and ob.orderStateEnum = ?", rewardId, state
        );
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    public List<OrderBuyCourseDTO> findAllByStudentId(Long studentId){
        return
                (List<OrderBuyCourseDTO>)
                        hibernateTemplate.find(
                                "from OrderBuyCourseDTO ob where ob.orderStateEnum = ?", studentId);
    }
}
