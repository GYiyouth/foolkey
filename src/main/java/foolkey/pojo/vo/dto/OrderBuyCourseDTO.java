package foolkey.pojo.vo.dto;

import foolkey.pojo.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.vo.assistObject.TeachMethodEnum;
import foolkey.pojo.vo.assistObject.TeachStateEnum;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by geyao on 2017/4/24.
 */
@Entity
@Component
@Table(name = "t_order_buy_course")
public class OrderBuyCourseDTO extends OrderAbstract{

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "number")
    private Double number;

    @Column(name = "cutOff_percent")
    private Double cutOffPercent;

    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "teach_method")
    private TeachMethodEnum teachMethodEnum;

    @Column(name = "teach_state")
    private TeachStateEnum teachStateEnum;

    @Column(name = "lesson_end_time")
    private Date lessonEndTime;

    @Column(name = "course_type")
    private CourseTypeEnum courseTypeEnum;

    public OrderBuyCourseDTO() {
        super();
    }

    @Override
    public String toString() {
        return "OrderBuyCourseDTO{" +
                "courseId=" + courseId +
                ", teacherId=" + teacherId +
                ", number=" + number +
                ", cutOffPercent=" + cutOffPercent +
                ", couponId=" + couponId +
                ", teachMethodEnum=" + teachMethodEnum +
                ", teachStateEnum=" + teachStateEnum +
                ", lessonEndTime=" + lessonEndTime +
                ", courseTypeEnum=" + courseTypeEnum +
                "} " + super.toString();
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getCutOffPercent() {
        return cutOffPercent;
    }

    public void setCutOffPercent(Double cutOffPercent) {
        this.cutOffPercent = cutOffPercent;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public TeachMethodEnum getTeachMethodEnum() {
        return teachMethodEnum;
    }

    public void setTeachMethodEnum(TeachMethodEnum teachMethodEnum) {
        this.teachMethodEnum = teachMethodEnum;
    }

    public TeachStateEnum getTeachStateEnum() {
        return teachStateEnum;
    }

    public void setTeachStateEnum(TeachStateEnum teachStateEnum) {
        this.teachStateEnum = teachStateEnum;
    }

    public Date getLessonEndTime() {
        return lessonEndTime;
    }

    public void setLessonEndTime(Date lessonEndTime) {
        this.lessonEndTime = lessonEndTime;
    }

    public CourseTypeEnum getCourseTypeEnum() {
        return courseTypeEnum;
    }

    public void setCourseTypeEnum(CourseTypeEnum courseTypeEnum) {
        this.courseTypeEnum = courseTypeEnum;
    }
}
