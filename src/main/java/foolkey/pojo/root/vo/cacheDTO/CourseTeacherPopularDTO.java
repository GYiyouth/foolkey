package foolkey.pojo.root.vo.cacheDTO;

import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by GR on 2017/5/3.
 */
@Component("courseTeacherPopularDTO")
@Scope("prototype")
public class CourseTeacherPopularDTO {
    //课程
    private CourseTeacherDTO courseTeacherDTO;
    //老师
    private TeacherAllInfoDTO teacherAllInfoDTO;

    @Override
    public String toString() {
        return "CourseTeacherPopularDTO{" +
                "courseTeacherDTO=" + courseTeacherDTO +
                ", teacherAllInfoDTO=" + teacherAllInfoDTO +
                '}';
    }

    public CourseTeacherDTO getCourseTeacherDTO() {
        return courseTeacherDTO;
    }

    public void setCourseTeacherDTO(CourseTeacherDTO courseTeacherDTO) {
        this.courseTeacherDTO = courseTeacherDTO;
    }

    public TeacherAllInfoDTO getTeacherAllInfoDTO() {
        return teacherAllInfoDTO;
    }

    public void setTeacherAllInfoDTO(TeacherAllInfoDTO teacherAllInfoDTO) {
        this.teacherAllInfoDTO = teacherAllInfoDTO;
    }
}
