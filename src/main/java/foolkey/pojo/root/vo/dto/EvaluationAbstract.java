package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.EvaluationStateEnum;
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
    private Long creatorId;

    @Column(name = "accept_id")
    private Long acceptor_id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "state")
    @Enumerated(EnumType.ORDINAL)
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
                ", creatorid=" + creatorId +
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
