package foolkey.pojo.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_application_question")
public class ApplicationQuestionDTO extends ApplicationAbstract{

    @Column(name = "question_id")
    private Long questionId;

    @Override
    public String toString() {
        return "ApplicationQuestionDTO{" +
                "questionId=" + questionId +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
