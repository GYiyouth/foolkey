package foolkey.pojo.root.bo;

import foolkey.pojo.root.CAO.CourseTeacher.CourseTeacherCAO;
import foolkey.pojo.root.DAO.course_teacher.GetCourseTeacherDAO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
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
        if (courseTeacherCAO.contaionsCourseTeacher(technicTagEnum, pageNo, pageSize))
            return courseTeacherCAO.getCourseTeacherPopular(technicTagEnum,pageNo,pageSize);
        return (ArrayList<CourseTeacherDTO>) getCourseTeacherDAO.findCourseTeacherByPage(technicTagEnum,pageNo,pageSize);
//            return keyCAO.getUserAESKeyDTO(userToken);
//        return aesCoder.getAESKeyBase64();


    }
}
