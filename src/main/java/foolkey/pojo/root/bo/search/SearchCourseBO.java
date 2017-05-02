package foolkey.pojo.root.bo.search;

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

    public List<CourseTeacherDTO> searchCourseDTO(ArrayList<String> keyList, ArrayList<TechnicTagEnum> techList, ArrayList<CourseTimeDayEnum>timeList, int pageNo){

        //如果只有技术关键字，则从缓存中取
        if (keyList.size() == 0 && timeList.size() == 0 && )
        return null;
    }
}
