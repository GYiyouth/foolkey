package foolkey.handler.question;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.question.QuestionBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.QuestionStateEnum;
import foolkey.pojo.root.vo.dto.OrderAskQuestionDTO;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.TeacherAllInfoDTO;
import foolkey.tool.Time;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static foolkey.tool.constant_values.MoneyRate.TEACHER_GET_MONEY_RATE;
import static foolkey.tool.constant_values.MoneyRate.VIRTUAL_REAL_RATE;

/**
 * 已认证老师回答问题
 * <p>
 * 参数：
 * token：用户标识：String
 * questionId：问题的id：Long
 * answerContent：回答的内容：String
 * <p>
 * 返回：
 * questionAnswerDTO：问题的DTO：QuestionAnswerDTO
 * orderAskQuestionDTO：订单DTO：OrderAskQuestionDTO
 * <p>
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
    @Autowired
    private MessageBO messageBO;

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

        //用不到老师那部分信息，所以只需取用学生那个表的信息
        StudentDTO answererDTO = studentInfoBO.getStudentDTO(token);
//        Long answerId = studentDTO.getId();

        //问题id
        Long questionId = clearJSON.getLong("questionId");
        //回答内容
        String answerContent = clearJSON.getString("answerContent");

        //获取到问题的DTO
        QuestionAnswerDTO questionAnswerDTO = questionBO.getQuestionAnswerDTOByQuestionAnswerId(questionId);
        questionAnswerDTO.setAnswerContent(answerContent);
        questionAnswerDTO.setAnswerTime(Time.getCurrentDate());
        questionAnswerDTO.setLastUpdateTime(Time.getCurrentDate());
        questionAnswerDTO.setInvalidTime(Time.getPermanentDate());
        questionAnswerDTO.setQuestionStateEnum(QuestionStateEnum.已回答);

        //  1. 存储回答之后的问题-回答DTO
        questionBO.createQuestionAnswer(questionAnswerDTO);


        //  2.修改订单DTO
        OrderAskQuestionDTO orderAskQuestionDTO = orderInfoBO.getByQuestionAndAnswer(questionAnswerDTO, answererDTO);
        orderInfoBO.updateOrderSateAfterAnswerAsAnswer(orderAskQuestionDTO);

        //  3.给回答者转钱，因为是认证老师，直接赚钱
        //      3.1老师虚拟币扣税之后收益
        Double gainsVirtualCurrency = orderAskQuestionDTO.getAmount() * TEACHER_GET_MONEY_RATE;
        Double gainsCash = gainsVirtualCurrency / VIRTUAL_REAL_RATE;
        answererDTO.setCash(answererDTO.getCash() + gainsCash);
        //      3.2更新余额信息
        studentInfoBO.updateStudent(answererDTO);

        // 4.发消息给提问者
        //      4.1获取到提问者DTO
        StudentDTO askerDTO = studentInfoBO.getStudentDTO(orderAskQuestionDTO.getUserId());
        //      4.2发送消息
        messageBO.sendToAskerOfAnswer(askerDTO);


        //返回result
        jsonObject.put("result", "success");
        jsonObject.put("questionAnswerDTO", questionAnswerDTO);
//        jsonObject.put("orderAskQuestionDTO", orderAskQuestionDTO);
        jsonHandler.sendJSON(jsonObject, response);
    }

}
