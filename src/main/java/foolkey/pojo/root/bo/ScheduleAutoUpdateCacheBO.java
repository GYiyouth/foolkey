package foolkey.pojo.root.bo;

import foolkey.tool.cache.Cache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 自动任务
 * Created by ustcg on 2017/4/30.
 */
@Service
public class ScheduleAutoUpdateCacheBO {

    @Resource(name = "localCache")
    private Cache cache;
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

    /**
     * 每2分钟发送一次请求，维护缓存连接
     */
    @Scheduled(cron = "0 0/2 * * * ? ")
    public void cacheMaintain(){
        cache.getString("key_YY_rsaKeyDTO");
    }
}
