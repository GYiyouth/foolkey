package foolkey.pojo.root.bo.order;

import foolkey.pojo.root.DAO.order_ask_question.SaveOrderAskQuestionDAO;
import foolkey.pojo.root.DAO.question_answer.SaveQuestionAnswerDAO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.OrderAskQuestionDTO;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.Time;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 提问-回答的各种BO
 * Created by GR on 2017/5/21.
 */
public class OrderAskQuestionBO {

    @Autowired
    private SaveOrderAskQuestionDAO saveOrderAskQuestionDAO;

    /**
     * 创建提问问题的订单DTO
     * @param studentDTO 提问者的DTO
     * @param questionAnswerDTO 问题DTO
     */
    public OrderAskQuestionDTO createOrderAsk(StudentDTO studentDTO, QuestionAnswerDTO questionAnswerDTO) throws Exception {
        //生成一个订单对象
        OrderAskQuestionDTO orderAskQuestionDTO = new OrderAskQuestionDTO();
        //各种对订单赋值
        orderAskQuestionDTO.setUserId(studentDTO.getId());
        orderAskQuestionDTO.setQuestionId(questionAnswerDTO.getId());
        orderAskQuestionDTO.setAmount(questionAnswerDTO.getPrice());
        orderAskQuestionDTO.setCreatedTime(Time.getCurrentDate());
        orderAskQuestionDTO.setExistingTime(Time.getOrderAskQuestionExistingDate());
        orderAskQuestionDTO.setOrderStateEnum(OrderStateEnum.未付款);
        orderAskQuestionDTO.setReceiverId(questionAnswerDTO.getAnswerId());
        //保存这个订单
        saveOrderAskQuestionDAO.save(orderAskQuestionDTO);
        return orderAskQuestionDTO;
    }
}
