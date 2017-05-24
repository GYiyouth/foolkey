package foolkey.pojo.root.DAO.evaluation_course;

import foolkey.pojo.root.DAO.base.SaveBaseDAO;
import foolkey.pojo.root.vo.dto.EvaluationCourseDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("saveEvaluationCourseDAO")
public class SaveEvaluationCourseDAO extends SaveBaseDAO<EvaluationCourseDTO>{
    @Override
    public EvaluationCourseDTO save(EvaluationCourseDTO entity) {
        return super.save(entity);
    }
}
