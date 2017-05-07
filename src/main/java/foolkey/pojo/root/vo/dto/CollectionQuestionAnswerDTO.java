package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 收藏的回答
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_collection_question_answer")
public class CollectionQuestionAnswerDTO extends CollectionAbstract{

    @Column(name = "question_answer_id")
    private Long questionAnswerId;

    @Override
    public String toString() {
        return "CollectionQuestionAnswerDTO{" +
                "questionAnswerId=" + questionAnswerId +
                '}';
    }

    public Long getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(Long questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }
}
