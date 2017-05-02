package foolkey.aop.cacheMaintain;

import foolkey.pojo.root.CAO.CourseTeacher.CourseTeacherCAO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import foolkey.tool.StaticVariable;
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
     * 添加搜索结果到缓存
     * @param jp
     * @param courseTeacherDTOS
     */
    @AfterReturning(returning = "courseTeacherDTOS",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_teacher.*.findCourseTeacherByPageLessCache(..))"  )
    public void addCourseTeacherDTOToCache(JoinPoint jp, ArrayList<CourseTeacherDTO> courseTeacherDTOS){
        System.out.println("参数为：");
        System.out.println(jp.getArgs()[0]);
        System.out.println(jp.getArgs()[1]);
        if (courseTeacherDTOS != null)
            courseTeacherCAO.addCourseTeacherPopular((TechnicTagEnum) jp.getArgs()[0],courseTeacherDTOS);
        System.out.println("AOP实现了courseTeacherDTO缓存的更新!!");
    }

    /**
     * 自动化添加课程到缓存
     * @param jp
     * @param courseTeacherDTOS
     */
    @AfterReturning(returning = "courseTeacherDTOS",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_teacher.*.autoAddCourseTeacherDTOToCache(..))"  )
    public void autoAddCourseTeacherDTOToCache(JoinPoint jp, ArrayList<CourseTeacherDTO> courseTeacherDTOS){
        if (courseTeacherDTOS != null) {
            courseTeacherCAO.autoAddCourseTeacherToCache((TechnicTagEnum) jp.getArgs()[0], courseTeacherDTOS);
        }
        System.out.println("AOP实现了courseTeacherDTO缓存的自动更新!!");
    }
    @AfterReturning(returning = "courseTeacherDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_teacher.*.update(..))"  )
    public void updateCourseTeacherDTO(JoinPoint jp,CourseTeacherDTO courseTeacherDTO){
        if (courseTeacherDTO != null) {
            courseTeacherCAO.updateCourseTeacherDTO((TechnicTagEnum) jp.getArgs()[0], courseTeacherDTO);
        }
        System.out.println("AOP实现了courseTeacherDTO缓存的自动更新!!");
    }

    @AfterReturning(returning = "courseTeacherDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_teacher.SaveCourseTeacherDAO.save())")
    public void addCourseTeacherDTOToCache(CourseTeacherDTO courseTeacherDTO){
        if(courseTeacherDTO != null){
            if(courseTeacherCAO.getTechnicTagCourseTeacherLength(courseTeacherDTO.getTechnicTagEnum())<StaticVariable.cacheSize){
                courseTeacherCAO.addCourseTeacherToCache(courseTeacherDTO);
            }
        }
    }
}
