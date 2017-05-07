package foolkey.handler.course.judge;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.bo.evaluation.EvaluationInfoBO;
import foolkey.pojo.root.bo.give_money_to_user.GiveMoneyToTeacherBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.EvaluationStateEnum;
import foolkey.pojo.root.vo.dto.EvaluationTeacherDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
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
    private CourseTeacherBO courseTeacherBO;
    @Autowired
    private EvaluationInfoBO evaluationInfoBO;
    @Autowired
    private GiveMoneyToTeacherBO giveMoneyToTeacherBO;

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
        Long teacherId = clearJSON.getLong( "teacherId" );

        //获取各种DTO
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        TeacherDTO teacherDTO = teacherInfoBO.getTeacherDTO(teacherId);
        StudentDTO teacher = studentInfoBO.getStudentDTO(teacherId);
        OrderBuyCourseDTO orderDTO = orderInfoBO.getCourseOrder( orderId + "" );

        //teacherDTO修改
        Float totalScore = teacherDTO.getTeacherAverageScore() * teacherDTO.getFollowerNumber();
        totalScore += score;
        teacherDTO.setTeachingNumber( teacherDTO.getTeachingNumber() + 1 );
        teacherDTO.setTeacherAverageScore( totalScore / teacherDTO.getTeachingNumber() );

        //给老师发钱，注意一定要先发钱再保存
        giveMoneyToTeacherBO.giveMoneyToTeacher(teacher, orderDTO);

        //保存
        teacherInfoBO.updateTeacherDTO(teacherDTO);
        studentInfoBO.updateStudent(teacher);


        //返回
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);

        //生成评价
        EvaluationTeacherDTO evaluationTeacherDTO = new EvaluationTeacherDTO();
        evaluationTeacherDTO.setCreatorId( studentDTO.getId() );
        evaluationTeacherDTO.setAcceptor_id( teacherId );
        evaluationTeacherDTO.setEvaluationStateEnum( EvaluationStateEnum.done );
        evaluationTeacherDTO.setOrderId( orderId );
        evaluationTeacherDTO.setScore( Double.parseDouble(score + "") );
        evaluationInfoBO.save(evaluationTeacherDTO);
    }
}
