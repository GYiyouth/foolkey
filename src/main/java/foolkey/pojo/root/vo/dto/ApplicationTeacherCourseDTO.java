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
@Table(name = "t_application_teacher_course")
public class ApplicationTeacherCourseDTO extends ApplicationAbstract{

    @Column(name = "order_id")
    private Long orderId;

    @Override
    public String toString() {
        return "ApplicationTeacherCourseDTO{" +
                "order=" + orderId +
                '}';
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
