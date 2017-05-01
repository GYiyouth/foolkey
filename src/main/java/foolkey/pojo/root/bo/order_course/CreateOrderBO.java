package foolkey.pojo.root.bo.order_course;

import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.assistObject.TeachMethodEnum;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class CreateOrderBO {

    /**
     * 根据信息生成订单
     * 不会修改课程的状态
     * @return
     */
    public OrderBuyCourseDTO createOrder(
            Double amount, // 总价格
            Double number, // 购买节数，0.5，1h这样子
            StudentDTO studentDTO, // 购买学生
            Long teacherId, // 相关老师
            Long courseId, // 课程id
            Double cutOffPercent, // 折扣
            TeachMethodEnum teachMethod, // 授课方法
            CourseTypeEnum courseType // 课程种类
    ){
        OrderBuyCourseDTO order = new OrderBuyCourseDTO();
        //从app端获取的消息
        order.setUserId(studentDTO.getId());
        order.setNumber(number);
        order.setAmount(amount);
        order.setTeacherId(teacherId);
        order.setCourseId(courseId);
        order.setCutOffPercent(cutOffPercent);
        order.setTeachMethodEnum(teachMethod);
        order.setCourseTypeEnum(courseType);

        order.setCreatedTime(new Date());
        order.setOrderStateEnum(OrderStateEnum.unpay);

        return order;
    }
}
