package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.assistObject.TransactionStateEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by geyao on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_transaction_user_take_money")
public class TransactionUserTakeMoneyDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //提现用户的id，一般等于studentId，teacherId
    @Column(name = "user_id")
    private Long userId;

    //实现时间
    @Column(name = "create_Time")
    private Date createdTime;

    //提现金额，一定是人民币
    @Column(name = "amount")
    private Double amount;

    //到账状态，分为未到账、已到账
    @Column(name = "transaction_state")
    @Enumerated(EnumType.ORDINAL)
    private TransactionStateEnum transactionState;

    public TransactionUserTakeMoneyDTO() {
        super();
    }

    @Override
    public String toString() {
        return "TransactionUserTakeMoney{" +
                "id=" + id +
                ", userId=" + userId +
                ", createdTime=" + createdTime +
                ", amount=" + amount +
                ", transactionState=" + transactionState +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionStateEnum getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(TransactionStateEnum transactionState) {
        this.transactionState = transactionState;
    }
}
