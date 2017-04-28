package foolkey.pojo.root.bo.CourseTeacher;

import foolkey.pojo.root.CAO.CourseTeacher.CourseTeacherCAO;
import foolkey.pojo.root.DAO.course_teacher.GetCourseTeacherDAO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import foolkey.tool.StaticVariable;
import foolkey.tool.cache.Cache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by admin on 2017/4/26.
 */
@Service("courseTeacherBO")
public class CourseTeacherBO {

    @Resource(name = "localCache")
    private Cache cache;

    @Resource(name = "courseTeacherCAO")
    private CourseTeacherCAO courseTeacherCAO;

    @Resource(name = "getCourseTeacherDAO")
    private GetCourseTeacherDAO getCourseTeacherDAO;

    /**
     * 获取某个标签下流行的课程
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ArrayList<CourseTeacherDTO> getPopularCourseTeacher(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize) throws Exception{
        //请求的内容超过内存大小
        if((pageNo-1)*pageSize >= StaticVariable.cacheSize) {
            return  getCourseTeacherDAO.findCourseTeacherByPageMoreCache(technicTagEnum,pageNo,pageSize);
        }else{
            if (courseTeacherCAO.contaionsCourseTeacher(technicTagEnum, pageNo, pageSize)) {
                System.out.println("缓存有！");
                return courseTeacherCAO.getCourseTeacherPopular(technicTagEnum, pageNo, pageSize);
            }else {
                System.out.println("缓存没有");
                return getCourseTeacherDAO.findCourseTeacherByPageLessCache(technicTagEnum, pageNo, pageSize);
            }
        }
    }
}
