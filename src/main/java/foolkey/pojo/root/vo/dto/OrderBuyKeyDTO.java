package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.PaymentEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by geyao on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_order_buy_key")
public class OrderBuyKeyDTO extends OrderAbstract {

    @Column(name = "payment")
    @Enumerated(EnumType.ORDINAL)
    private PaymentEnum paymentEnum;

    public OrderBuyKeyDTO() {
        super();
    }

    public PaymentEnum getPaymentEnum() {
        return paymentEnum;
    }

    public void setPaymentEnum(PaymentEnum paymentEnum) {
        this.paymentEnum = paymentEnum;
    }
}
