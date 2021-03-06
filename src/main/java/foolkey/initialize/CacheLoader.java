package foolkey.initialize;

import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.course.CourseBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.tool.StaticVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import static foolkey.tool.UploadFileTencent.download;
import static foolkey.tool.UploadFileTencent.getDefaultPhotoCostPath;
import static foolkey.tool.UploadFileTencent.getUserPhotoCosPath;


/**
 * Created by GR on 2017/5/3.
 */
@Component
//public class CacheLoader{}
public class CacheLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CourseBO courseTeacherBO;
    @Autowired
    private RewardBO courseStudentBO;
    @Autowired
    private StudentInfoBO studentInfoBO;

    //下载一些默认图片到本地
    private static Integer defaultPicNums = 14;

    /**
     * 预热程序，填充缓存中的信息
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        if (contextRefreshedEvent.getApplicationContext().getParent() != null)//root application context 没有parent，他就是老大.
        if(contextRefreshedEvent.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
            //初始化
            //事先在缓存分配空间给悬赏、课程
            courseTeacherBO.initCourseCache();
            courseStudentBO.initRewardCache();

            //1.    添加学生-老师信息到缓存
            studentInfoBO.fillStudentDTOToCache();
            //2.    添加token_id,id_token
            studentInfoBO.fillTokenIdAndIdToken();


            for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
                //3.    添加热门课程到缓存中
                System.out.println("预热程序，热门课程类别：" + technicTagEnum);
                courseTeacherBO.fillCourseWithTeacherSTCDTOToCache(technicTagEnum, StaticVariable.CACHE_SIZE);
                //4.    添加最新的、未解决的悬赏到缓存中
                System.out.println("预热程序，悬赏类别：" + technicTagEnum);
                courseStudentBO.fillRewardWithStudentDTOToCache(technicTagEnum, StaticVariable.CACHE_SIZE);
            }
        }

        for (int i = 0; i <= defaultPicNums ; i++ ){
            System.out.println(" UploadFileTencet " + " 30行 ");
            System.out.println("下载第" + i + "张图片");
            System.out.println( download( getDefaultPhotoCostPath( i ) ) ) ;
        }
    }
}
