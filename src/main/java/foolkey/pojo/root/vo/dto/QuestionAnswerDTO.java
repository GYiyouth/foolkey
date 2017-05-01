package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.QuestionStateEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by geyao on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_question_answer")
public class QuestionAnswerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "asker_id")
    private Long askerId;

    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "title")
    private String title;

    @Column(name = "question_content")
    private String questionContent;

    @Column(name = "price")
    private Double price;

    @Column(name = "create_time")
    private Date createdTime;

    @Column(name = "invalid_time")
    private Date invalidTime;

    @Column(name = "state")
    @Enumerated(EnumType.ORDINAL)
    private QuestionStateEnum questionStateEnum;

    @Column(name = "onlooker_number")
    private Integer onlookerNumber;

    @Column(name = "answer_time")
    private Date answerTime;

    @Column(name = "answer_content")
    private String answerContent;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    public QuestionAnswerDTO() {
        super();
    }

    @Override
    public String toString() {
        return "QuestionAnswerDTO{" +
                "id=" + id +
                ", askerId=" + askerId +
                ", answerId=" + answerId +
                ", title='" + title + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", price=" + price +
                ", createdTime=" + createdTime +
                ", invalidTime=" + invalidTime +
                ", questionStateEnum=" + questionStateEnum +
                ", onlookerNumber=" + onlookerNumber +
                ", answerTime=" + answerTime +
                ", answerContent='" + answerContent + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAskerId() {
        return askerId;
    }

    public void setAskerId(Long askerId) {
        this.askerId = askerId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public QuestionStateEnum getQuestionStateEnum() {
        return questionStateEnum;
    }

    public void setQuestionStateEnum(QuestionStateEnum questionStateEnum) {
        this.questionStateEnum = questionStateEnum;
    }

    public Integer getOnlookerNumber() {
        return onlookerNumber;
    }

    public void setOnlookerNumber(Integer onlookerNumber) {
        this.onlookerNumber = onlookerNumber;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
