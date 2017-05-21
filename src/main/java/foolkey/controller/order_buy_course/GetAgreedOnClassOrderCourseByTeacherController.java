package foolkey.controller.order_buy_course;

import foolkey.controller.AbstractController;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 老师获取已经付款且未评价的课程订单(同意上课, 上课中)
 * Created by GR on 2017/5/12.
 */
@Controller
@RequestMapping(value = "/order/getAgreedOnClassOrderCourseByTeacher")
public class GetAgreedOnClassOrderCourseByTeacherController extends AbstractController{


    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private CourseBO courseBO;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
//            @RequestParam("pageNo") Integer pageNo,
            HttpServletResponse response
    ) {
        //获取-解析明文JSON数据
        String clearText = request.getParameter("clearText");
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        Integer pageNo = clearJSON.getInt("pageNo");

        /**
         未付款, 已付款, 申请退款, 同意退款, 退款完成,
         取消课程, 同意上课, 上课中, 结束上课,
         已评价
         */
        //根据token，获取当前用户的id(因为后面用到的信息在“学生”部分就可以获得，因此暂时使用学生DTO)
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);

//        // 按照订单状态分类的订单
        Long teacherId = studentDTO.getId();
//        Long teacherId = 20001L;

        //最后返回的东西
        List<OrderBuyCourseAsTeacherSTCDTO> orderBuyCourseAsTeacherSTCDTOS = new ArrayList<>();

        try {
            //********** 课程 *****************
            //1. 获得该老师下面哪些课程订单处于规定状态
            List<Long> courseIdS = orderInfoBO.getOrderBuyCourseDTOAsTeacherByOrderStates(teacherId, CourseTypeEnum.老师课程,pageNo,StaticVariable.PAGE_SIZE, OrderStateEnum.同意上课, OrderStateEnum.上课中);
            //2. 上面每个课程，获取下面的学生-订单信息
            for(Long courseId:courseIdS){
                OrderBuyCourseAsTeacherSTCDTO orderBuyCourseAsTeacherSTCDTO = new OrderBuyCourseAsTeacherSTCDTO();
                //      2.1获取课程DTO
                CourseDTO courseDTO = courseBO.getCourseTeacherDTOById(courseId);
                orderBuyCourseAsTeacherSTCDTO.setCourseDTO(courseDTO);
                //      2.2由课程id获取申请-学生DTOS
                List<OrderBuyCourseWithStudentAsTeacherSTCDTO> orderBuyCourseWithStudentAsTeacherSTCDTOS = orderInfoBO.getOrderBuyCourseWithStudentAsTeacher(courseId, CourseTypeEnum.老师课程,1,4, OrderStateEnum.同意上课, OrderStateEnum.上课中);
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
