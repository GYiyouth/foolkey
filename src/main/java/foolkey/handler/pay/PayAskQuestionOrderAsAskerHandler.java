package foolkey.handler.pay;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.coupon.CouponInfoBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.pay_order.PayBO;
import foolkey.pojo.root.bo.question.QuestionBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.PayResultEnum;
import foolkey.pojo.root.vo.dto.CouponDTO;
import foolkey.pojo.root.vo.dto.OrderAskQuestionDTO;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 提问者为自己的提问付款
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
    @Autowired
    private CouponInfoBO couponInfoBO;



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
        //获取订单中使用的优惠券的DTO
        CouponDTO couponDTO = couponInfoBO.getCouponDTO(orderAskQuestionDTO.getCouponId());


        //付钱
        PayResultEnum payResult = payBO.payForQuestionAsAsker(studentDTO,orderAskQuestionDTO,couponDTO);

        if(payResult.equals(PayResultEnum.success)){
            //成功后修改订单状态
            orderInfoBO.updateOrderSateAfterPayAsAsker(orderAskQuestionDTO);
            //成功后删除使用过的券
            couponInfoBO.delete(couponDTO);

            //返回result
            jsonObject.put("result", "success");
            jsonObject.put("orderAskQuestionDTO", orderAskQuestionDTO);
            jsonHandler.sendJSON(jsonObject, response);
        }else if(payResult.equals(PayResultEnum.notEnoughBalance)){
            //余额不足
            jsonObject.put("result", "notEnoughBalance");
            jsonObject.put("orderAskQuestionDTO", orderAskQuestionDTO);
            jsonHandler.sendJSON(jsonObject, response);
        }else if(payResult.equals(PayResultEnum.notUseCoupon)){
            //券不可用
            jsonObject.put("result", "notUseCoupon");
            jsonObject.put("orderAskQuestionDTO", orderAskQuestionDTO);
            jsonHandler.sendJSON(jsonObject, response);
        }else{
            jsonHandler.sendFailJSON(response);
        }


    }
}
