package foolkey.handler.course.judge;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.bo.coupon.CouponInfoBO;
import foolkey.pojo.root.bo.evaluation.EvaluationInfoBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.EvaluationStateEnum;
import foolkey.pojo.root.vo.dto.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * aes加密
 * 只能对老师的课程发起评价
 * 需要token，orderId，score,content, pic1Path ~ pic4Path
 * Created by geyao on 2017/5/6.
 */
@Service
@Transactional
public class EvaluateCourseHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private CourseBO courseTeacherBO;
    @Autowired
    private EvaluationInfoBO evaluationInfoBO;
    @Autowired
    private CouponInfoBO couponInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getParameter("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        //获取原始数据
        String token = clearJSON.getString("token");
        Long orderId = clearJSON.getLong("orderId");
        Double score = Double.parseDouble( clearJSON.getString("score") );
        String content = clearJSON.getString("content");
        String pic1Path = clearJSON.getString("pic1Path");
        String pic2Path = clearJSON.getString("pic2Path");
        String pic3Path = clearJSON.getString("pic3Path");
        String pic4Path = clearJSON.getString("pic4Path");

        //获取DTO
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        OrderBuyCourseDTO orderDTO = orderInfoBO.getCourseOrder(orderId + "");
        CourseDTO courseDTO = courseTeacherBO.getCourseTeacherDTOById(orderDTO.getCourseId());

        //新建一个评价
        EvaluationCourseDTO evaluationDTO = new EvaluationCourseDTO();
        evaluationDTO.setCreatorId( studentDTO.getId() );
        evaluationDTO.setAcceptor_id( courseDTO.getCreatorId() );
        evaluationDTO.setEvaluationStateEnum(EvaluationStateEnum.done);
        evaluationDTO.setOrderId( orderId );
        evaluationDTO.setScore( score );
        evaluationDTO.setContent( content );
        evaluationDTO.setCourseId( courseDTO.getId() );
        evaluationDTO.setPic1Path( pic1Path );
        evaluationDTO.setPic2Path( pic2Path );
        evaluationDTO.setPic3Path( pic3Path );
        evaluationDTO.setPic4Path( pic4Path );
        //保存
        evaluationInfoBO.save(evaluationDTO);


        //返回
        jsonObject.put("result", "success");
        jsonObject.put("evaluation", evaluationDTO);
        jsonHandler.sendJSON(jsonObject, response);

        //修改课程的得分
        Double totalScore = 0.0 + courseDTO.getSales() * courseDTO.getAverageScore();
        totalScore += score;
        courseDTO.setSales( courseDTO.getSales() + 1 );
        Double average = totalScore / courseDTO.getSales();
        courseDTO.setAverageScore( Float.parseFloat( average.toString() ) );
        //保存
        courseTeacherBO.updateCourseTeacherDTO(courseDTO);

        //删除优惠券
        Long couponId = orderDTO.getCouponId();
        if ( couponId != null & !couponId.equals(0L)) {
            CouponDTO couponDTO = couponInfoBO.getCouponDTO( couponId );
            couponInfoBO.delete(couponDTO);
        }


    }
}
