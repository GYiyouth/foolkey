package foolkey.pojo.root.bo.question;

import foolkey.pojo.root.DAO.question_answer.GetQuestionAnswerDAO;
import foolkey.pojo.root.DAO.question_answer.SaveQuestionAnswerDAO;
import foolkey.pojo.root.DAO.question_answer.UpdateQuestionAnswerDAO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.QuestionStateEnum;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.TeacherAllInfoDTO;
import foolkey.pojo.send_to_client.question.QuestionAskerAnswerSTCDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private UpdateQuestionAnswerDAO updateQuestionAnswerDAO;
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;

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

    /**
     * 修改提问的DTO
     * @param questionAnswerDTO
     * @return
     */
    public QuestionAnswerDTO updateQuestionAnswerDTO(QuestionAnswerDTO questionAnswerDTO){
        updateQuestionAnswerDAO.update(questionAnswerDTO);
        return questionAnswerDTO;
    }

    /**
     * 根据问题状态，分页获取的我回答的问题（待回答、已回答）
     * @param answererDTO
     * @param questionStateEnum
     * @param pageNo
     * @return
     */
    public List<QuestionAnswerDTO> getQuestionAsAnswer(StudentDTO answererDTO, QuestionStateEnum questionStateEnum, Integer pageNo){
        return getQuestionAnswerDAO.getQuestionAsAnswer(answererDTO, questionStateEnum, pageNo);
    }

    /**
     * 根据问题状态，分页获取的我提出的问题（待回答、已回答）
     * @param askerDTO
     * @param questionStateEnum 问题状态（待回答、已回答）
     * @param pageNo
     * @return
     */
    public List<QuestionAnswerDTO> getQuestionAsAsker(StudentDTO askerDTO, QuestionStateEnum questionStateEnum, Integer pageNo){
        return getQuestionAnswerDAO.getQuestionAsAsker(askerDTO, questionStateEnum, pageNo);
    }


    /**
     * 问题DTO封装成问题-提问者-回答者DTO
     * @param questionAnswerDTOS
     * @return
     */
    public List<QuestionAskerAnswerSTCDTO> convertQuestionAnswerDTOToQuestionAskerAnswerSTCDTO(List<QuestionAnswerDTO> questionAnswerDTOS) throws Exception {

        List<QuestionAskerAnswerSTCDTO> questionAskerAnswerSTCDTOS = new ArrayList<>();

        for(QuestionAnswerDTO questionAnswerDTO:questionAnswerDTOS){
            //封装成的对象
            QuestionAskerAnswerSTCDTO questionAskerAnswerSTCDTO = new QuestionAskerAnswerSTCDTO();

            StudentDTO askerDTO = studentInfoBO.getStudentDTO(questionAnswerDTO.getAskerId());
            TeacherAllInfoDTO answererDTO = teacherInfoBO.getTeacherAllInfoDTO(questionAnswerDTO.getAnswerId());

            questionAskerAnswerSTCDTO.setAnswererDTO(answererDTO);
            questionAskerAnswerSTCDTO.setAskerDTO(askerDTO);
            questionAskerAnswerSTCDTO.setQuestionAnswerDTO(questionAnswerDTO);
            questionAskerAnswerSTCDTOS.add(questionAskerAnswerSTCDTO);
        }

        return questionAskerAnswerSTCDTOS;
    }
}
