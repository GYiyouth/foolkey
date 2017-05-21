package foolkey.handler.question;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.OrderAskQuestionDTO;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取我回答的问题（已认证老师才可以）
 * 参数：
 * 返回：
 * Created by GR on 2017/5/21.
 */
public class GetMyAnswerQuestionHandler extends AbstractBO {

    public void execute(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObject) throws Exception {

        //读取客户端传入参数
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        Integer pageNo = clearJSON.getInt("pageNo");
        String orderStateEnumStr = clearJSON.getString("orderStateEnum");
        OrderStateEnum orderStateEnum = OrderStateEnum.valueOf(orderStateEnumStr);


        QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO();
        OrderAskQuestionDTO orderAskQuestionDTO = new OrderAskQuestionDTO();
        orderAskQuestionDTO.getOrderStateEnum();
        //获取个人信息，进而判断是否可围观




    }
}
