package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.CourseStudentStateEnum;
import foolkey.pojo.root.vo.assistObject.StudentBaseEnum;
import foolkey.pojo.root.vo.assistObject.TeacherRequirementEnum;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_course_student")
public class CourseStudentDTO extends CourseAbstract{

    @Column(name = "teacher_requirement")
    private TeacherRequirementEnum teacherRequirementEnum;

    @Column(name = "course_student_state")
    private CourseStudentStateEnum courseStudentStateEnum;

    @Column(name = "student_base")
    private StudentBaseEnum studentBaseEnum;

    @Override
    public String toString() {
        return "CourseStudentDTO{" +
                "teacherRequirementEnum=" + teacherRequirementEnum +
                ", courseStudentStateEnum=" + courseStudentStateEnum +
                ", studentBaseEnum=" + studentBaseEnum +
                '}';
    }

    public TeacherRequirementEnum getTeacherRequirementEnum() {
        return teacherRequirementEnum;
    }

    public void setTeacherRequirementEnum(TeacherRequirementEnum teacherRequirementEnum) {
        this.teacherRequirementEnum = teacherRequirementEnum;
    }

    public CourseStudentStateEnum getCourseStudentStateEnum() {
        return courseStudentStateEnum;
    }

    public void setCourseStudentStateEnum(CourseStudentStateEnum courseStudentStateEnum) {
        this.courseStudentStateEnum = courseStudentStateEnum;
    }

    public StudentBaseEnum getStudentBaseEnum() {
        return studentBaseEnum;
    }

    public void setStudentBaseEnum(StudentBaseEnum studentBaseEnum) {
        this.studentBaseEnum = studentBaseEnum;
    }
}
