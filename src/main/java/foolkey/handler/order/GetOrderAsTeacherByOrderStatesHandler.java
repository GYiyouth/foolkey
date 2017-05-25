package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.course.CourseBO;
import foolkey.pojo.root.bo.order.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.OrderBuyCourseAsStudentDTO;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 老师
 * 获取（课程？悬赏）订单，通过课程状态（一个或多个）
 * Created by GR on 2017/5/23.
 */
@Service
public class GetOrderAsTeacherByOrderStatesHandler extends AbstractBO {

    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;
    @Autowired
    private CourseBO courseBO;
    @Autowired
    private RewardBO rewardBO;


    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) throws Exception {
        //获取json并解析
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        //获取老师（当前用户）的信息
        String token = clearJSON.getString("token");
        StudentDTO teacherDTO = studentInfoBO.getStudentDTO(token);

        Integer pageNo = clearJSON.getInt("pageNo");

//        //获取到请求的类别
//        String courseTypeEnumStr = clearJSON.getString("courseTypeEnum");
//        CourseTypeEnum courseTypeEnum = CourseTypeEnum.valueOf(courseTypeEnumStr);

        //获取到请求订单的状态
        String orderStateEnumStr = clearJSON.getString("orderStateEnum");
        OrderStateEnum orderStateEnum = OrderStateEnum.valueOf(orderStateEnumStr);

        //返回给前端内容
        List<OrderBuyCourseAsStudentDTO> orderList = new ArrayList<>();

        //所有的订单信息
        List<OrderBuyCourseDTO> orderBuyCourseDTOS = orderInfoBO.getOrderBuyCourseAsTeacherByOrderStateAndCourseType(teacherDTO.getId(), pageNo, orderStateEnum);

        for (OrderBuyCourseDTO orderBuyCourseDTO : orderBuyCourseDTOS) {
            //结束上课的时候，因为老师只能对学生评论，因此需要去查看是否已经对学生评价了
//            if(orderStateEnum.equals(OrderStateEnum.结束上课))


//            StringEscapeUtils.escapeHtml()

            OrderBuyCourseAsStudentDTO orderBuyCourseAsStudentDTO = new OrderBuyCourseAsStudentDTO();

            StudentDTO studentDTO = studentInfoBO.getStudentDTO(orderBuyCourseDTO.getUserId());
            orderBuyCourseAsStudentDTO.setStudentDTO(studentDTO);
            orderBuyCourseAsStudentDTO.setOrderDTO( orderBuyCourseDTO );

            orderBuyCourseAsStudentDTO.setTeacherDTO(teacherInfoBO.getTeacherDTO(teacherDTO.getId()));

            //如果请求的是老师课程
            if (orderBuyCourseDTO.getCourseTypeEnum().equals(CourseTypeEnum.老师课程)) {
                CourseDTO courseDTO = courseBO.getCourseTeacherDTOById(orderBuyCourseDTO.getCourseId());
                orderBuyCourseAsStudentDTO.setCourse(courseDTO);
            } else if (orderBuyCourseDTO.getCourseTypeEnum().equals(CourseTypeEnum.学生悬赏)) {
                RewardDTO rewardDTO = rewardBO.getCourseStudentDTOById(orderBuyCourseDTO.getCourseId());
                orderBuyCourseAsStudentDTO.setCourse(rewardDTO);
            }
            orderList.add(orderBuyCourseAsStudentDTO);
        }


        jsonObject.put("result", "success");
        jsonObject.put("orderList", orderList);
        jsonHandler.sendJSON(jsonObject,response);

    }
}
