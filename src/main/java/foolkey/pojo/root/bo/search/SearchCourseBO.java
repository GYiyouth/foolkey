package foolkey.pojo.root.bo.search;

import foolkey.pojo.root.DAO.course_teacher.GetCourseTeacherDAO;
import foolkey.pojo.root.bo.course.CourseBO;
import foolkey.pojo.root.vo.assistObject.CourseTimeDayEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.send_to_client.course.CourseWithTeacherSTCDTO;
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
    private CourseBO courseTeacherBO;
    @Autowired
    private GetCourseTeacherDAO getCourseTeacherDAO;

    public List<CourseWithTeacherSTCDTO> searchCourseDTO(
            ArrayList<String> keyList
            , ArrayList<TechnicTagEnum> techList
            , ArrayList<CourseTimeDayEnum>timeList
            , int pageNo
    ) throws Exception{

        //如果只有技术关键字，则从缓存中取
        if (keyList.size() == 0 && timeList.size() == 0 && techList.size() > 0) {
            List<CourseWithTeacherSTCDTO> courseWithTeacherSTCDTOS = courseTeacherBO.getCourseWithTeacherSTCDTO(techList.get(0), pageNo, 20);
            return courseWithTeacherSTCDTOS;
        }
        //如果，有技术关键词
        if (keyList.size() > 0){
            List<CourseDTO> courseTeacherDTOS =  getCourseTeacherDAO.findByPage("from CourseTeacherDTO ct where ct.topic like ? ", pageNo, 20, "%" + keyList.get(0) + "%" );
            return courseTeacherBO.convertCourseDTOIntoCourseWithTeacherDTO(courseTeacherDTOS);
        }
        return new ArrayList<>();
    }
}
