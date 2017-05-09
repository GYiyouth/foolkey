package foolkey.pojo.root.bo.coupon;

import foolkey.pojo.root.vo.assistObject.CouponTypeEnum;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.dto.CouponDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
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
        if (!couponDTO.getId().equals( studentDTO.getId() )) // 优惠券所有者不对
            return null;
        if (couponDTO.getDeadTime().compareTo(new Date()) < 0) // 优惠券已使用或者过期
            return null;

        CourseTypeEnum courseTypeEnum = orderDTO.getCourseTypeEnum();
        CouponTypeEnum couponTypeEnum = couponDTO.getCouponTypeEnum();
        if ( !couponTypeEnum.checkType(courseTypeEnum) ) // 优惠券类型不符
            return null;


        int level = couponDTO.getLevel().compareTo(orderDTO.getAmount());
        if (level < 0) { // 验证门槛
            orderDTO.setCouponId(couponDTO.getId());
            return (orderDTO.getAmount() - couponDTO.getValue());
        }else { //门槛未达到
//            return orderDTO.getAmount();
        }

        return null;
    }

    /**
     * 使用优惠券，验证优惠券的可用性，返回计算后价格
     * @param studentDTO
     * @param courseDTO
     * @param couponDTO
     */
    public Double userCouponForReward(
            StudentDTO studentDTO, RewardDTO courseDTO, CouponDTO couponDTO){

        if (couponDTO == null)
            return courseDTO.getPrice();
        if (!couponDTO.getId().equals( studentDTO.getId() )) // 优惠券所有者不对
            return null;
        if (couponDTO.getDeadTime().compareTo(new Date()) < 0) // 优惠券已使用或者过期
            return null;

        CourseTypeEnum courseTypeEnum = CourseTypeEnum.学生悬赏;
        CouponTypeEnum couponTypeEnum = couponDTO.getCouponTypeEnum();
        if ( !couponTypeEnum.checkType(courseTypeEnum) ) // 优惠券类型不符
            return null;


        int level = couponDTO.getLevel().compareTo(courseDTO.getPrice());
        if (level < 0) { // 验证门槛
            Double price = courseDTO.getPrice() - couponDTO.getValue();
            return ( price > 0 ? price : courseDTO.getPrice());
        }else { //门槛未达到
//            return orderDTO.getAmount();
        }

        return null;
    }
}
