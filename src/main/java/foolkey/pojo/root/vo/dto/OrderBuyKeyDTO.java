package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.PaymentEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 购买虚拟币的订单
 * Created by geyao on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_order_buy_key")
public class OrderBuyKeyDTO extends OrderAbstract {

    @Column(name = "payment")
    @Enumerated(EnumType.ORDINAL)
    private PaymentEnum paymentEnum;

    //优惠券的id
    @Column(name = "coupon_id")
    private Long couponId;

    @Override
    public String toString() {
        return "OrderBuyKeyDTO{" +
                "paymentEnum=" + paymentEnum +
                ", couponId=" + couponId +
                '}';
    }

    public PaymentEnum getPaymentEnum() {
        return paymentEnum;
    }

    public void setPaymentEnum(PaymentEnum paymentEnum) {
        this.paymentEnum = paymentEnum;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }
}
