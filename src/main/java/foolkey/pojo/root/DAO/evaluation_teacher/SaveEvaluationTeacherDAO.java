package foolkey.pojo.root.DAO.evaluation_teacher;

import foolkey.pojo.root.DAO.base.SaveBaseDAO;
import foolkey.pojo.root.vo.dto.EvaluationTeacherDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("saveEvaluationTeacherDAO")
public class SaveEvaluationTeacherDAO extends SaveBaseDAO<EvaluationTeacherDTO>{
    @Override
    public EvaluationTeacherDTO save(EvaluationTeacherDTO entity) {
        return super.save(entity);
    }
}
