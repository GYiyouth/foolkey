package foolkey.pojo.root.DAO.course_student;

import foolkey.pojo.root.DAO.base.DeleteBaseDAO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("deleteCourseStudentDAO")
public class DeleteCourseStudentDAO extends DeleteBaseDAO<RewardDTO> {

    @Override
    public void delete(RewardDTO entity) {
        super.delete(entity);
    }

}
