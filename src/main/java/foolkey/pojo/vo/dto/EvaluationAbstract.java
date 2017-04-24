package foolkey.pojo.vo.dto;

import foolkey.pojo.vo.assistObject.EvaluationStateEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by geyao on 2017/4/24.
 */
@Component
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EvaluationAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creator_id")
    private Long creatorid;

    @Column(name = "accept_id")
    private Long acceptor_id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "state")
    private EvaluationStateEnum evaluationStateEnum;

    @Column(name = "score")
    private Double score;


    public EvaluationAbstract() {
        super();
    }

    @Override
    public String toString() {
        return "EvaluationAbstract{" +
                "id=" + id +
                ", creatorid=" + creatorid +
                ", acceptor_id=" + acceptor_id +
                ", orderId=" + orderId +
                ", evaluationStateEnum=" + evaluationStateEnum +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(Long creatorid) {
        this.creatorid = creatorid;
    }

    public Long getAcceptor_id() {
        return acceptor_id;
    }

    public void setAcceptor_id(Long acceptor_id) {
        this.acceptor_id = acceptor_id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public EvaluationStateEnum getEvaluationStateEnum() {
        return evaluationStateEnum;
    }

    public void setEvaluationStateEnum(EvaluationStateEnum evaluationStateEnum) {
        this.evaluationStateEnum = evaluationStateEnum;
    }
}
