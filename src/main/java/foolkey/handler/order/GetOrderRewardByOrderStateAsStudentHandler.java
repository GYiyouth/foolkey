package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.OrderBuyCourseAsStudentSTCDTO;
import foolkey.pojo.send_to_client.OrderBuyRewardAsStudentSTCDTO;
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
 * Created by GR on 2017/5/14.
 */
//@Service
//@Transactional
public class GetOrderRewardByOrderStateAsStudentHandler extends AbstractBO {
//
//    @Autowired
//    private StudentInfoBO studentInfoBO;
//    @Autowired
//    private OrderInfoBO orderInfoBO;
//    @Autowired
//    private RewardBO rewardBO;
//    @Autowired
//    private TeacherInfoBO teacherInfoBO;
//
//    public void execute(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            JSONObject jsonObject
//    ) {
//        //获取-解析明文JSON数据
//        String clearText = request.getParameter("clearText");
//        JSONObject clearJSON = JSONObject.fromObject(clearText);
//
//        String token = clearJSON.getString("token");
//        Integer pageNo = clearJSON.getInt("pageNo");
//        String orderStateStr = clearJSON.getString("orderStateEnum");
//        OrderStateEnum orderStateEnum = OrderStateEnum.valueOf(orderStateStr);
//
//        /**
//         未付款, 已付款, 申请退款, 同意退款, 退款完成,
//         取消课程, 同意上课, 上课中, 结束上课,
//         上课完成
//         */
//        //根据token，获取当前学生的id
//        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
//        Long studentId = studentDTO.getId();
//
//        //最后返回的东西
//        List<OrderBuyRewardAsStudentSTCDTO> orderBuyRewardAsStudentSTCDTOS = new ArrayList<>();
//
//        try {
//            //********** 悬赏 *****************
//            //1. 获得该学生下面哪些悬赏订单处于规定状态
//            List<Long> courseIdS = orderInfoBO.getOrderBuyCourseDTOAsStudentByOrderStates(studentId, CourseTypeEnum.学生悬赏, pageNo, StaticVariable.pageSize, orderStateEnum);
//            //2. 上面每个悬赏，获取下面的老师-订单信息
//            for (Long rewardId : courseIdS) {
//                OrderBuyRewardAsStudentSTCDTO orderBuyRewardAsStudentSTCDTO = new OrderBuyRewardAsStudentSTCDTO();
//                //      2.1获取悬赏DTO
//                RewardDTO rewardDTO = rewardBO.getCourseStudentDTO(rewardId);
//                orderBuyRewardAsStudentSTCDTO.setRewardDTO(rewardDTO);
//                //      2.2获取订单DTO
//                OrderBuyCourseDTO orderBuyCourseDTO = orderInfoBO.getorder(rewardId);
//                orderBuyCourseAsStudentSTCDTO.setOrderBuyCourseDTO(orderBuyCourseDTO);
//                //      2.3获取老师DTO
//                TeacherAllInfoDTO teacherAllInfoDTO = teacherInfoBO.getTeacherAllInfoDTO(orderBuyCourseDTO.getTeacherId());
//                orderBuyCourseAsStudentSTCDTO.setTeacherAllInfoDTO(teacherAllInfoDTO);
//                orderBuyRewardAsStudentSTCDTOS.add(orderBuyCourseAsStudentSTCDTO);
//            }
//
//            //封装、传送JSON
//            jsonObject.put("result", "success");
//            jsonObject.put("orderBuyRewardAsStudentSTCDTOS", orderBuyRewardAsStudentSTCDTOS);
//            jsonHandler.sendJSON(jsonObject, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            jsonHandler.sendFailJSON(response);
//        }
//    }
}
