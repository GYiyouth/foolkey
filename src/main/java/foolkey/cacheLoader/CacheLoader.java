package foolkey.cacheLoader;

import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.tool.BeanFactory;
import foolkey.tool.StaticVariable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * Created by GR on 2017/5/3.
 */
@Component
//public class CacheLoader{}
public class CacheLoader implements ApplicationListener<ContextRefreshedEvent> {


    /**
     * 预热程序，填充缓存中的信息
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        if (contextRefreshedEvent.getApplicationContext().getParent() != null)//root application context 没有parent，他就是老大.
        if(contextRefreshedEvent.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
            System.out.println("name:" + contextRefreshedEvent.getApplicationContext().getDisplayName());
            for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
                //1.添加热门课程到缓存中
                System.out.println("预热程序，热门课程类别：" + technicTagEnum);
                CourseTeacherBO courseTeacherBO = BeanFactory.getBean("courseTeacherBO", CourseTeacherBO.class);
                courseTeacherBO.fillCourseTeacherPopularDTOToCache(technicTagEnum, StaticVariable.cacheSize);
            }
        }
    }
}
