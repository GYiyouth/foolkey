package foolkey.pojo.root.bo.order_course;

import foolkey.pojo.root.DAO.order_ask_question.GetOrderAskQuestionDAO;
import foolkey.pojo.root.DAO.order_buy_answer.GetOrderBuyAnswerDAO;
import foolkey.pojo.root.DAO.order_buy_key.GetOrderBuyKeyDAO;
import foolkey.pojo.root.DAO.order_course.GetOrderCourseDAO;
import foolkey.pojo.root.DAO.order_course.SaveOrderCourseDAO;
import foolkey.pojo.root.DAO.order_course.UpdateOrderCourseDAO;
import foolkey.pojo.root.DAO.order_refound.GetOrderRefoundDAO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.bo.coupon.CouponInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.assistObject.TeachMethodEnum;
import foolkey.pojo.send_to_client.OrderBuyCourseAsTeacherSTCDTO;
import foolkey.pojo.send_to_client.OrderBuyCourseWithStudentAsTeacherSTCDTO;
import foolkey.pojo.root.vo.dto.*;
import foolkey.pojo.send_to_client.OrderBuyRewardAsTeacherSTCDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private TeacherInfoBO teacherInfoBO;
    @Autowired
    private RewardBO courseStudentBO;
    @Autowired
    private CourseBO courseTeacherBO;
    @Autowired
    private GetOrderBuyKeyDAO getOrderBuyKeyDAO;
    @Autowired
    private GetOrderBuyAnswerDAO getOrderBuyAnswerDAO;
    @Autowired
    private GetOrderAskQuestionDAO getOrderAskQuestionDAO;
    @Autowired
    private GetOrderRefoundDAO getOrderRefoundDAO;

    /**
     * 根据orderId获取买课订单信息
     * @param orderId
     * @return
     */
    public OrderBuyCourseDTO getOrderBuyCourseDTOByOrderId(Long orderId){
        return getOrderCourseDAO.get(OrderBuyCourseDTO.class,orderId);
    }

    /**
     * 根据orderId获取买Key信息
     * @param orderId
     * @return
     */
    public OrderBuyKeyDTO getOrderBuyKeyDTOByOrderId(Long orderId){
        return getOrderBuyKeyDAO.get(OrderBuyKeyDTO.class,orderId);
    }

    /**
     * 根据orderId获取回答的订单信息
     * @param orderId
     * @return
     */
    public OrderBuyAnswerDTO getOrderBuyAnswerDTOByOrderId(Long orderId){
        return getOrderBuyAnswerDAO.get(OrderBuyAnswerDTO.class,orderId);
    }

    /**
     * 根据orderId获取提问订单信息
     * @param orderId
     * @return
     */
    public OrderAskQuestionDTO getOrderAskQuestionDTOByOrderId(Long orderId){
        return getOrderAskQuestionDAO.get(OrderAskQuestionDTO.class,orderId);
    }

    /**
     * 根据orderId获取订单信息
     * @param orderId
     * @return
     */
    public OrderRefundDTO getOrderRefundDTOByOrderId(Long orderId){
        return getOrderRefoundDAO.get(OrderRefundDTO.class,orderId);
    }


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
        order.setOrderStateEnum(OrderStateEnum.未付款);

