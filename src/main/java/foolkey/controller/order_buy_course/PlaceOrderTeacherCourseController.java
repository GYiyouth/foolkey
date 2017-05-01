package foolkey.controller.order_buy_course;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.palce_order.PlaceOrderTeacherCourseBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 学生对老师的课程下订单，需要AES加密传输，遵守AES的规则
     * clearText:JSON格式，明文，必须包含token，
     * validation:由clearText 对称加密得来
     * cipherText:一些保密数据，密文

 * 这里需要提交的数据有 课程编号、老师编号、购买时长
 * 后台逻辑是：验证课程开课状态、验证老师与课程是否匹配、验证老师的账号状态（AOP)
 * 生成订单，生成相应的申请、消息，并发送给老师
 * Created by geyao on 2017/4/30.
 */
@Controller
@RequestMapping("/aes/placeOrderTeacherCourse")
public class PlaceOrderTeacherCourseController extends AbstractController{

    @Resource(name = "placeOrderTeacherCourseBO")
    private PlaceOrderTeacherCourseBO placeOrderTeacherCourseBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        placeOrderTeacherCourseBO.execute(request, response);
    }
}
