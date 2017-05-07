package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 退款的订单
 * Created by geyao on 2017/4/24.
 */
@Entity
@Component
@Table(name = "t_order_refund")
public class OrderRefundDTO extends OrderAbstract {

    //申请退款时间
    @Column(name = "apply_time")
    private Date applyTime;

    //到账时间
    @Column(name = "to_account_time")
    private Date toAccountTime;

    //退款的金额，虚拟币
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
