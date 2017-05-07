package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by geyao on 2017/4/24.
 */
@Entity @Component
@Table(name = "t_order_ask_question")
public class OrderAskQuestionDTO extends OrderAbstract{

    //被提问者id
    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "question_answer_id")
    private Long questionId;

    public OrderAskQuestionDTO() {
        super();
    }

    @Override
    public String toString() {
        return "OrderAskQuestionDTO{" +
                "receiverId=" + receiverId +
                ", questionId=" + questionId +
                "} " + super.toString();
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
