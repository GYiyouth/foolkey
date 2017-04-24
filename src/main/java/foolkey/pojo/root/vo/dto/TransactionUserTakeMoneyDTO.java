package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
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

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "create_Time")
    private Date createdTime;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "order_state")
    private OrderStateEnum orderStateEnum;

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
                ", orderStateEnum=" + orderStateEnum +
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

    public OrderStateEnum getOrderStateEnum() {
        return orderStateEnum;
    }

    public void setOrderStateEnum(OrderStateEnum orderStateEnum) {
        this.orderStateEnum = orderStateEnum;
    }
}
