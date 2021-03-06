package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by geyao on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_order_buy_answer")
public class OrderBuyAnswerDTO extends OrderAbstract {

    @Column(name = "question_answer_id")
    private Long questionId;

    //优惠券的id
    @Column(name = "coupon_id")
    private Long couponId;

    public OrderBuyAnswerDTO() {
        super();
    }

    @Override
    public String toString() {
        return "OrderBuyAnswerDTO{" +
                "questionId=" + questionId +
                ", couponId=" + couponId +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }
}
