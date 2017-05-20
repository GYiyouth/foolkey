package foolkey.pojo.root.bo.question;

import foolkey.pojo.root.DAO.question_answer.GetQuestionAnswerDAO;
import foolkey.pojo.root.DAO.question_answer.SaveQuestionAnswerDAO;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by GR on 2017/5/20.
 */
public class QuestionBO {

    @Autowired
    private SaveQuestionAnswerDAO saveQuestionAnswerDAO;
    @Autowired
    private GetQuestionAnswerDAO getQuestionAnswerDAO;

    /**
     * 创建问题
     * @param questionAnswerDTO
     */
    public void createQuestion(QuestionAnswerDTO questionAnswerDTO){
        if(questionAnswerDTO != null){
            saveQuestionAnswerDAO.save(questionAnswerDTO);
        }
    }


    /**
     * 根据问题-回答id，获取DTO
     * @param questionAnswerId
     * @return
     */
    public QuestionAnswerDTO getQuestionAnswerDTOByQuestionAnswerId(Long questionAnswerId){
        return getQuestionAnswerDAO.get(QuestionAnswerDTO.class, questionAnswerId);
    }
}
