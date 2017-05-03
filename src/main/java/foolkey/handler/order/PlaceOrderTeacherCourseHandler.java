package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.order_course.OrderBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.TeachMethodEnum;
import foolkey.pojo.root.vo.dto.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 向老师课程下订单的BO
 * 后台逻辑是：验证课程开课状态、验证老师与课程是否匹配、验证老师的账号状态
 * 生成订单，返回
 * 付款后，再生成相应的申请、消息，并发送给老师（这一步先不做了）
 * Created by geyao on 2017/4/30.
 */
@Service(value = "placeOrderTeacherCourseBO")
@Transactional
public class PlaceOrderTeacherCourseHandler extends AbstractBO{

    @Autowired
    private StudentInfoBO studentInfoBO;

    @Autowired
    private TeacherInfoBO teacherInfoBO;

    @Autowired
    private CourseTeacherBO courseBO;

    @Autowired
    private OrderBO orderBO;
    @Autowired
    private ApplicationInfoBO applicationInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        // 从明文中获取以下token，课程id，老师id
        String token = clearJSON.getString("token");
        String courseId = clearJSON.getString("courseId");
        String teacherId = clearJSON.getString("teacherId");

        // 获取学生DTO，课程DTO，老师DTO
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        CourseTeacherDTO courseDTO = courseBO.getCourseTeacherDTOById(courseId);
        TeacherDTO teacherDTO = teacherInfoBO.getTeacherDTO( Long.parseLong(teacherId) );


        //验证课程，老师
        boolean courseFlag = courseBO.checkCourse(courseDTO);
        boolean teacherFlag = courseDTO.getCreatorId().equals(teacherDTO.getId());
        //反馈一波原因
        if ( !courseFlag ){
            jsonObject.put("course", "false");
        }else {
            jsonObject.put("course", "true");
        }
        if ( !teacherFlag ){
            jsonObject.put("teacher", "false");
        }else {
            jsonObject.put("teacher", "true");
        }

        if (courseFlag && teacherFlag){ //验证通过，可以生成订单
            Double amount = clearJSON.getDouble("amount"); // 总价格
            Double number = clearJSON.getDouble("number"); // 购买节数，0.5，1h这样子
            Double cutOffPercent = clearJSON.getDouble("cutOffPercent"); // 折扣
            TeachMethodEnum teachMethod = TeachMethodEnum.valueOf(
                    clearJSON.getString("teachMethod")
            );// 授课方法
            CourseTypeEnum courseType = CourseTypeEnum.老师课程;
            // 生成订单，并存进数据库
            OrderBuyCourseDTO order = orderBO.createOrder(
                    amount, number, studentDTO, teacherDTO.getId()
                    , courseDTO.getId(), cutOffPercent, teachMethod, courseType
            );

            //先给客户端返回订单，再生成申请，以及给老师发送消息
            ApplicationTeacherCourseDTO application = applicationInfoBO
                    .createApplicationForTeacherCourse(
                            studentDTO.getId(),
                            order.getId(),
                            null,
                            teacherDTO.getId()
                    );

            applicationInfoBO.save(application);


            jsonObject.put("order", order);
            jsonObject.put("result", "success");
            jsonHandler.sendJSON(jsonObject, response);


        }else {
            jsonHandler.sendJSON(jsonObject, response);
        }

    }
}
