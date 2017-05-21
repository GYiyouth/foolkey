package foolkey.pojo.root.DAO.question_answer;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.assistObject.QuestionStateEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.StaticVariable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static foolkey.tool.StaticVariable.PAGE_SIZE;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getQuestionAnswerDAO")
public class GetQuestionAnswerDAO extends GetBaseDAO<QuestionAnswerDTO> {

    /**
     * 根据问题状态，分页获取的我回答的问题（待回答、已回答）
     *
     * @param answererDTO
     * @param questionStateEnum
     * @param pageNo
     * @return
     */
    public List<QuestionAnswerDTO> getQuestionAsAnswer(StudentDTO answererDTO, QuestionStateEnum questionStateEnum, Integer pageNo) {
        String hql = "from foolkey.pojo.root.vo.dto.QuestionAnswerDTO qa where qa.answerId = ? and qa.questionStateEnum = ? order by qa.lastUpdateTime desc ";
        return findByPage(hql, pageNo, PAGE_SIZE, answererDTO.getId(), questionStateEnum);

    }

    /**
     * 根据问题状态，分页获取的我提出的问题（待回答、已回答）
     *
     * @param askerDTO
     * @param questionStateEnum
     * @param pageNo
     * @return
     */
    public List<QuestionAnswerDTO> getQuestionAsAsker(StudentDTO askerDTO, QuestionStateEnum questionStateEnum, Integer pageNo) {
        String hql = "from foolkey.pojo.root.vo.dto.QuestionAnswerDTO qa where qa.askerId = ? and qa.questionStateEnum = ? order by qa.lastUpdateTime desc ";
        return findByPage(hql, pageNo, PAGE_SIZE, askerDTO.getId(), questionStateEnum);
    }

    /**
     * 获取到“热门”的提问
     * 已回答、且提问者-回答者都不是自己
     *
     * @param studentDTO
     * @param technicTagEnum
     * @param pageNo
     * @return
     * @throws Exception
     */
    public List<QuestionAnswerDTO> getPopularQuestionAnswerDTO(StudentDTO studentDTO, TechnicTagEnum technicTagEnum, Integer pageNo) throws Exception {
        String hql = "from foolkey.pojo.root.vo.dto.QuestionAnswerDTO qa where qa.askerId != ? and qa.answerId != ? and qa.technicTagEnum = ? and qa.questionStateEnum = ? group by qa.lastUpdateTime desc ";
        return findByPage(hql, pageNo, StaticVariable.PAGE_SIZE, studentDTO.getId(), studentDTO.getId(), technicTagEnum, QuestionStateEnum.已回答);
    }
}
