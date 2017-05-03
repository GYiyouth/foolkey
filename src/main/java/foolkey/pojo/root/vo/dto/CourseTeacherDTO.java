package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.CourseTeacherStateEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_course_teacher")
public class CourseTeacherDTO extends CourseAbstract{

    //预计时长 0.5h/1h...
    @Column(name = "duration")
    private Double duration;

    //课程数
    @Column(name = "class_amount")
    private Integer classAmount;

    @Column(name = "course_teacher_state")
    @Enumerated(EnumType.ORDINAL)
    private CourseTeacherStateEnum courseTeacherStateEnum;

    @Column(name  = "sales")
    private Integer sales;

    @Column(name = "average_score")
    private Double averageScore;

    @Override
    public String toString() {
        return "CourseTeacherDTO{" +
                "duration=" + duration +
                ", classAmount=" + classAmount +
                ", courseTeacherStateEnum=" + courseTeacherStateEnum +
                ", sales=" + sales +
                ", averageScore=" + averageScore +
                "} " + super.toString();
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getClassAmount() {
        return classAmount;
    }

    public void setClassAmount(Integer classAmount) {
        this.classAmount = classAmount;
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

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }
}
