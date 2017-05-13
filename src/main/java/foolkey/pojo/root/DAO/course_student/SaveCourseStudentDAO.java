package foolkey.pojo.root.DAO.course_student;

import foolkey.pojo.root.DAO.base.SaveBaseDAO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("saveCourseStudentDAO")
@Transactional
public class SaveCourseStudentDAO extends SaveBaseDAO<RewardDTO>{

    /**
     * 重写方法，为了能够顺利通过AOP
     * @param courseStudentDTO
     * @return
     */
    @Override
    public RewardDTO save(RewardDTO courseStudentDTO) {
        super.save(courseStudentDTO);
        return courseStudentDTO;
    }
}
