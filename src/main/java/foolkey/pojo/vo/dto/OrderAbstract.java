package foolkey.pojo.vo.dto;

import foolkey.pojo.vo.assistObject.OrderStateEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by geyao on 2017/4/24.
 */
@Component
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class OrderAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "create_time")
    private Date createdTime;

    @Column(name = "pay_time")
    private Date payTime;

    @Column(name = "exist_time")
    private Date existingTime;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "state")
    private OrderStateEnum orderStateEnum;

    @Override
    public String toString() {
        return "AbstractOrder{" +
                "id=" + id +
                ", amount=" + amount +
                ", createdTime=" + createdTime +
                ", payTime=" + payTime +
                ", existingTime=" + existingTime +
                ", userId=" + userId +
                ", orderStateEnum=" + orderStateEnum +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getExistingTime() {
        return existingTime;
    }

    public void setExistingTime(Date existingTime) {
        this.existingTime = existingTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderStateEnum getOrderStateEnum() {
        return orderStateEnum;
    }

    public void setOrderStateEnum(OrderStateEnum orderStateEnum) {
        this.orderStateEnum = orderStateEnum;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
