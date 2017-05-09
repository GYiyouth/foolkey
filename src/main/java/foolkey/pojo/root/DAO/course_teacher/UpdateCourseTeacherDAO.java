package foolkey.pojo.root.DAO.course_teacher;

import foolkey.pojo.root.DAO.base.UpdateBaseDAO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("updateCourseTeacherDAO")
@Transactional
public class UpdateCourseTeacherDAO extends UpdateBaseDAO<CourseDTO>{


    public CourseDTO update(TechnicTagEnum technicTagEnum, CourseDTO entity) {
        return super.update(entity);
    }
}
