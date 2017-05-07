package foolkey.pojo.root.bo.order_course;

import foolkey.pojo.root.DAO.order_course.GetOrderCourseDAO;
import foolkey.pojo.root.DAO.order_course.SaveOrderCourseDAO;
import foolkey.pojo.root.DAO.order_course.UpdateOrderCourseDAO;
import foolkey.pojo.root.bo.coupon.CouponInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.assistObject.TeachMethodEnum;
import foolkey.pojo.root.vo.dto.CouponDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class OrderInfoBO {

    @Autowired
    private GetOrderCourseDAO getOrderCourseDAO;

    @Autowired
    private SaveOrderCourseDAO saveOrderCourseDAO;

    @Autowired
    private UpdateOrderCourseDAO updateOrderCourseDAO;
    @Autowired
    private CouponInfoBO couponInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;
    @Autowired
    private StudentInfoBO studentInfoBO;

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
        order.setOrderStateEnum(OrderStateEnum.unPay);

//        saveOrderCourseDAO.save(order);

        return order;
    }

    public void update(OrderBuyCourseDTO order){
        updateOrderCourseDAO.update(order);
    }

    public void updateOrderSateAfterPay(OrderBuyCourseDTO order){
        order.setOrderStateEnum(OrderStateEnum.payed);
        update(order);
    }

    /**
     * 获取订单资料
     * @param orderId 订单id
     * @return
     */
    public OrderBuyCourseDTO getCourseOrder(String orderId){
        Long id = Long.parseLong(orderId);
        return getOrderCourseDAO.get(OrderBuyCourseDTO.class, id);
    }

    /**
     * 获取订单资料
     * @param courseId 学生悬赏课程的Id
     * @return
     */
    public OrderBuyCourseDTO getRewardOrder(String courseId, OrderStateEnum state){
        return getOrderCourseDAO.getRewardOrderByCourseId(Long.parseLong( courseId ), state);
    }

    /**
     * 获取学生角色的未付款课程订单
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<OrderBuyCourseDTO> getCourseOrderAsStudent(Long studentId, OrderStateEnum orderStateEnum) throws Exception{
        return getOrderCourseDAO.getCourseOrderAsStudent(studentId, orderStateEnum);
    }

    /**
     * 获取学生角色的未付款课程订单
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<OrderBuyCourseDTO> getCourseOrderAsStudent(Long studentId) throws Exception{
        return getOrderCourseDAO.findAllByStudentId(studentId);
    }

    /**
     * 存储订单
     * @param order
     * @return
     */
    public OrderBuyCourseDTO save(OrderBuyCourseDTO order){
        return saveOrderCourseDAO.save(order);
    }

    /**
     * 课程的订单，退款，会更新订单信息、学生余额，删除优惠券
     * @param orderDTO
     * @param studentDTO
     * @return
     * @throws Exception
     */
    public boolean courseRefund(OrderBuyCourseDTO orderDTO, StudentDTO studentDTO) throws Exception{
        //修改订单状态
        orderDTO.setOrderStateEnum(OrderStateEnum.agreeRefund);
        //获取退款价格
        Double price = orderDTO.getAmount();
        Long couponId = orderDTO.getCouponId();
        if ( couponId != null && !couponId.equals(0L) ){
            //如果使用了优惠券，则把这个价格去除
            CouponDTO couponDTO = couponInfoBO.getCouponDTO(couponId);
            price = price - couponDTO.getValue();
            if (price < 0){
                throw new Exception("价格出错");
            }
            //删除优惠券
            couponInfoBO.delete(couponDTO);
        }
        //退款
        studentDTO.setVirtualCurrency( studentDTO.getVirtualCurrency() + price );
        //更新学生余额
        studentInfoBO.updateStudent(studentDTO);
        //再次修改订单状态
        orderDTO.setOrderStateEnum( OrderStateEnum.refundCompete);
        //更新订单状态
        orderInfoBO.update(orderDTO);
        return true;
    }

}
