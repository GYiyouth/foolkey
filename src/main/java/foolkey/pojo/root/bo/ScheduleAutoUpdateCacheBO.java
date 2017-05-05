package foolkey.pojo.root.bo;

import foolkey.pojo.root.DAO.course_teacher.GetCourseTeacherDAO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自动任务
 * Created by ustcg on 2017/4/30.
 */
//@Component("myJob")
public class ScheduleAutoUpdateCacheBO {
//
//    @Resource(name = "getCourseTeacherDAO")
//    private GetCourseTeacherDAO getCourseTeacherDAO;
//
//    public ScheduleAutoUpdateCacheBO() {
//        System.out.println("创建自动任务...");
//    }
//
//    @Scheduled(cron = "0 0 0/2 * * ? ")//每隔2小时
//    public void run(){
//        System.out.println("Hello ScheduleAutoUpdateCacheBO  "+
//                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
//        for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
//            System.out.println("更新"+technicTagEnum.name());
//            getCourseTeacherDAO.autoAddCourseTeacherDTOToCache(technicTagEnum);
//            System.out.println("ii?");
//        }
//
//    }
}
