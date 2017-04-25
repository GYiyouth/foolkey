package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by geyao on 2017/4/24.
 */
@Component @Entity @Table(name = "t_order_buy_answer")
public class OrderBuyAnswerDTO extends OrderAbstract{

    @Column(name = "question_answer_id")
    private Long questionId;

    public OrderBuyAnswerDTO() {
        super();
    }

    @Override
    public String toString() {
        return "OrderButAnswerDTO{" +
                "questionId=" + questionId +
                "} " + super.toString();
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
