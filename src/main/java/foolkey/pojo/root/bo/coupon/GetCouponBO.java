package foolkey.pojo.root.bo.coupon;

import foolkey.pojo.root.vo.dto.CouponDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional(readOnly = true)
public class GetCouponBO {

    /**
     * 根据id获取优惠券信息
     * @param couponId
     * @return
     */
    public CouponDTO getCouponDTO(String couponId){
        return null;
    }
}
