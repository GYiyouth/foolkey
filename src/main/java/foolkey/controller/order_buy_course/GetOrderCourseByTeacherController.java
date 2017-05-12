package foolkey.controller.order_buy_course;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.OrderBuyCourseAsTeacherSTCDTO;
import foolkey.pojo.send_to_client.OrderBuyRewardAsTeacherSTCDTO;
import foolkey.tool.StaticVariable;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * 老师获取自己有关的订单
 * 目前只有课程、悬赏
 * Created by ustcg on 2017/5/9.
 */
@Controller
@RequestMapping(value = "/order")
public class GetOrderCourseByTeacherController extends AbstractController {

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;

    @RequestMapping(value = "/getOrderCourseByTeacher")
    public void execute(
            HttpServletRequest request,
//            @RequestParam("token") String token,
            HttpServletResponse response
    ) {
        //获取-解析明文JSON数据
        String clearText = request.getParameter("clearText");
        JSONObject clearJSON = JSONObject.fromObject(clearText);


        String orderStateStr = clearJSON.getString("orderStateEnum");
        OrderStateEnum orderStateEnum = OrderStateEnum.valueOf(orderStateStr);
        String token = clearJSON.getString("token");
        Integer pageNo = clearJSON.getInt("pageNo");
//        Integer pageSize = clearJSON.getInt("pageSize");

        /**
         未付款, 已付款, 申请退款, 同意退款, 退款完成,
         取消课程, 同意上课, 上课中, 结束上课,
         上课完成
         */
        //根据token，获取当前用户的id(因为后面用到的信息在“学生”部分就可以获得，因此暂时使用学生DTO)
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);

//        // 按照订单状态分类的订单
//        List<OrderBuyCourseDTO> orderBuyCourseDTOS = new ArrayList<>();
        Long teacherId = studentDTO.getId();
        try {
            //********** 悬赏 *****************
            //按照订单状态，该老师下面的悬赏订单
            ArrayList<OrderBuyCourseDTO> orderBuyCourseDTOS_Reward = orderInfoBO.getOrderBuyCourseDTOAsTeacher(teacherId, CourseTypeEnum.学生悬赏,orderStateEnum,pageNo, StaticVariable.pageSize);
            //封装悬赏订单
            ArrayList<OrderBuyRewardAsTeacherSTCDTO> orderBuyRewardAsTeacherSTCDTOS = orderInfoBO.convertOrderBuyCourseDTOIntoOrderBuyRewardSTCDTO(orderBuyCourseDTOS_Reward);

            //******** 课程 *****************
            //按照订单状态，该老师下面的课程订单
            ArrayList<OrderBuyCourseDTO> orderBuyCourseDTOS_Course = orderInfoBO.getOrderBuyCourseDTOAsTeacher(teacherId, CourseTypeEnum.老师课程,orderStateEnum,pageNo,StaticVariable.pageSize);
            //封装课程订单
            ArrayList<OrderBuyCourseAsTeacherSTCDTO> orderBuyCourseAsTeacherSTCDTOS = orderInfoBO.convertOrderBuyCourseDTOIntoOrderBuyCourseSTCDTO(orderBuyCourseDTOS_Course);


            //封装、传送JSON
            jsonObject.put("result", "success");
            jsonObject.put("orderBuyRewardAsTeacherSTCDTOS", orderBuyRewardAsTeacherSTCDTOS);
            jsonObject.put("orderBuyCourseAsTeacherSTCDTOS", orderBuyCourseAsTeacherSTCDTOS);
            jsonHandler.sendJSON(jsonObject, response);
        } catch (Exception e) {
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
