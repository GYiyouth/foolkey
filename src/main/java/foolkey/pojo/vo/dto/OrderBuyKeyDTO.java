package foolkey.pojo.vo.dto;

import foolkey.pojo.vo.assistObject.PaymentEnum;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by geyao on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_order_buy_key")
public class OrderBuyKeyDTO extends OrderAbstract {

    @Column(name = "payment")
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