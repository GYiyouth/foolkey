package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.OrderBuyCourseAsTeacherSTCDTO;
import foolkey.pojo.send_to_client.OrderBuyCourseWithStudentAsTeacherSTCDTO;
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
 * Created by GR on 2017/5/12.
 */
@Service
@Transactional
public class GetOrderCourseByOrderStateAsTeacherHandler extends AbstractBO{

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private CourseBO courseBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ){
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
        //根据token，获取当前老师的id(因为后面用到的信息在“学生”部分就可以获得，因此暂时使用学生DTO)
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        Long teacherId = studentDTO.getId();

        //最后返回的东西
        List<OrderBuyCourseAsTeacherSTCDTO> orderBuyCourseAsTeacherSTCDTOS = new ArrayList<>();

        try {
            //********** 课程 *****************
            //1. 获得该老师下面哪些课程订单处于规定状态
            List<Long> courseIdS = orderInfoBO.getOrderBuyCourseDTOAsTeacherByOrderStates(teacherId, CourseTypeEnum.老师课程,pageNo, StaticVariable.pageSize, OrderStateEnum.同意上课, orderStateEnum);
            //2. 上面每个课程，获取下面的学生-订单信息
            for(Long courseId:courseIdS){
                OrderBuyCourseAsTeacherSTCDTO orderBuyCourseAsTeacherSTCDTO = new OrderBuyCourseAsTeacherSTCDTO();
                //      2.1获取课程DTO
                CourseDTO courseDTO = courseBO.getCourseTeacherDTOById(courseId);
                orderBuyCourseAsTeacherSTCDTO.setCourseDTO(courseDTO);
                //      2.2由课程id获取申请-学生DTOS
                List<OrderBuyCourseWithStudentAsTeacherSTCDTO> orderBuyCourseWithStudentAsTeacherSTCDTOS = orderInfoBO.getOrderBuyCourseWithStudentAsTeacher(courseId, CourseTypeEnum.老师课程,1,4, orderStateEnum);
                orderBuyCourseAsTeacherSTCDTO.setOrderBuyCourseWithStudentAsTeacherSTCDTOS(orderBuyCourseWithStudentAsTeacherSTCDTOS);
                orderBuyCourseAsTeacherSTCDTOS.add(orderBuyCourseAsTeacherSTCDTO);
            }

            //封装、传送JSON
            jsonObject.put("result", "success");
            jsonObject.put("orderBuyCourseAsTeacherSTCDTOS", orderBuyCourseAsTeacherSTCDTOS);
            jsonHandler.sendJSON(jsonObject, response);
        } catch (Exception e) {
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }

    }


}
