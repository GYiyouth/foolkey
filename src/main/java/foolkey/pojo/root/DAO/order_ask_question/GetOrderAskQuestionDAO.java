package foolkey.pojo.root.DAO.order_ask_question;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.dto.OrderAskQuestionDTO;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getOrderAskQuestionDAO")
public class GetOrderAskQuestionDAO extends GetBaseDAO<OrderAskQuestionDTO> {

    /**
     * 根据回答者、问题获取到订单
     * @param questionAnswerDTO
     * @param answererDTO
     * @return
     */
    public OrderAskQuestionDTO getByQuestionAndAnswer(QuestionAnswerDTO questionAnswerDTO, StudentDTO answererDTO) {
        String hql = "from OrderAskQuestionDTO oaq where oap.receiverId = ? and oaq.questionId = ?";
        List<OrderAskQuestionDTO> list = find(hql, answererDTO.getId(), questionAnswerDTO.getId());
        if (list.size() > 0)
            return list.get(0);
        return null;
    }
}
