package foolkey.pojo.root.bo.pay_order;

import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.CouponDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 从账户扣款
 * 不对订单，账户，做任何修改
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class PayBO {
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderBO;

    public Boolean pay(StudentDTO studentDTO, OrderBuyCourseDTO order, CouponDTO couponDTO, Double expectPrice){
        Double money = studentDTO.getVirtualCurrency();
        Double totalPrice = order.getAmount();

        if (order.getCouponId() != null)
            totalPrice = totalPrice - couponDTO.getValue();
        //再次计算预期价格，是否相等
        if (!totalPrice.equals(expectPrice))
            return false;
        totalPrice = totalPrice * order.getCutOffPercent();
        if (money > totalPrice){
            money = money - totalPrice;
            studentDTO.setVirtualCurrency(money);
            return true;
        }
        return false;
    }

    public Boolean payForReward(StudentDTO studentDTO, RewardDTO courseDTO, CouponDTO couponDTO){
        Double money = studentDTO.getVirtualCurrency();
        Double totalPrice = courseDTO.getPrice();

        if (couponDTO != null)
            totalPrice = totalPrice - couponDTO.getValue();
        if (money > totalPrice){
            money = money - totalPrice;
            studentDTO.setVirtualCurrency(money);
            return true;
        }
        return false;
    }
}
