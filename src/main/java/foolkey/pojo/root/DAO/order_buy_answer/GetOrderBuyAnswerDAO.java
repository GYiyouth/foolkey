package foolkey.pojo.root.DAO.order_buy_answer;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.dto.OrderBuyAnswerDTO;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getOrderBuyAnswerDAO")
public class GetOrderBuyAnswerDAO extends GetBaseDAO<OrderBuyAnswerDTO> {


    public OrderBuyAnswerDTO getByOnlookerAndQuestion(StudentDTO onlookerDTO, QuestionAnswerDTO questionAnswerDTO) throws Exception {
        String hql = "from OrderBuyAnswerDTO oba where oba.userId = ? and oba.questionId = ?";
        List<OrderBuyAnswerDTO> list = find(hql, onlookerDTO.getId(), questionAnswerDTO.getId());
        if (list.size() > 0)
            return list.get(0);
        return null;
    }
}
