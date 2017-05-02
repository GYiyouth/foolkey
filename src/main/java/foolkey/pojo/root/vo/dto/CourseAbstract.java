package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.CourseTimeDayEnum;
import foolkey.pojo.root.vo.assistObject.TeachMethodEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;

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

    @Column(name = "creator_id")
    private Long creatorId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "technic_tag")
    private TechnicTagEnum technicTagEnum;

    //名字
    @Column(name = "topic")
    private String topic;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "teach_method")
    @Enumerated(EnumType.ORDINAL)
    private TeachMethodEnum teachMethodEnum;

    @Column(name ="class_time")
    @Enumerated(EnumType.ORDINAL)
    private CourseTimeDayEnum courseTimeDayEnum;

    @Override
    public String toString() {
        return "CourseAbstract{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", technicTagEnum=" + technicTagEnum +
                ", topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", teachMethodEnum=" + teachMethodEnum +
                ", courseTimeDayEnum=" + courseTimeDayEnum +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public TechnicTagEnum getTechnicTagEnum() {
        return technicTagEnum;
    }

    public void setTechnicTagEnum(TechnicTagEnum technicTagEnum) {
        this.technicTagEnum = technicTagEnum;
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

    public CourseTimeDayEnum getCourseTimeDayEnum() {
        return courseTimeDayEnum;
    }

    public void setCourseTimeDayEnum(CourseTimeDayEnum courseTimeDayEnum) {
        this.courseTimeDayEnum = courseTimeDayEnum;
    }
}
