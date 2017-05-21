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
 * 提问的handler
 * 1. 存储问题DTO
 * 2. 存储订单DTO
 * Created by GR on 2017/5/20.
 */
@Service
public class CreateQuestionHandler extends AbstractBO {

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

        Long answerId = clearJSON.getLong("answerId");
        Double price = clearJSON.getDouble("price");
        String title = clearJSON.getString("title");
        String questionContent = clearJSON.getString("questionContent");
        Long couponId = clearJSON.getLong("couponId");

        //创建一个问题DTO
        QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO();
        questionAnswerDTO.setAskerId(askerId);
        questionAnswerDTO.setAnswerId(answerId);
        questionAnswerDTO.setCreatedTime(Time.getCurrentDate());
        questionAnswerDTO.setInvalidTime(Time.getAskQuestioninValidDate());
        questionAnswerDTO.setLastUpdateTime(Time.getCurrentDate());
        questionAnswerDTO.setOnlookerNumber(0);
        questionAnswerDTO.setPrice(price);
        questionAnswerDTO.setQuestionContent(questionContent);
        questionAnswerDTO.setTitle(title);
        questionAnswerDTO.setQuestionStateEnum(QuestionStateEnum.待付款);
        //  1. 存储问题DTO
        questionBO.createQuestionAnswer(questionAnswerDTO);
        //  2.存储订单DTO
        OrderAskQuestionDTO orderAskQuestionDTO = orderInfoBO.createOrderAsk(studentDTO, questionAnswerDTO, couponId);
        //返回result
        jsonObject.put("result", "success");
        jsonObject.put("questionAnswerDTO", questionAnswerDTO);
        jsonObject.put("orderAskQuestionDTO", orderAskQuestionDTO);
        jsonHandler.sendJSON(jsonObject, response);
    }

}
