package foolkey.pojo.root.bo.question;

import foolkey.pojo.root.DAO.question_answer.GetQuestionAnswerDAO;
import foolkey.pojo.root.DAO.question_answer.SaveQuestionAnswerDAO;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by GR on 2017/5/20.
 */
@Service
@Transactional
public class QuestionBO {

    @Autowired
    private SaveQuestionAnswerDAO saveQuestionAnswerDAO;
    @Autowired
    private GetQuestionAnswerDAO getQuestionAnswerDAO;

    /**
     * 创建问题、回答问题都可以用这个
     * @param questionAnswerDTO
     */
    public QuestionAnswerDTO createQuestionAnswer(QuestionAnswerDTO questionAnswerDTO){
        saveQuestionAnswerDAO.save(questionAnswerDTO);
        return questionAnswerDTO;
    }


    /**
     * 根据问题-回答id，获取DTO
     * @param questionAnswerId
     * @return
     */
    public QuestionAnswerDTO getQuestionAnswerDTOByQuestionAnswerId(Long questionAnswerId){
        return getQuestionAnswerDAO.get(QuestionAnswerDTO.class, questionAnswerId);
    }

//    public

}
