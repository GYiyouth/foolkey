package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.IndustryTagEnum;
import foolkey.pojo.root.vo.assistObject.TeachMethodEnum;
import foolkey.pojo.root.vo.assistObject.TeachStateEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 2017/4/24.
 */
@Component
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CourseAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "industry_tag")
    private IndustryTagEnum industryTagEnum;

    @Column(name = "topic")
    private String topic;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "teach_method")
    private TeachMethodEnum teachMethodEnum;

    @Column(name ="class_time")
    private Date classTime;

    @Column(name = "teach_state")
    private TeachStateEnum teachStateEnum;

    @Override
    public String toString() {
        return "CourseAbstract{" +
                "id=" + id +
                ", industryTagEnum=" + industryTagEnum +
                ", topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", teachMethodEnum=" + teachMethodEnum +
                ", classTime=" + classTime +
                ", teachStateEnum=" + teachStateEnum +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IndustryTagEnum getIndustryTagEnum() {
        return industryTagEnum;
    }

    public void setIndustryTagEnum(IndustryTagEnum industryTagEnum) {
        this.industryTagEnum = industryTagEnum;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TeachMethodEnum getTeachMethodEnum() {
        return teachMethodEnum;
    }

    public void setTeachMethodEnum(TeachMethodEnum teachMethodEnum) {
        this.teachMethodEnum = teachMethodEnum;
    }

    public Date getClassTime() {
        return classTime;
    }

    public void setClassTime(Date classTime) {
        this.classTime = classTime;
    }

    public TeachStateEnum getTeachStateEnum() {
        return teachStateEnum;
    }

    public void setTeachStateEnum(TeachStateEnum teachStateEnum) {
        this.teachStateEnum = teachStateEnum;
    }
}