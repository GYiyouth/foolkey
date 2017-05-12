package foolkey.controller.order_buy_course;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.send_to_client.OrderBuyCourseWithStudentAsTeacherSTCDTO;
import foolkey.tool.StaticVariable;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by GR on 2017/5/12.
 */
@Controller
@RequestMapping(value = "/order/getAgreedOnClassOrderCourseStudentByTeacher")
public class GetAgreedOnClassOrderCourseStudentByTeacherController extends AbstractController{

    @Autowired
    private OrderInfoBO orderInfoBO;


    @RequestMapping
    public void execute(
            HttpServletRequest request,
//            @RequestParam("pageNo")Integer pageNo,
//            @RequestParam("courseId")Long courseId,
            HttpServletResponse response
    ){
        try{

            //获取-解析明文JSON数据
            String clearText = request.getParameter("clearText");
            JSONObject clearJSON = JSONObject.fromObject(clearText);

//            String token = clearJSON.getString("token");

            Integer pageNo = clearJSON.getInt("pageNo");
            Long courseId = clearJSON.getLong("courseId");

            //根据课程id，获取到已付款、未上课的申请
            List<OrderBuyCourseWithStudentAsTeacherSTCDTO> orderBuyCourseWithStudentAsTeacherSTCDTOS = orderInfoBO.getOrderBuyCourseWithStudentAsTeacher(courseId, CourseTypeEnum.老师课程,pageNo, StaticVariable.pageSize, OrderStateEnum.同意上课, OrderStateEnum.上课中);

            //封装、传送JSON
            jsonObject.put("result", "success");
            jsonObject.put("orderBuyCourseWithStudentAsTeacherSTCDTOS", orderBuyCourseWithStudentAsTeacherSTCDTOS);
            jsonHandler.sendJSON(jsonObject, response);


        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
