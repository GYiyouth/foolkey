package foolkey.pojo.root.bo.coupon;

import foolkey.pojo.root.vo.dto.CouponDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class UseCouponBO {

    public void userCoupon(
            StudentDTO studentDTO, OrderBuyCourseDTO orderDTO, CouponDTO couponDTO){

    }
}
