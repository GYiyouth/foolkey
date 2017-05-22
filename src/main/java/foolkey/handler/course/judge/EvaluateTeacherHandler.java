package foolkey.handler.course.judge;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.evaluation.EvaluationInfoBO;
import foolkey.pojo.root.bo.give_money_to_user.GiveMoneyToTeacherBO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.CheckEvaluationForVerifyBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.*;
import foolkey.pojo.root.vo.dto.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对老师进行评价，意味着一个订单的完结，要修改订单状态，老师的教学次数与分数，给老师发钱
 * 通过aop来进行非认证老师的认证处理
 * Created by geyao on 2017/5/6.
 */
@Service
@Transactional
public class EvaluateTeacherHandler extends AbstractBO{



    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private CourseBO courseTeacherBO;
    @Autowired
    private RewardBO rewardBO;
    @Autowired
    private EvaluationInfoBO evaluationInfoBO;
    @Autowired
    private GiveMoneyToTeacherBO giveMoneyToTeacherBO;
    @Autowired
    private CheckEvaluationForVerifyBO checkEvaluationForVerifyBO;
    @Autowired
    private MessageBO messageBO;
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        //获取原始数据
        String token = clearJSON.getString("token");
        Long orderId = clearJSON.getLong("orderId");
        Float score = Float.parseFloat( clearJSON.get("score").toString() );


        //获取各种DTO
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        OrderBuyCourseDTO orderDTO = orderInfoBO.getCourseOrder( orderId + "" );

        //订单状态不对
        if(orderDTO.getOrderStateEnum().compareTo( OrderStateEnum.结束上课) != 0){
            jsonHandler.sendFailJSON(response);
            return;
        }

        TeacherDTO teacherDTO = teacherInfoBO.getTeacherDTO(orderDTO.getTeacherId());
        StudentDTO teacher = studentInfoBO.getStudentDTO(orderDTO.getTeacherId());


        //teacherDTO修改
        Float totalScore = teacherDTO.getTeacherAverageScore() * teacherDTO.getTeachingNumber();
        //注意，目前的teachingNumber和授课时间有关系
        Integer teachHour = (int) Math.ceil( orderDTO.getNumber() );
        totalScore += score * teachHour;
        teacherDTO.setTeachingNumber( teacherDTO.getTeachingNumber() + teachHour );
        teacherDTO.setTeacherAverageScore( totalScore / teacherDTO.getTeachingNumber() );

        //给老师发钱，注意一定要先发钱再保存
        giveMoneyToTeacherBO.giveMoneyToTeacher(teacher, orderDTO);

        //保存
        teacherInfoBO.updateTeacherDTO(teacherDTO);
        studentInfoBO.updateStudent(teacher);

        //学生课程的话，要同时修改课程状态
        System.out.println(orderDTO);
        if (orderDTO.getCourseTypeEnum().compareTo( CourseTypeEnum.学生悬赏) == 0 ){
            RewardDTO rewardDTO = rewardBO.getCourseStudentDTOById(orderDTO.getCourseId());
            rewardDTO.setRewardStateEnum( RewardStateEnum.已解决 );
            rewardBO.update( rewardDTO );
        }

        orderDTO.setOrderStateEnum( OrderStateEnum.已评价 );
        orderInfoBO.update( orderDTO );


        //返回
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);

        //生成评价
        EvaluationTeacherDTO evaluationTeacherDTO = new EvaluationTeacherDTO();
        evaluationTeacherDTO.setCreatorId( studentDTO.getId() );
        evaluationTeacherDTO.setAcceptor_id( orderDTO.getTeacherId() );
        evaluationTeacherDTO.setEvaluationStateEnum( EvaluationStateEnum.done );
        evaluationTeacherDTO.setOrderId( orderId );
        evaluationTeacherDTO.setScore( Double.parseDouble(score + "") );
        evaluationInfoBO.save(evaluationTeacherDTO);

        //检查老师的认证状态
        if (teacher.getRoleEnum().compareTo( RoleEnum.alreadyApplied ) == 0){
            //非认证老师，需要查看过往评价，有没有5次连续好评
            checkEvaluationForVerifyBO.execute(teacher, teacherDTO);
        }

        //发送消息
        messageBO.sendForEvaluation(teacher);
    }
}
