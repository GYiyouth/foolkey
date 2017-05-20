package foolkey.aop.cacheMaintain;

import foolkey.pojo.root.CAO.Course.CourseCAO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.DirectionEnum;
import foolkey.pojo.send_to_client.TeacherAllInfoDTO;
import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.send_to_client.course.CourseWithTeacherSTCDTO;
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
    private CourseCAO courseCAO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;


    /**
     * 更新课程信息之后的操作
     * @param jp
     * @param courseDTO
     */
    @AfterReturning(returning = "courseDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_teacher.*.update(..))"  )
    public void updateCourseTeacherDTO(JoinPoint jp, CourseDTO courseDTO) throws Exception{
        if(courseDTO != null){
            TeacherAllInfoDTO teacherAllInfoDTO = teacherInfoBO.getTeacherAllInfoDTO(courseDTO.getCreatorId());
            CourseWithTeacherSTCDTO courseWithTeacherSTCDTO = new CourseWithTeacherSTCDTO();
            courseWithTeacherSTCDTO.setTeacherAllInfoDTO(teacherAllInfoDTO);
            courseWithTeacherSTCDTO.setCourseDTO(courseDTO);
            courseCAO.updateCourseWithTeacher(courseDTO.getTechnicTagEnum(), courseWithTeacherSTCDTO);
        }

        System.out.println("更新课程之后，完成对缓存的更新");
    }

    /**
     * 创建课程
     * @param courseDTO
     */
    @AfterReturning(returning = "courseDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_teacher.SaveCourseTeacherDAO.save(..))")
    public void addCourseTeacherDTOToCache(CourseDTO courseDTO)throws Exception{
        if(courseDTO != null){
//            courseCAO.addNewCourseTeacherToCache(courseTeacherDTO);
            TeacherAllInfoDTO teacherAllInfoDTO = teacherInfoBO.getTeacherAllInfoDTO(courseDTO.getCreatorId());
            CourseWithTeacherSTCDTO courseWithTeacherSTCDTO = new CourseWithTeacherSTCDTO();
            courseWithTeacherSTCDTO.setTeacherAllInfoDTO(teacherAllInfoDTO);
            courseWithTeacherSTCDTO.setCourseDTO(courseDTO);
            courseCAO.addCourseWithTeacherToCache(courseDTO.getTechnicTagEnum(), courseWithTeacherSTCDTO, DirectionEnum.head);
        }
        System.out.println("添加课程之后，完成对缓存的更新");
    }

    
}
