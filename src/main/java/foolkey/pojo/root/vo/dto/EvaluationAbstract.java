package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.EvaluationStateEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 评价，其实除了课程评价，评分是不让对方看到的
 * Created by geyao on 2017/4/24.
 */
@Component
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EvaluationAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //评价人的id
    @Column(name = "creator_id")
    private Long creatorId;

    //被评价者id
    @Column(name = "accept_id")
    private Long acceptor_id;

    //相关的订单
    @Column(name = "order_id")
    private Long orderId;

    //评价的状态，目前没有什么用
    @Column(name = "state")
    @Enumerated(EnumType.ORDINAL)
    private EvaluationStateEnum evaluationStateEnum;

    //分数
    @Column(name = "score")
    private Float score;


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

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
