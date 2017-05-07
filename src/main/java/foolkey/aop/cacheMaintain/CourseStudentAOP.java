package foolkey.aop.cacheMaintain;

import foolkey.pojo.root.CAO.CourseStudent.CourseStudentCAO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.DirectionEnum;
import foolkey.pojo.root.vo.cacheDTO.CourseStudentPopularDTO;
import foolkey.pojo.root.vo.dto.CourseStudentDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ustcg on 2017/5/6.
 */
@Aspect
public class CourseStudentAOP {


    @Autowired
    private CourseStudentCAO courseStudentCAO;
    @Autowired
    private StudentInfoBO studentInfoBO;


    /**
     * 更新悬赏之后对缓存更新
     * @param jp
     * @param courseStudentDTO
     */
    @AfterReturning(returning = "courseStudentDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_student.*.update(..))"  )
    public void updateCourseStudentDTO(JoinPoint jp, CourseStudentDTO courseStudentDTO){
        if(courseStudentDTO != null){
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(courseStudentDTO.getCreatorId());
            CourseStudentPopularDTO courseStudentPopularDTO = new CourseStudentPopularDTO();
            courseStudentPopularDTO.setStudentDTO(studentDTO);
            courseStudentPopularDTO.setCourseStudentDTO(courseStudentDTO);
            courseStudentCAO.updateCourseStudentDTO(courseStudentDTO.getTechnicTagEnum(), courseStudentPopularDTO);
        }

        System.out.println("更新悬赏之后，完成对缓存的更新");
    }

    /**
     * 创建悬赏
     * @param courseStudentDTO
     */
    @AfterReturning(returning = "courseStudentDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_student.SaveCourseStudentDAO.save(..))")
    public void addCourseTeacherDTOToCache(CourseStudentDTO courseStudentDTO){
        if(courseStudentDTO != null){
//            courseTeacherCAO.addNewCourseTeacherToCache(courseTeacherDTO);
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(courseStudentDTO.getCreatorId());
            CourseStudentPopularDTO courseStudentPopularDTO = new CourseStudentPopularDTO();
            courseStudentPopularDTO.setCourseStudentDTO(courseStudentDTO);
            courseStudentPopularDTO.setStudentDTO(studentDTO);
            courseStudentCAO.addCourseStudentPopularDTOToCache(courseStudentDTO.getTechnicTagEnum(), courseStudentPopularDTO, DirectionEnum.head);
        }
        System.out.println("添加悬赏之后，完成对缓存的更新");
    }


}
