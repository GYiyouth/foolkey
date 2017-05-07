package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.VerifyStateEnum;
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
    private Float teacherAverageScore;

    //授课时长
    @Column(name = "teaching_time")
    private Float teachingTime;

    //教授的学生人次
    @Column(name = "teaching_number")
    private Integer teachingNumber;

    //认证状态，分为 进行中，已认证，失败 三种
    @Column(name = "verify_state")
    @Enumerated(EnumType.ORDINAL)
    private VerifyStateEnum verifyState;

    //关注者人数
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

    public Float getTeacherAverageScore() {
        return teacherAverageScore;
    }

    public void setTeacherAverageScore(Float teacherAverageScore) {
        this.teacherAverageScore = teacherAverageScore;
    }

    public Float getTeachingTime() {
        return teachingTime;
    }

    public void setTeachingTime(Float teachingTime) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
