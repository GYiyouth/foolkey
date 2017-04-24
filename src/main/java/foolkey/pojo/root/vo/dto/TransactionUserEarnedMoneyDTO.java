package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.TransactionTypeEnum;
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

    @Column(name = "acceptor_id")
    private Long acceptUserId;

    private Double amount;

    @Column(name = "order_type")
    private TransactionTypeEnum transactionTypeEnum;

    @Column(name = "order_id")
    private Long orderId;

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
                ", transactionTypeEnum=" + transactionTypeEnum +
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

    public TransactionTypeEnum getTransactionTypeEnum() {
        return transactionTypeEnum;
    }

    public void setTransactionTypeEnum(TransactionTypeEnum transactionTypeEnum) {
        this.transactionTypeEnum = transactionTypeEnum;
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
