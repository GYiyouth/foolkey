package foolkey.pojo.root.bo.search;

import foolkey.pojo.root.DAO.course_student.GetCourseStudentDAO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geyao on 2017/5/2.
 */
@Service
public class SearchRewardBO {

    @Autowired
    private GetCourseStudentDAO getCourseStudentDAO;

    public List<CourseStudentDTO> searchRewardDTO(ArrayList<String> keyList, ArrayList<TechnicTagEnum> techList, int pageNo){
        List<CourseStudentDTO> result = new ArrayList<>();
        getCourseStudentDAO.findByPage("from foolkey.pojo.root.vo.dto.CourseStudentDTO course " +
                        " where course.topic like '%?%' "
                , pageNo, 10
                , keyList.size() > 0? keyList.get(0) : "");

        return null;
    }
}
