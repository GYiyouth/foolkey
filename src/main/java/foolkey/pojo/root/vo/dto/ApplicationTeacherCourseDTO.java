package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 申请老师的课程
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_application_teacher_course")
public class ApplicationTeacherCourseDTO extends ApplicationAbstract{

    //订单的id
    @Column(name = "order_id")
    private Long orderId;

    //老师(创建课程人)的id
    @Column(name = "courseTeacher_id")
    private Long teacherId;

    @Override
    public String toString() {
        return "ApplicationTeacherCourseDTO{" +
                "orderId=" + orderId +
                ", teacherId=" + teacherId +
                "} " + super.toString();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
