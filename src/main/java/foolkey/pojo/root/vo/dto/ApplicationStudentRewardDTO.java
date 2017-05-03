package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_application_student_reward")
public class ApplicationStudentRewardDTO extends ApplicationAbstract{

    @Column(name = "courseStudent_id")
    private Long courseStudentId;

    @Column(name = "order_id")
    private Long orderId;

    @Override
    public String toString() {
        return "ApplicationStudentRewardDTO{" +
                "courseStudentId=" + courseStudentId +
                ", orderId=" + orderId +
                "} " + super.toString();
    }

    public Long getCourseStudentId() {
        return courseStudentId;
    }

    public void setCourseStudentId(Long courseStudentId) {
        this.courseStudentId = courseStudentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
