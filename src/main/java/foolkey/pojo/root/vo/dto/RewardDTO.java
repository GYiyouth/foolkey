package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.RewardStateEnum;
import foolkey.pojo.root.vo.assistObject.SexTagEnum;
import foolkey.pojo.root.vo.assistObject.StudentBaseEnum;
import foolkey.pojo.root.vo.assistObject.TeacherRequirementEnum;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 悬赏
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_course_student")
public class RewardDTO extends CourseAbstract{

    // 认证老师,非认证老师,不限
    @Column(name = "teacher_requirement")
    private TeacherRequirementEnum teacherRequirementEnum;

    // 悬赏状态 待接单,已解决
    @Column(name = "course_student_state")
    private RewardStateEnum rewardStateEnum;

    // 学生基础
    @Column(name = "student_base")
    private StudentBaseEnum studentBaseEnum;

    // 性别
    @Column(name = "teacher_sex_requirement")
    private SexTagEnum sexTagEnum;

    @Override
    public String toString() {
        return "CourseStudentDTO{" +
                "teacherRequirementEnum=" + teacherRequirementEnum +
                ", rewardStateEnum=" + rewardStateEnum +
                ", studentBaseEnum=" + studentBaseEnum +
                ", sexTagEnum=" + sexTagEnum +
                '}';
    }

    public TeacherRequirementEnum getTeacherRequirementEnum() {
        return teacherRequirementEnum;
    }

    public void setTeacherRequirementEnum(TeacherRequirementEnum teacherRequirementEnum) {
        this.teacherRequirementEnum = teacherRequirementEnum;
    }

    public RewardStateEnum getRewardStateEnum() {
        return rewardStateEnum;
    }

    public void setRewardStateEnum(RewardStateEnum rewardStateEnum) {
        this.rewardStateEnum = rewardStateEnum;
    }

    public StudentBaseEnum getStudentBaseEnum() {
        return studentBaseEnum;
    }

    public void setStudentBaseEnum(StudentBaseEnum studentBaseEnum) {
        this.studentBaseEnum = studentBaseEnum;
    }

    public SexTagEnum getSexTagEnum() {
        return sexTagEnum;
    }

    public void setSexTagEnum(SexTagEnum sexTagEnum) {
        this.sexTagEnum = sexTagEnum;
    }
}
