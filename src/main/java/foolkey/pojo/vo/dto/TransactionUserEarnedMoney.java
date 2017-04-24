package foolkey.pojo.vo.dto;

import foolkey.pojo.vo.assistObject.TransactionTypeEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by geyao on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_transaction_user_earned_money")
public class TransactionUserEarnedMoney {
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
}
