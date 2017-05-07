package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.CourseTeacherStateEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * 课程
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_course_teacher")
public class CourseTeacherDTO extends CourseAbstract{

    //建议时长 0.5h/1h...
    @Column(name = "duration")
    private Float duration;


    //开课状态
    @Column(name = "course_teacher_state")
    @Enumerated(EnumType.ORDINAL)
    private CourseTeacherStateEnum courseTeacherStateEnum;

    //销量
    @Column(name  = "sales")
    private Integer sales;

    //平均得分
    @Column(name = "average_score")
    private Float averageScore;

    @Override
    public String toString() {
        return "CourseTeacherDTO{" +
                "duration=" + duration +
                ", courseTeacherStateEnum=" + courseTeacherStateEnum +
                ", sales=" + sales +
                ", averageScore=" + averageScore +
                "} " + super.toString();
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public CourseTeacherStateEnum getCourseTeacherStateEnum() {
        return courseTeacherStateEnum;
    }

    public void setCourseTeacherStateEnum(CourseTeacherStateEnum courseTeacherStateEnum) {
        this.courseTeacherStateEnum = courseTeacherStateEnum;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Float averageScore) {
        this.averageScore = averageScore;
    }
}
