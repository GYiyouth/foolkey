package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.OrderTypeEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by geyao on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_transaction_user_earned_money")
public class TransactionUserEarnedMoneyDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //老师的id
    @Column(name = "acceptor_id")
    private Long acceptUserId;

    //金额，人民币，由虚拟货币转换来，汇率参考foolkey.tool.constant_values.MoneyRate
    private Double amount;

    //订单分类
    @Column(name = "order_type")
    @Enumerated(EnumType.ORDINAL)
    private OrderTypeEnum orderTypeEnum;

    @Column(name = "order_id")
    private Long orderId;

    //创建时间
    @Column(name = "create_time")
    private Date createdTime;

    public TransactionUserEarnedMoneyDTO() {
        super();
    }

    @Override
    public String toString() {
        return "TransactionUserEarnedMoney{" +
                "id=" + id +
                ", acceptUserId=" + acceptUserId +
                ", amount=" + amount +
                ", orderTypeEnum=" + orderTypeEnum +
                ", orderId=" + orderId +
                ", createdTime=" + createdTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(Long acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public OrderTypeEnum getOrderTypeEnum() {
        return orderTypeEnum;
    }

    public void setOrderTypeEnum(OrderTypeEnum orderTypeEnum) {
        this.orderTypeEnum = orderTypeEnum;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
