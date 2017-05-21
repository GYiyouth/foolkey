package foolkey.pojo.send_to_client.question;

import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.TeacherAllInfoDTO;

/**
 * 问题-提问者-回答者封装在一起的DTO
 * Created by GR on 2017/5/21.
 */
public class QuestionAskerAnswerSTCDTO {

    private StudentDTO askerDTO;
    private QuestionAnswerDTO questionAnswerDTO;
    private TeacherAllInfoDTO answererDTO;

    @Override
    public String toString() {
        return "QuestionAskerAnswerSTCDTO{" +
                "askerDTO=" + askerDTO +
                ", questionAnswerDTO=" + questionAnswerDTO +
                ", answererDTO=" + answererDTO +
                '}';
    }

    public StudentDTO getAskerDTO() {
        return askerDTO;
    }

    public void setAskerDTO(StudentDTO askerDTO) {
        this.askerDTO = askerDTO;
    }

    public QuestionAnswerDTO getQuestionAnswerDTO() {
        return questionAnswerDTO;
    }

    public void setQuestionAnswerDTO(QuestionAnswerDTO questionAnswerDTO) {
        this.questionAnswerDTO = questionAnswerDTO;
    }

    public TeacherAllInfoDTO getAnswererDTO() {
        return answererDTO;
    }

    public void setAnswererDTO(TeacherAllInfoDTO answererDTO) {
        this.answererDTO = answererDTO;
    }
}
