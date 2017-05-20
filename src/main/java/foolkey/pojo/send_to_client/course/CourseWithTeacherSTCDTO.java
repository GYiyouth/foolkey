package foolkey.pojo.send_to_client.course;

import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.send_to_client.TeacherAllInfoDTO;

/**
 * 课程-老师 信息DTO
 * Created by GR on 2017/5/20.
 */
public class CourseWithTeacherSTCDTO {

    //课程信息
    private CourseDTO courseDTO;
    //老师信息
    private TeacherAllInfoDTO teacherAllInfoDTO;

    @Override
    public String toString() {
        return "CourseWithTeacherSTCDTO{" +
                "courseDTO=" + courseDTO +
                ", teacherAllInfoDTO=" + teacherAllInfoDTO +
                '}';
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public TeacherAllInfoDTO getTeacherAllInfoDTO() {
        return teacherAllInfoDTO;
    }

    public void setTeacherAllInfoDTO(TeacherAllInfoDTO teacherAllInfoDTO) {
        this.teacherAllInfoDTO = teacherAllInfoDTO;
    }
}
