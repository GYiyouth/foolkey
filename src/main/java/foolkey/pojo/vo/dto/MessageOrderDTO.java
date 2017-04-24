package foolkey.pojo.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by geyao on 2017/4/24.
 */
@Entity
@Component
@Table(name = "t_message_order")
public class MessageOrderDTO extends MessageAbstract {

    @Column(name = "order_id")
    private Long OrderId;

    @Column(name = "acceptor_id")
    private Long receiverId;

    public MessageOrderDTO() {
        super();
    }

    @Override
    public String toString() {
        return "MessageOrderDTO{" +
                "OrderId=" + OrderId +
                ", receiverId=" + receiverId +
                "} " + super.toString();
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    @Override
    public Long getReceiverId() {
        return receiverId;
    }

    @Override
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
