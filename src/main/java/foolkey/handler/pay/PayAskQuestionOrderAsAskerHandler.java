package foolkey.handler.pay;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.pay_order.PayBO;
import foolkey.pojo.root.bo.question.QuestionBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.OrderAskQuestionDTO;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GR on 2017/5/21.
 */
public class PayAskQuestionOrderAsAskerHandler extends AbstractBO {


    @Autowired
    private PayBO payBO;
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private QuestionBO questionBO;
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
        //提问者id
        Long askerId = studentDTO.getId();

        //提问DTO的id
        Long questionAnswerId = clearJSON.getLong("questionAnswerId");
        //提问订单的id
        Long orderAskQuestionId = clearJSON.getLong("orderAskQuestionId");

        //根据questionAnswerId获取提问的DTO
        QuestionAnswerDTO questionAnswerDTO = questionBO.getQuestionAnswerDTOByQuestionAnswerId(questionAnswerId);
        //获取提问订单DTO
        OrderAskQuestionDTO orderAskQuestionDTO = orderInfoBO.getOrderAskQuestionDTOByOrderAskQuestionId(orderAskQuestionId);

        //付钱
        boolean flag = payBO.payForQuestionAsAsker(studentDTO,questionAnswerDTO);

        //付钱成功后修改订单状态
        if(flag){
            orderInfoBO.updateOrderSateAfterPayAsAsker(orderAskQuestionDTO);
            //返回result
            jsonObject.put("result", "success");
            jsonObject.put("orderAskQuestionDTO", orderAskQuestionDTO);
            jsonHandler.sendJSON(jsonObject, response);
        }else{
            jsonHandler.sendFailJSON(response);
        }


    }
}
