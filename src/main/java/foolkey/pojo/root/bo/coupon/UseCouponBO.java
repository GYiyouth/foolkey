package foolkey.pojo.root.bo.coupon;

import foolkey.pojo.root.vo.assistObject.CouponTypeEnum;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.dto.CouponDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class UseCouponBO {

    /**
     * 使用优惠券，验证优惠券的可用性，返回计算后价格
     * @param studentDTO
     * @param orderDTO
     * @param couponDTO
     */
    public Double userCoupon(
            StudentDTO studentDTO, OrderBuyCourseDTO orderDTO, CouponDTO couponDTO){

        if (couponDTO == null)
            return orderDTO.getAmount();
        if (!couponDTO.getId().equals( studentDTO.getId() ))
            return null;
        if (couponDTO.getDeadTime().compareTo(new Date()) < 0) // 优惠券已使用或者过期
            return null;

        CourseTypeEnum courseTypeEnum = orderDTO.getCourseTypeEnum();
        CouponTypeEnum couponTypeEnum = couponDTO.getCouponTypeEnum();
        if ( !couponTypeEnum.checkType(courseTypeEnum) ) // 优惠券类型不符
            return null;

        if (couponDTO.getCouponTypeEnum().compareTo(CouponTypeEnum.购买课程) == 0
                || couponDTO.getCouponTypeEnum().compareTo(CouponTypeEnum.全场) == 0){
            int time = couponDTO.getDeadTime().compareTo( new Date() );
            if (time > 0){
                int level = couponDTO.getLevel().compareTo(orderDTO.getAmount());
                if (level < 0) {
                    orderDTO.setCouponId(couponDTO.getId());
                    return (orderDTO.getAmount() - couponDTO.getValue());
                }
            }
        }
        return null;
    }
}
