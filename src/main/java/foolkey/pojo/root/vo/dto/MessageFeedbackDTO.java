package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.FeedbackTypeEnum;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by geyao on 2017/4/24.
 */
@Entity
@Component
@Table(name = "t_message_feedback")
public class MessageFeedbackDTO extends MessageAbstract{

    @Column(name = "t_message_feedback")
    private Long receiverId;

    @Column(name = "t_message_feedback")
    private FeedbackTypeEnum feedbackTypeEnum;

    public MessageFeedbackDTO() {
        super();
    }

    @Override
    public String toString() {
        return "MessageFeedbackDTO{" +
                "receiverId=" + receiverId +
                ", feedbackTypeEnum=" + feedbackTypeEnum +
                "} " + super.toString();
    }

    @Override
    public Long getReceiverId() {
        return receiverId;
    }

    @Override
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public FeedbackTypeEnum getFeedbackTypeEnum() {
        return feedbackTypeEnum;
    }

    public void setFeedbackTypeEnum(FeedbackTypeEnum feedbackTypeEnum) {
        this.feedbackTypeEnum = feedbackTypeEnum;
    }
}
