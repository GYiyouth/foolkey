package foolkey.pojo.root.DAO.course_teacher;

import foolkey.pojo.root.DAO.base.SaveBaseDAO;
import foolkey.pojo.root.vo.dto.CourseDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("saveCourseTeacherDAO")
public class SaveCourseTeacherDAO extends SaveBaseDAO<CourseDTO>{

    @Override
    public CourseDTO save(CourseDTO courseTeacherDTO) {
        super.save(courseTeacherDTO);
        return courseTeacherDTO;
    }
}
