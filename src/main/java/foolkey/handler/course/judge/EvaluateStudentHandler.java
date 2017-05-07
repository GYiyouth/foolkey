package foolkey.handler.course.judge;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.bo.evaluation.EvaluationInfoBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.EvaluationStateEnum;
import foolkey.pojo.root.vo.dto.EvaluationCourseDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对学生进行评价
 * Created by geyao on 2017/5/6.
 */
@Service
@Transactional
public class EvaluateStudentHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private CourseTeacherBO courseTeacherBO;
    @Autowired
    private EvaluationInfoBO evaluationInfoBO;

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

        //获取DTO
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        OrderBuyCourseDTO orderDTO = orderInfoBO.getCourseOrder(orderId + "");
//        CourseTeacherDTO courseDTO = courseTeacherBO.getCourseTeacherDTOById(orderDTO.getCourseId());
        StudentDTO student = studentInfoBO.getStudentDTO( orderDTO.getUserId() );



        //修改学生评价
        Float totalScore = student.getStudentAverageScore() * student.getLearningTime();
        totalScore += score;
        student.setLearningNumber( student.getLearningNumber() + 1 );
        Float average = totalScore / student.getLearningNumber();
        student.setStudentAverageScore(average);
        //保存
        studentInfoBO.updateStudent(studentDTO);

        //返回消息
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);


        //新建一个评价
        EvaluationCourseDTO evaluationDTO = new EvaluationCourseDTO();
        evaluationDTO.setCreatorId( studentDTO.getId() );
        evaluationDTO.setAcceptor_id( orderDTO.getUserId() );
        evaluationDTO.setEvaluationStateEnum(EvaluationStateEnum.done);
        evaluationDTO.setOrderId( orderId );
        evaluationDTO.setScore( Double.parseDouble( score + "" ) );

        evaluationInfoBO.save(evaluationDTO);
    }
}

