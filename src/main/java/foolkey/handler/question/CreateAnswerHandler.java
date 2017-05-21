package foolkey.handler.question;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.question.QuestionBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.QuestionStateEnum;
import foolkey.pojo.root.vo.dto.OrderAskQuestionDTO;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.Time;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 回答问题
 * Created by GR on 2017/5/21.
 */
@Service
public class CreateAnswerHandler extends AbstractBO {

    @Autowired
    private QuestionBO questionBO;
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) throws Exception {
        //读取客户端传入参数
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        //获取原始信息
        String token = clearJSON.getString("token");

        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        Long askerId = studentDTO.getId();

        //问题id
        Long questionId = clearJSON.getLong("questionId");
        //提问订单id
        Long orderAskQuestionId = clearJSON.getLong("orderAskQuestionId");
        String answerContent = clearJSON.getString("answerContent");

        //创建一个问题DTO
        QuestionAnswerDTO questionAnswerDTO = questionBO.getQuestionAnswerDTOByQuestionAnswerId(questionId);
        questionAnswerDTO.setAnswerContent(answerContent);
        questionAnswerDTO.setAnswerTime(Time.getCurrentDate());
        questionAnswerDTO.setLastUpdateTime(Time.getCurrentDate());
        questionAnswerDTO.setInvalidTime(Time.getPermanentDate());
        questionAnswerDTO.setQuestionStateEnum(QuestionStateEnum.已回答);
        //  1. 存储问题-回答DTO
        questionBO.createQuestionAnswer(questionAnswerDTO);
        //  2.修改订单DTO
        OrderAskQuestionDTO orderAskQuestionDTO = orderInfoBO.getOrderAskQuestionDTOByOrderAskQuestionId(orderAskQuestionId);
        orderInfoBO.updateOrderSateAfterAnswerAsAnswer(orderAskQuestionDTO);
        //  3.给回答者赚钱
        /***********  暂时没写   ******************************/

        //返回result
        jsonObject.put("result", "success");
        jsonObject.put("questionAnswerDTO", questionAnswerDTO);
        jsonObject.put("orderAskQuestionDTO", orderAskQuestionDTO);
        jsonHandler.sendJSON(jsonObject, response);
    }

}
