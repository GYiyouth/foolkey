package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 悬赏-学生 DTO
 * Created by ustcg on 2017/5/6.
 */
@Component("courseStudentPopularDTO")
@Scope("prototype")
public class CourseStudentPopularDTO {

    //悬赏内容DTO
    private RewardDTO courseStudentDTO;
    //发布悬赏的学生DTO
    private StudentDTO studentDTO;

    @Override
    public String toString() {
        return "CourseStudentPopularDTO{" +
                "courseStudentDTO=" + courseStudentDTO +
                ", studentDTO=" + studentDTO +
                '}';
    }

    public RewardDTO getCourseStudentDTO() {
        return courseStudentDTO;
    }

    public void setCourseStudentDTO(RewardDTO courseStudentDTO) {
        this.courseStudentDTO = courseStudentDTO;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }
}
