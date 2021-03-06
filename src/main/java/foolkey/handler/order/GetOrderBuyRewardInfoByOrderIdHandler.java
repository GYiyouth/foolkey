package foolkey.handler.order;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.order.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.TeacherAllInfoDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GR on 2017/5/12.
 */
@Service
@Transactional
public class GetOrderBuyRewardInfoByOrderIdHandler extends AbstractBO {

    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private RewardBO rewardBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ){
        try{
            //获取json并解析
            String clearText = request.getAttribute("clearText").toString();
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            String token = clearJSON.getString("token");
            Long orderId = clearJSON.getLong("orderId");

            //获取课程订单DTO
            OrderBuyCourseDTO orderBuyCourseDTO = orderInfoBO.getOrderBuyCourseDTOByOrderId(orderId);

            //获取老师信息DTO
            TeacherAllInfoDTO teacherAllInfoDTO = teacherInfoBO.getTeacherAllInfoDTO(orderBuyCourseDTO.getTeacherId());

            //获取学生信息
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(orderBuyCourseDTO.getUserId());

            //课程信息
            RewardDTO rewardDTO = rewardBO.getCourseStudentDTO(orderBuyCourseDTO.getCourseId());

            jsonObject.put("result","success");
            jsonObject.put("orderBuyCourseDTO",orderBuyCourseDTO);
            jsonObject.put("teacherAllInfoDTO",teacherAllInfoDTO);
            jsonObject.put("studentDTO",studentDTO);
            jsonObject.put("rewardDTO",rewardDTO);
            jsonHandler.sendJSON(jsonObject,response);

        }catch(Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
