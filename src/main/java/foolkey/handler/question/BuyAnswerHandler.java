package foolkey.handler.question;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.coupon.CouponInfoBO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.pay_order.PayBO;
import foolkey.pojo.root.bo.question.QuestionBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.PayResultEnum;
import foolkey.pojo.root.vo.dto.CouponDTO;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 去围观问题
 * <p>
 * 参数：
 * token：用户标识：String
 * questionId：问题id：Long
 * couponId：优惠券的id：Long
 * <p>
 * 返回：
 * questionAnswerDTO：问题DTO：QuestionAnswerDTO
 * <p>
 * Created by GR on 2017/5/21.
 */
@Service
public class BuyAnswerHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private QuestionBO questionBO;
    @Autowired
    private CouponInfoBO couponInfoBO;
    @Autowired
    private PayBO payBO;
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

        String token = clearJSON.getString("token");
        Long questionId = clearJSON.getLong("questionId");
        Long couponId = clearJSON.getLong("couponId");


        //购买者DTO(围观者)
        StudentDTO onlookerDTO = studentInfoBO.getStudentDTO(token);
        QuestionAnswerDTO questionAnswerDTO = questionBO.getQuestionAnswerDTOByQuestionAnswerId(questionId);
        CouponDTO couponDTO = couponInfoBO.getCouponDTO(couponId);

        //获取回答者、提问者DTO
        StudentDTO answererDTO = studentInfoBO.getStudentDTO(questionAnswerDTO.getAnswerId());
        StudentDTO askerDTO = studentInfoBO.getStudentDTO(questionAnswerDTO.getAskerId());

        //完成围观付钱订单
        PayResultEnum payResultEnum = payBO.payForAnswerAsOnlooker(onlookerDTO, askerDTO, answererDTO, couponDTO);
        jsonObject.put("result", payResultEnum.toString());

        if (payResultEnum.equals(PayResultEnum.success)) {
            //付款成功，围观订单要添加到数据库
            orderInfoBO.createOrderBuyAnswerDTO(onlookerDTO, couponDTO, questionAnswerDTO);
            //修改课程的围观人数+1
            questionAnswerDTO.setOnlookerNumber(questionAnswerDTO.getOnlookerNumber()+1);
            questionBO.updateQuestionAnswerDTO(questionAnswerDTO);

            jsonObject.put("questionAnswerDTO", questionAnswerDTO);
            jsonHandler.sendJSON(jsonObject, response);
        } else {
            //把答案抹掉
            String answerContent = questionAnswerDTO.getAnswerContent();
            questionAnswerDTO.setAnswerTime(null);
            jsonObject.put("questionAnswerDTO", questionAnswerDTO);
            jsonHandler.sendJSON(jsonObject, response);
            questionAnswerDTO.setAnswerContent(answerContent);
        }
        //发消息给提问者有人围观了
        messageBO.sendToAskerOfOnlook(askerDTO);
        //发消息给回答者有人围观了
        messageBO.sendToAnswererOfOnlook(answererDTO);

    }
}
