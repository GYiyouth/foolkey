package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 申请提问
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_application_question")
public class ApplicationQuestionDTO extends ApplicationAbstract{

    //问题的id
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
