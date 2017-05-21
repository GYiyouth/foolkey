package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.OrderBuyCourseAsStudentSTCDTO;
import foolkey.pojo.send_to_client.TeacherAllInfoDTO;
import foolkey.tool.StaticVariable;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生，根据输入的订单状态，获取课程订单
 * Created by GR on 2017/5/12.
 */
@Service
@Transactional
public class GetOrderCourseByOrderStateAsStudentHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private CourseBO courseBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) {
        //获取-解析明文JSON数据
        String clearText = request.getParameter("clearText");
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        Integer pageNo = clearJSON.getInt("pageNo");
        String orderStateStr = clearJSON.getString("orderStateEnum");
        OrderStateEnum orderStateEnum = OrderStateEnum.valueOf(orderStateStr);

        /**
         未付款, 已付款, 申请退款, 同意退款, 退款完成,
         取消课程, 同意上课, 上课中, 结束上课,
         上课完成
         */
        //根据token，获取当前学生的id
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        Long studentId = studentDTO.getId();

        //最后返回的东西
        List<OrderBuyCourseAsStudentSTCDTO> orderBuyCourseAsStudentSTCDTOS = new ArrayList<>();

        try {
            //********** 课程 *****************
            //1. 获得该学生下面哪些课程订单处于规定状态
            List<Long> courseIdS = orderInfoBO.getOrderBuyCourseDTOAsStudentByOrderStates(studentId, CourseTypeEnum.老师课程, pageNo, StaticVariable.PAGE_SIZE, orderStateEnum);
            //2. 上面每个课程，获取下面的老师-订单信息
            for (Long courseId : courseIdS) {
                OrderBuyCourseAsStudentSTCDTO orderBuyCourseAsStudentSTCDTO = new OrderBuyCourseAsStudentSTCDTO();
                //      2.1获取课程DTO
                CourseDTO courseDTO = courseBO.getCourseTeacherDTOById(courseId);
                orderBuyCourseAsStudentSTCDTO.setCourseDTO(courseDTO);
                //      2.2获取订单DTO
                OrderBuyCourseDTO orderBuyCourseDTO = orderInfoBO.getOrderBuyCourseDTOByOrderId(courseId);
                orderBuyCourseAsStudentSTCDTO.setOrderBuyCourseDTO(orderBuyCourseDTO);
                //      2.3获取老师DTO
                TeacherAllInfoDTO teacherAllInfoDTO = teacherInfoBO.getTeacherAllInfoDTO(orderBuyCourseDTO.getTeacherId());
                orderBuyCourseAsStudentSTCDTO.setTeacherAllInfoDTO(teacherAllInfoDTO);
                orderBuyCourseAsStudentSTCDTOS.add(orderBuyCourseAsStudentSTCDTO);
            }

            //封装、传送JSON
            jsonObject.put("result", "success");
            jsonObject.put("orderBuyCourseAsStudentSTCDTOS", orderBuyCourseAsStudentSTCDTOS);
            jsonHandler.sendJSON(jsonObject, response);
        } catch (Exception e) {
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
