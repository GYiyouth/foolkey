package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.CourseDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 课程-老师信息  DTO
 * Created by GR on 2017/5/3.
 */
@Component("courseTeacherPopularDTO")
@Scope("prototype")
public class CourseTeacherPopularDTO {
    //课程
    private CourseDTO courseTeacherDTO;
    //老师
    private TeacherAllInfoDTO teacherAllInfoDTO;

    @Override
    public String toString() {
        return "CourseTeacherPopularDTO{" +
                "courseTeacherDTO=" + courseTeacherDTO +
                ", teacherAllInfoDTO=" + teacherAllInfoDTO +
                '}';
    }

    public CourseDTO getCourseTeacherDTO() {
        return courseTeacherDTO;
    }

    public void setCourseTeacherDTO(CourseDTO courseTeacherDTO) {
        this.courseTeacherDTO = courseTeacherDTO;
    }

    public TeacherAllInfoDTO getTeacherAllInfoDTO() {
        return teacherAllInfoDTO;
    }

    public void setTeacherAllInfoDTO(TeacherAllInfoDTO teacherAllInfoDTO) {
        this.teacherAllInfoDTO = teacherAllInfoDTO;
    }
}