//        saveOrderCourseDAO.save(order);

        return order;
    }

    public void update(OrderBuyCourseDTO order){
        updateOrderCourseDAO.update(order);
    }

    public void updateOrderSateAfterPay(OrderBuyCourseDTO order){
        order.setOrderStateEnum(OrderStateEnum.已付款);
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
        orderDTO.setOrderStateEnum(OrderStateEnum.同意退款);
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
        orderDTO.setOrderStateEnum( OrderStateEnum.退款完成);
        //更新订单状态
        orderInfoBO.update(orderDTO);
        return true;
    }


    /**
     * 学生=》按照订单状态、课程种类(悬赏-课程),分页获取订单信息
     * @param studentId
     * @param courseTypeEnum
     * @param orderStateEnum
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ArrayList<OrderBuyCourseDTO> getOrderBuyCourseDTOAsStudent(Long studentId, CourseTypeEnum courseTypeEnum, OrderStateEnum orderStateEnum, Integer pageNo, Integer pageSize)throws Exception{
        String hql = "select ob from OrderBuyCourseDTO ob where ob.userId = ? and ob.courseTypeEnum = ? and ob.orderStateEnum = ? order by createdTime desc";
        return getOrderCourseDAO.findByPage(hql,pageNo,pageSize,studentId,orderStateEnum);
    }

    /**
     * 老师=》按照订单状态、课程种类(悬赏-课程),分页获取订单信息
     * @param teacherId
     * @param courseTypeEnum
     * @param orderStateEnum
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ArrayList<OrderBuyCourseDTO> getOrderBuyCourseDTOAsTeacher(Long teacherId, CourseTypeEnum courseTypeEnum, OrderStateEnum orderStateEnum, Integer pageNo, Integer pageSize)throws Exception{
        String hql = "select ob from OrderBuyCourseDTO ob where ob.teacherId = ? and ob.courseTypeEnum = ? and ob.orderStateEnum = ? order by createdTime desc";
        return getOrderCourseDAO.findByPage(hql,pageNo,pageSize,teacherId,orderStateEnum);
    }

    /**
     * 老师=》获取多个订单状态下，根据课程种类(悬赏-课程),分页获取订单信息
     * @param teacherId
     * @param courseTypeEnum
     * @param pageNo
     * @param pageSize
     * @param params
     * @return
     * @throws Exception
     */
    public List<Long> getOrderBuyCourseDTOAsTeacherByOrderStates(Long teacherId, CourseTypeEnum courseTypeEnum, Integer pageNo, Integer pageSize, Object... params)throws Exception{
        System.out.println(courseTypeEnum+"：课程种类");
        return getOrderCourseDAO.findCourseIdByArbitraryStateCondition(teacherId,courseTypeEnum,pageNo,pageSize,params);
    }

    /**
     * 把订单信息封装成传送的客户端的DTO（订单信息、学生、老师、课程）
     * @param orderBuyCourseDTOS
     * @return
     * @throws Exception
     */
    public ArrayList<OrderBuyCourseAsTeacherSTCDTO> convertOrderBuyCourseDTOIntoOrderBuyCourseSTCDTO(List<OrderBuyCourseDTO> orderBuyCourseDTOS) throws Exception{
        ArrayList<OrderBuyCourseAsTeacherSTCDTO> orderBuyCourseAsTeacherSTCDTOS = new ArrayList<>();
//        for(OrderBuyCourseDTO orderBuyCourseDTO:orderBuyCourseDTOS){
//            OrderBuyCourseAsTeacherSTCDTO orderBuyCourseSTCAsTeacherDTO = new OrderBuyCourseAsTeacherSTCDTO();
//            //获取-赋值  订单信息
//            orderBuyCourseSTCAsTeacherDTO.setOrderBuyCourseDTO(orderBuyCourseDTO);
//            //获取-赋值  悬赏的信息
//            CourseDTO courseTeacherDTO = courseTeacherBO.getCourseTeacherDTOById(orderBuyCourseDTO.getCourseId());
//            orderBuyCourseSTCAsTeacherDTO.setCourseTeacherDTO(courseTeacherDTO);
//            //获取、赋值  学生信息(发布者)
//            StudentDTO studentDTO = studentInfoBO.getStudentDTO(orderBuyCourseDTO.getUserId());
//            orderBuyCourseSTCAsTeacherDTO.setStudentDTO(studentDTO);
//            //获取、赋值   老师信息(接悬赏)
//            TeacherAllInfoDTO teacherAllInfoDTO = teacherInfoBO.getTeacherAllInfoDTO(orderBuyCourseDTO.getTeacherId());
//            orderBuyCourseSTCAsTeacherDTO.setTeacherAllInfoDTO(teacherAllInfoDTO);
//
//            orderBuyCourseAsTeacherSTCDTOS.add(orderBuyCourseSTCAsTeacherDTO);
//        }
        return orderBuyCourseAsTeacherSTCDTOS;
    }

    /**
     * 把订单信息封装成传送的客户端的DTO（订单信息、学生、老师、悬赏）
     * @param orderBuyCourseDTOS
     * @return
     * @throws Exception
     */
    public ArrayList<OrderBuyRewardAsTeacherSTCDTO> convertOrderBuyCourseDTOIntoOrderBuyRewardSTCDTO(List<OrderBuyCourseDTO> orderBuyCourseDTOS) throws Exception{
        ArrayList<OrderBuyRewardAsTeacherSTCDTO> orderBuyRewardAsTeacherSTCDTOS = new ArrayList<>();
//        for(OrderBuyCourseDTO orderBuyCourseDTO:orderBuyCourseDTOS){
//            OrderBuyRewardAsTeacherSTCDTO orderBuyRewardAsTeacherSTCDTO = new OrderBuyRewardAsTeacherSTCDTO();
//            //获取-赋值  订单信息
//            orderBuyRewardAsTeacherSTCDTO.setOrderBuyCourseDTO(orderBuyCourseDTO);
//            //获取-赋值  悬赏的信息
//            RewardDTO courseStudentDTO = courseStudentBO.getCourseStudentDTOById(orderBuyCourseDTO.getCourseId());
//            orderBuyRewardAsTeacherSTCDTO.setCourseStudentDTO(courseStudentDTO);
//            //获取、赋值  学生信息(发布者)
//            StudentDTO studentDTO = studentInfoBO.getStudentDTO(orderBuyCourseDTO.getUserId());
//            orderBuyRewardAsTeacherSTCDTO.setStudentDTO(studentDTO);
//            //获取、赋值   老师信息(接悬赏)
//            TeacherAllInfoDTO teacherAllInfoDTO = teacherInfoBO.getTeacherAllInfoDTO(orderBuyCourseDTO.getTeacherId());
//            orderBuyRewardAsTeacherSTCDTO.setTeacherAllInfoDTO(teacherAllInfoDTO);
//
//            orderBuyRewardAsTeacherSTCDTOS.add(orderBuyRewardAsTeacherSTCDTO);
//        }
        return orderBuyRewardAsTeacherSTCDTOS;
    }


    /**
     * 根据课程id，获取规定状态下所有的订单-学生信息
     * @param courseId
     * @param pageNo
     * @param pageSize
     * @param params
     * @return
     * @throws Exception
     */
    public List<OrderBuyCourseWithStudentAsTeacherSTCDTO> getOrderBuyCourseWithStudentAsTeacher(Long courseId, CourseTypeEnum courseTypeEnum, Integer pageNo, Integer pageSize,Object... params)throws Exception{
        List<OrderBuyCourseDTO> orderBuyCourseDTOS = getOrderCourseDAO.getOrderBuyCourseDTO(courseId,courseTypeEnum,pageNo,pageSize,params);
        List<OrderBuyCourseWithStudentAsTeacherSTCDTO> orderBuyCourseWithStudentAsTeacherSTCDTOS = new ArrayList<>();
        for(OrderBuyCourseDTO orderBuyCourseDTO:orderBuyCourseDTOS){
            OrderBuyCourseWithStudentAsTeacherSTCDTO orderBuyCourseWithStudentAsTeacherSTCDTO = new OrderBuyCourseWithStudentAsTeacherSTCDTO();
            //获取学生信息
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(orderBuyCourseDTO.getUserId());
            orderBuyCourseWithStudentAsTeacherSTCDTO.setStudentDTO(studentDTO);
            //订单信息
            orderBuyCourseWithStudentAsTeacherSTCDTO.setOrderBuyCourseDTO(orderBuyCourseDTO);
            orderBuyCourseWithStudentAsTeacherSTCDTOS.add(orderBuyCourseWithStudentAsTeacherSTCDTO);
        }
        return  orderBuyCourseWithStudentAsTeacherSTCDTOS;
    }

}
