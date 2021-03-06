package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.AbstractDTO;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.QuestionStateEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by geyao on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_question_answer")
public class QuestionAnswerDTO  extends AbstractDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //提问者Id
    @Column(name = "asker_id")
    private Long askerId;

    //回答者的Id
    @Column(name = "answer_id")
    private Long answerId;

    //问题的标题
    @Column(name = "title")
    private String title;

    //问题的描述
    @Column(name = "question_content")
    private String questionContent;

    //问题的标价，虚拟币
    @Column(name = "price")
    private Double price;

    //问题的创建时间
    @Column(name = "create_time")
    private Date createdTime;

    //失效时间
    @Column(name = "invalid_time")
    private Date invalidTime;

    //问题的状态 待付款, 待回答, 已回答, 已过期
    @Column(name = "state")
    @Enumerated(EnumType.ORDINAL)
    private QuestionStateEnum questionStateEnum;

    //围观的人数
    @Column(name = "onlooker_number")
    private Integer onlookerNumber;

    //回答的时间
    @Column(name = "answer_time")
    private Date answerTime;

    //回答的内容
    @Column(name = "answer_content")
    private String answerContent;

    //回答的最后更新时间
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    //提问属于什么类别的
    @Column(name = "technic_tag")
    private TechnicTagEnum technicTagEnum;


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
                ", technicTagEnum=" + technicTagEnum +
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

    public TechnicTagEnum getTechnicTagEnum() {
        return technicTagEnum;
    }

    public void setTechnicTagEnum(TechnicTagEnum technicTagEnum) {
        this.technicTagEnum = technicTagEnum;
    }
}
