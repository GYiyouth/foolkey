package foolkey.pojo.root.DAO.question_answer;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.assistObject.QuestionStateEnum;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

import static foolkey.tool.StaticVariable.PAGE_SIZE;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getQuestionAnswerDAO")
public class GetQuestionAnswerDAO extends GetBaseDAO<QuestionAnswerDTO>{

    /**
     * 分页获取的我回答的问题（待回答、已回答）
     * @param answererDTO
     * @param questionStateEnum
     * @param pageNo
     * @return
     */
    public List<QuestionAnswerDTO> getQuestionAsAnswer(StudentDTO answererDTO, QuestionStateEnum questionStateEnum, Integer pageNo){
        String hql = "from foolkey.pojo.root.vo.dto.QuestionAnswerDTO qa where qa.answerId = ? and qa.questionStateEnum = ? group by qa.lastUpdateTime desc ";
        return findByPage(hql, pageNo, PAGE_SIZE, answererDTO.getId(), questionStateEnum);

    }
}
