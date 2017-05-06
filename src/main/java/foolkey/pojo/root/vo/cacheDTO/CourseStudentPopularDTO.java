package foolkey.pojo.root.vo.cacheDTO;

import foolkey.pojo.root.vo.dto.CourseStudentDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by ustcg on 2017/5/6.
 */
@Component("courseStudentPopularDTO")
@Scope("prototype")
public class CourseStudentPopularDTO {

    //悬赏内容DTO
    private CourseStudentDTO courseStudentDTO;
    //发布悬赏的学生DTO
    private StudentDTO studentDTO;

    @Override
    public String toString() {
        return "CourseStudentPopularDTO{" +
                "courseStudentDTO=" + courseStudentDTO +
                ", studentDTO=" + studentDTO +
                '}';
    }

    public CourseStudentDTO getCourseStudentDTO() {
        return courseStudentDTO;
    }

    public void setCourseStudentDTO(CourseStudentDTO courseStudentDTO) {
        this.courseStudentDTO = courseStudentDTO;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        //把密码置为空
        studentDTO.setPassWord("");
        this.studentDTO = studentDTO;
    }
}
