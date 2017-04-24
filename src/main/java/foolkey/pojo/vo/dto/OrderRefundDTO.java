package foolkey.pojo.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by geyao on 2017/4/24.
 */
@Entity
@Component
@Table(name = "t_order_refund")
public class OrderRefundDTO extends OrderAbstract {

    @Column(name = "apply_time")
    private Date applyTime;


    @Column(name = "to_account_time")
    private Date toAccountTime;

    @Column(name = "refund_amount")
    private Double refundAmount;

    public OrderRefundDTO() {
        super();
    }

    @Override
    public String toString() {
        return "RefundOrderDTO{" +
                "applyTime=" + applyTime +
                ", toAccountTime=" + toAccountTime +
                ", refundAmount=" + refundAmount +
                "} " + super.toString();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getToAccountTime() {
        return toAccountTime;
    }

    public void setToAccountTime(Date toAccountTime) {
        this.toAccountTime = toAccountTime;
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }
}
