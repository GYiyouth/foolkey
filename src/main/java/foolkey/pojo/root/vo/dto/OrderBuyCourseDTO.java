package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.TeachMethodEnum;
import foolkey.pojo.root.vo.assistObject.CourseTeacherStateEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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
    @Enumerated(EnumType.ORDINAL)
    private TeachMethodEnum teachMethodEnum;

    @Column(name = "teach_state")
    @Enumerated(EnumType.ORDINAL)
    private CourseTeacherStateEnum teachStateEnum;

    @Column(name = "lesson_end_time")
    private Date lessonEndTime;

    @Column(name = "course_type")
    @Enumerated(EnumType.ORDINAL)
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

    public CourseTeacherStateEnum getTeachStateEnum() {
        return teachStateEnum;
    }

    public void setTeachStateEnum(CourseTeacherStateEnum teachStateEnum) {
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
