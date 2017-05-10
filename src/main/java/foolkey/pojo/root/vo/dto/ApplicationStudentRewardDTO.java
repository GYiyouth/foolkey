package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 申请学生的悬赏
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_application_student_reward")
public class ApplicationStudentRewardDTO extends ApplicationAbstract{

    //学生(发布悬赏人)的id
    @Column(name = "student_id")
    private Long studentId;

    //悬赏的id
    @Column(name = "reward_id")
    private Long rewardId;

    @Override
    public String toString() {
        return "ApplicationStudentRewardDTO{" +
                "studentId=" + studentId +
                ", orderId=" + rewardId +
                "} " + super.toString();
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }
}
