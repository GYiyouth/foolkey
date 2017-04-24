package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_course_teacher")
public class CourseTeacherDTO extends CourseAbstract{

    //预计时长
    @Column(name = "duration")
    private Date duration;

    //课程数
    @Column(name = "class_amount")
    private Date classAmount;

    @Column(name  = "sales")
    private Integer sales;

    @Column(name = "average_score")
    private double averageScore;


    @Override
    public String toString() {
        return "CourseTeacherDTO{" +
                "duration=" + duration +
                ", classAmount=" + classAmount +
                ", sales=" + sales +
                ", averageScore=" + averageScore +
                '}';
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Date getClassAmount() {
        return classAmount;
    }

    public void setClassAmount(Date classAmount) {
        this.classAmount = classAmount;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
