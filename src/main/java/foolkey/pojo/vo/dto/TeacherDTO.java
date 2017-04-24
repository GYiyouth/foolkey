package foolkey.pojo.vo.dto;

import foolkey.pojo.vo.assistObject.VerifyStateEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by geyao on 2017/4/24.
 */
@Entity
@Component
@Table(name = "t_teacher")
public class TeacherDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_average_score")
    private Integer teacherAverageScore;

    @Column(name = "teaching_time")
    private Integer teachingTime;

    @Column(name = "teaching_number")
    private Integer teachingNumber;

    @Column(name = "verify_state")
    private VerifyStateEnum verifyState;

    @Column(name = "follower_number")
    private Integer followerNumber;

    public TeacherDTO() {
        super();
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "id=" + id +
                ", teacherAverageScore=" + teacherAverageScore +
                ", teachingTime=" + teachingTime +
                ", teachingNumber=" + teachingNumber +
                ", verifyState=" + verifyState +
                ", followerNumber=" + followerNumber +
                '}';
    }

    public Integer getTeacherAverageScore() {
        return teacherAverageScore;
    }

    public void setTeacherAverageScore(Integer teacherAverageScore) {
        this.teacherAverageScore = teacherAverageScore;
    }

    public Integer getTeachingTime() {
        return teachingTime;
    }

    public void setTeachingTime(Integer teachingTime) {
        this.teachingTime = teachingTime;
    }

    public Integer getTeachingNumber() {
        return teachingNumber;
    }

    public void setTeachingNumber(Integer teachingNumber) {
        this.teachingNumber = teachingNumber;
    }

    public VerifyStateEnum getVerifyState() {
        return verifyState;
    }

    public void setVerifyState(VerifyStateEnum verifyState) {
        this.verifyState = verifyState;
    }

    public Integer getFollowerNumber() {
        return followerNumber;
    }

    public void setFollowerNumber(Integer followerNumber) {
        this.followerNumber = followerNumber;
    }
}
