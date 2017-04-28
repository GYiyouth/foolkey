package foolkey.aop.cacheMaintain;

import foolkey.pojo.root.CAO.CourseTeacher.CourseTeacherCAO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ustcg on 2017/4/28.
 */
@Aspect
public class CourseTeacherAOP {

    @Resource(name = "courseTeacherCAO")
    private CourseTeacherCAO courseTeacherCAO;

    /**
     *
     * @param jp
     * @param courseTeacherDTOS
     */
    @AfterReturning(returning = "courseTeacherDTOS",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_teacher.*.findCourseTeacherByPageLessCache(..))"  )
    public void addCourseTeacherDTO(JoinPoint jp, ArrayList<CourseTeacherDTO> courseTeacherDTOS){
        System.out.println("参数为：");
        System.out.println(jp.getArgs()[0]);
        if (courseTeacherDTOS != null)
            courseTeacherCAO.addCourseTeacherPopular((TechnicTagEnum) jp.getArgs()[0],courseTeacherDTOS);
        System.out.println("AOP实现了服务器RSAKey缓存的更新!!");
    }
}
