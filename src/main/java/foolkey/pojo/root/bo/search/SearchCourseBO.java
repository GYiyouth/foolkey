package foolkey.pojo.root.bo.search;

import foolkey.pojo.root.DAO.course_teacher.GetCourseTeacherDAO;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.vo.assistObject.CourseTimeDayEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geyao on 2017/5/2.
 */
@Service
public class SearchCourseBO {
    @Autowired
    private CourseTeacherBO courseTeacherBO;
    @Autowired
    private GetCourseTeacherDAO getCourseTeacherDAO;

    public List<CourseTeacherDTO> searchCourseDTO(
            ArrayList<String> keyList
            , ArrayList<TechnicTagEnum> techList
            , ArrayList<CourseTimeDayEnum>timeList
            , int pageNo
    ) throws Exception{

        //如果只有技术关键字，则从缓存中取
        if (keyList.size() == 0 && timeList.size() == 0 && techList.size() > 0)
            return courseTeacherBO.getPopularCourseTeacher(techList.get(0), pageNo, 20);

        //如果，有技术关键词
        if (keyList.size() > 0){
            return getCourseTeacherDAO.findByPage("from CourseTeacherDTO ct where ct.topic like'%?%' ", pageNo, 20, keyList.get(0));
        }
        return null;
    }
}
