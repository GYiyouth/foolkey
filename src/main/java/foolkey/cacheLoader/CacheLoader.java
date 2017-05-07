package foolkey.cacheLoader;

import foolkey.pojo.root.bo.CourseStudent.CourseStudentBO;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.tool.BeanFactory;
import foolkey.tool.StaticVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * Created by GR on 2017/5/3.
 */
@Component
//public class CacheLoader{}
public class CacheLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CourseTeacherBO courseTeacherBO;
    @Autowired
    private CourseStudentBO courseStudentBO;
    @Autowired
    private StudentInfoBO studentInfoBO;


    /**
     * 预热程序，填充缓存中的信息
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        if (contextRefreshedEvent.getApplicationContext().getParent() != null)//root application context 没有parent，他就是老大.
        if(contextRefreshedEvent.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
            for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
                //1.添加热门课程到缓存中
                System.out.println("预热程序，热门课程类别：" + technicTagEnum);
                courseTeacherBO.fillCourseTeacherPopularDTOToCache(technicTagEnum, StaticVariable.cacheSize);
                //2.添加最新的、未解决的悬赏到缓存中
                System.out.println("预热程序，悬赏类别：" + technicTagEnum);
                courseStudentBO.fillCourseStudentPopularDTOToCache(technicTagEnum, StaticVariable.cacheSize);
                //3.添加学生信息到缓存
                System.out.println("预热程序，学生信息：");
                studentInfoBO.fillStudentDTOToCache();
            }
        }
    }
}
