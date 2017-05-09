package foolkey.aop.cacheMaintain;

import foolkey.pojo.root.CAO.CourseTeacher.CourseTeacherCAO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.DirectionEnum;
import foolkey.pojo.send_to_client.CourseTeacherPopularDTO;
import foolkey.pojo.send_to_client.TeacherAllInfoDTO;
import foolkey.pojo.root.vo.dto.CourseDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ustcg on 2017/4/28.
 */
@Aspect
public class CourseTeacherAOP {

    @Autowired
    private CourseTeacherCAO courseTeacherCAO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;


    /**
     * 更新课程信息之后的操作
     * @param jp
     * @param courseTeacherDTO
     */
    @AfterReturning(returning = "courseTeacherDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_teacher.*.update(..))"  )
    public void updateCourseTeacherDTO(JoinPoint jp, CourseDTO courseTeacherDTO){
        if(courseTeacherDTO != null){
            TeacherAllInfoDTO teacherAllInfoDTO = teacherInfoBO.getTeacherAllInfoDTO(courseTeacherDTO.getCreatorId());
            CourseTeacherPopularDTO courseTeacherPopularDTO = new CourseTeacherPopularDTO();
            courseTeacherPopularDTO.setTeacherAllInfoDTO(teacherAllInfoDTO);
            courseTeacherPopularDTO.setCourseTeacherDTO(courseTeacherDTO);
            courseTeacherCAO.updateCourseTeacherDTO(courseTeacherDTO.getTechnicTagEnum(), courseTeacherPopularDTO);
        }

        System.out.println("更新课程之后，完成对缓存的更新");
    }

    /**
     * 创建课程
     * @param courseTeacherDTO
     */
    @AfterReturning(returning = "courseTeacherDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_teacher.SaveCourseTeacherDAO.save(..))")
    public void addCourseTeacherDTOToCache(CourseDTO courseTeacherDTO){
        if(courseTeacherDTO != null){
//            courseTeacherCAO.addNewCourseTeacherToCache(courseTeacherDTO);
            TeacherAllInfoDTO teacherAllInfoDTO = teacherInfoBO.getTeacherAllInfoDTO(courseTeacherDTO.getCreatorId());
            CourseTeacherPopularDTO courseTeacherPopularDTO = new CourseTeacherPopularDTO();
            courseTeacherPopularDTO.setTeacherAllInfoDTO(teacherAllInfoDTO);
            courseTeacherPopularDTO.setCourseTeacherDTO(courseTeacherDTO);
            courseTeacherCAO.addCourseTeacherPopularDTOToCache(courseTeacherPopularDTO,courseTeacherDTO.getTechnicTagEnum(), DirectionEnum.head);
        }
        System.out.println("添加课程之后，完成对缓存的更新");
    }

    
}
