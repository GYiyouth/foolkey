package foolkey.pojo.root.bo.order_course;

import foolkey.pojo.root.DAO.order_ask_question.GetOrderAskQuestionDAO;
import foolkey.pojo.root.DAO.order_ask_question.SaveOrderAskQuestionDAO;
import foolkey.pojo.root.DAO.order_ask_question.UpdateOrderAskQuestionDAO;
import foolkey.pojo.root.DAO.order_buy_answer.GetOrderBuyAnswerDAO;
import foolkey.pojo.root.DAO.order_buy_answer.SaveOrderBuyAnswerDAO;
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
import foolkey.pojo.root.vo.assistObject.RoleEnum;
import foolkey.pojo.root.vo.assistObject.TeachMethodEnum;
import foolkey.pojo.send_to_client.OrderBuyCourseAsStudentDTO;
import foolkey.pojo.send_to_client.OrderBuyCourseAsTeacherSTCDTO;
import foolkey.pojo.send_to_client.OrderBuyCourseWithStudentAsTeacherSTCDTO;
import foolkey.pojo.root.vo.dto.*;
import foolkey.pojo.send_to_client.OrderBuyRewardAsTeacherSTCDTO;
import foolkey.tool.StaticVariable;
import foolkey.tool.constant_values.RewardLimit;
import foolkey.tool.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    private UpdateOrderAskQuestionDAO updateOrderAskQuestionDAO;
    @Autowired
    private SaveOrderAskQuestionDAO saveOrderAskQuestionDAO;
    @Autowired
    private SaveOrderBuyAnswerDAO saveOrderBuyAnswerDAO;

    /**
     * 根据orderId获取买课订单信息
     *
     * @param orderId
     * @return
     */
    public OrderBuyCourseDTO getOrderBuyCourseDTOByOrderId(Long orderId) {
        return getOrderCourseDAO.get(OrderBuyCourseDTO.class, orderId);
    }

    /**
     * 根据orderId获取买Key信息
     *
     * @param orderId
     * @return
     */
    public OrderBuyKeyDTO getOrderBuyKeyDTOByOrderId(Long orderId) {
        return getOrderBuyKeyDAO.get(OrderBuyKeyDTO.class, orderId);
    }

    /**
     * 根据orderId获取回答的订单信息
     *
     * @param orderId
     * @return
     */
    public OrderBuyAnswerDTO getOrderBuyAnswerDTOByOrderId(Long orderId) {
        return getOrderBuyAnswerDAO.get(OrderBuyAnswerDTO.class, orderId);
    }

    /**
     * 根据orderId获取提问订单信息
     *
     * @param orderId
     * @return
     */
    public OrderAskQuestionDTO getOrderAskQuestionDTOByOrderId(Long orderId) {
        return getOrderAskQuestionDAO.get(OrderAskQuestionDTO.class, orderId);
    }

    /**
     * 根据orderId获取订单信息
     *
     * @param orderId
     * @return
     */
    public OrderRefundDTO getOrderRefundDTOByOrderId(Long orderId) {
        return getOrderRefoundDAO.get(OrderRefundDTO.class, orderId);
    }


    /**
     * 根据信息生成订单
     * 不会修改课程的状态
     *
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
    ) {
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

    public void update(OrderBuyCourseDTO order) {
        updateOrderCourseDAO.update(order);
    }

    public void updateOrderSateAfterPay(OrderBuyCourseDTO order) {
        order.setOrderStateEnum(OrderStateEnum.已付款);
        update(order);
    }

    /**
     * 获取订单资料
     *
     * @param orderId 订单id
     * @return
     */
    public OrderBuyCourseDTO getCourseOrder(String orderId) {
        Long id = Long.parseLong(orderId);
        return getOrderCourseDAO.get(OrderBuyCourseDTO.class, id);
    }

    /**
     * 获取订单资料
     *
     * @param courseId 学生悬赏课程的Id
     * @return
     */
    public OrderBuyCourseDTO getRewardOrder(String courseId, OrderStateEnum state, Integer pageNo) {
        return getOrderCourseDAO.getRewardOrderByCourseId(Long.parseLong(courseId), state, pageNo);
    }

    /**
     * 获取学生角色的未付款课程订单
     *
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<OrderBuyCourseDTO> getCourseOrderAsStudent(Long studentId, OrderStateEnum orderStateEnum, Integer pageNo) throws Exception {
        return getOrderCourseDAO.getCourseOrderAsStudent(studentId, orderStateEnum, pageNo);
    }

    /**
     * 获取学生角色的未付款课程订单
     *
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<OrderBuyCourseDTO> getCourseOrderAsStudent(Long studentId) throws Exception {
        return getOrderCourseDAO.findAllByStudentId(studentId);
    }

    /**
     * 存储订单
     *
     * @param order
     * @return
     */
    public OrderBuyCourseDTO save(OrderBuyCourseDTO order) {
        return saveOrderCourseDAO.save(order);
    }

    /**
     * 课程的订单，退款，会更新订单信息、学生余额，删除优惠券
     *
     * @param orderDTO
     * @param studentDTO
     * @return
     * @throws Exception
     */
    public boolean courseRefund(OrderBuyCourseDTO orderDTO, StudentDTO studentDTO) throws Exception {
        //修改订单状态
        orderDTO.setOrderStateEnum(OrderStateEnum.同意退款);
        //获取退款价格
        Double price = orderDTO.getAmount();
        Long couponId = orderDTO.getCouponId();
        if (couponId != null && !couponId.equals(0L)) {
            //如果使用了优惠券，则把这个价格去除
            CouponDTO couponDTO = couponInfoBO.getCouponDTO(couponId);
            price = price - couponDTO.getValue();
            if (price < 0) {
                throw new Exception("价格出错");
            }
            //删除优惠券
            couponInfoBO.delete(couponDTO);
        }
        //退款
        studentDTO.setVirtualCurrency(studentDTO.getVirtualCurrency() + price);
        //更新学生余额
        studentInfoBO.updateStudent(studentDTO);
        //再次修改订单状态
        orderDTO.setOrderStateEnum(OrderStateEnum.退款完成);
        //更新订单状态
        orderInfoBO.update(orderDTO);
        return true;
    }


    /**
     * 学生=》按照订单状态、课程种类(悬赏-课程),分页获取订单信息
     *
     * @param studentId
     * @param courseTypeEnum
     * @param orderStateEnum
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ArrayList<OrderBuyCourseDTO> getOrderBuyCourseDTOAsStudent(Long studentId, CourseTypeEnum courseTypeEnum, OrderStateEnum orderStateEnum, Integer pageNo, Integer pageSize) throws Exception {
        String hql = "select ob from OrderBuyCourseDTO ob where ob.userId = ? and ob.courseTypeEnum = ? and ob.orderStateEnum = ? order by createdTime desc";
        return getOrderCourseDAO.findByPage(hql, pageNo, pageSize, studentId, orderStateEnum);
    }

    /**
     * 老师=》按照订单状态、课程种类(悬赏-课程),分页获取订单信息
     *
     * @param teacherId
     * @param courseTypeEnum
     * @param orderStateEnum
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ArrayList<OrderBuyCourseDTO> getOrderBuyCourseDTOAsTeacher(Long teacherId, CourseTypeEnum courseTypeEnum, OrderStateEnum orderStateEnum, Integer pageNo, Integer pageSize) throws Exception {
        String hql = "select ob from OrderBuyCourseDTO ob where ob.teacherId = ? and ob.courseTypeEnum = ? and ob.orderStateEnum = ? order by createdTime desc";
        return getOrderCourseDAO.findByPage(hql, pageNo, pageSize, teacherId, orderStateEnum);
    }

    /**
     * 老师=》获取多个订单状态下，根据课程种类(悬赏-课程),分页获取订单信息
     *
     * @param teacherId
     * @param courseTypeEnum
     * @param pageNo
     * @param pageSize
     * @param params
     * @return
     * @throws Exception
     */
    public List<Long> getOrderBuyCourseDTOAsTeacherByOrderStates(Long teacherId, CourseTypeEnum courseTypeEnum, Integer pageNo, Integer pageSize, Object... params) throws Exception {
        return getOrderCourseDAO.findCourseIdAsTeacherByArbitraryStateCondition(teacherId, courseTypeEnum, pageNo, pageSize, params);
    }

    public List<Long> getOrderBuyCourseDTOAsStudentByOrderStates(Long studentId, CourseTypeEnum courseTypeEnum, Integer pageNo, Integer pageSize, Object... params) throws Exception {
        return getOrderCourseDAO.findCourseIdAsStudentByArbitraryStateCondition(studentId, courseTypeEnum, pageNo, pageSize, params);
    }


    /**
     * 根据课程id，获取规定状态下所有的订单-学生信息
     *
     * @param courseId
     * @param pageNo
     * @param pageSize
     * @param params
     * @return
     * @throws Exception
     */
    public List<OrderBuyCourseWithStudentAsTeacherSTCDTO> getOrderBuyCourseWithStudentAsTeacher(Long courseId, CourseTypeEnum courseTypeEnum, Integer pageNo, Integer pageSize, Object... params) throws Exception {
        List<OrderBuyCourseDTO> orderBuyCourseDTOS = getOrderCourseDAO.getOrderBuyCourseDTO(courseId, courseTypeEnum, pageNo, pageSize, params);
        List<OrderBuyCourseWithStudentAsTeacherSTCDTO> orderBuyCourseWithStudentAsTeacherSTCDTOS = new ArrayList<>();
        for (OrderBuyCourseDTO orderBuyCourseDTO : orderBuyCourseDTOS) {
            OrderBuyCourseWithStudentAsTeacherSTCDTO orderBuyCourseWithStudentAsTeacherSTCDTO = new OrderBuyCourseWithStudentAsTeacherSTCDTO();
            //获取学生信息
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(orderBuyCourseDTO.getUserId());
            orderBuyCourseWithStudentAsTeacherSTCDTO.setStudentDTO(studentDTO);
            //订单信息
            orderBuyCourseWithStudentAsTeacherSTCDTO.setOrderBuyCourseDTO(orderBuyCourseDTO);
            orderBuyCourseWithStudentAsTeacherSTCDTOS.add(orderBuyCourseWithStudentAsTeacherSTCDTO);
        }
        return orderBuyCourseWithStudentAsTeacherSTCDTOS;
    }


    /**
     * 获取课程订单来进行评价,作为学生
     *
     * @param studentDTO
     * @return
     */
    public List<OrderBuyCourseAsStudentDTO> getOrderBuyCourseToJudge(StudentDTO studentDTO, Integer pageNo) throws Exception {

        return getOrderBuyCourseToJudge(studentDTO, pageNo, RoleEnum.student);
    }

    /**
     * 老师获取待评价的课程订单
     *
     * @param teacher
     * @return
     */
    public List<OrderBuyCourseAsStudentDTO> getOrderToJudgeAsTeacher(StudentDTO teacher, Integer pageNo) throws Exception {
        return getOrderBuyCourseToJudge(teacher, pageNo, RoleEnum.teacher);
    }

    /**
     * 获取课程订单来进行评价,角色可选
     *
     * @param studentDTO
     * @return
     */
    public List<OrderBuyCourseAsStudentDTO> getOrderBuyCourseToJudge(StudentDTO studentDTO, Integer pageNo, RoleEnum roleEnum) throws Exception {
        List<OrderBuyCourseAsStudentDTO> result = new ArrayList<>();
        List<OrderBuyCourseDTO> orderList;
        //获取结束上课状态的订单
        String role = "";
        //获取身份
        switch (roleEnum) {
            case student: {
                role = "userId";
            }
            break;
            case teacher: {
                role = "teacherId";
            }
            break;
        }
        String hql = "from foolkey.pojo.root.vo.dto.OrderBuyCourseDTO t " +
                "where t." + role + " = ? and t.orderStateEnum = ? order by t.createdTime desc ";
        orderList = getOrderCourseDAO.findByPage(hql, pageNo, 10, studentDTO.getId(), OrderStateEnum.结束上课);

        //遍历该 list，获取课程信息、个人信息
        for (OrderBuyCourseDTO orderDTO : orderList) {
            OrderBuyCourseAsStudentDTO orderBuyCourseAsStudentDTO = new OrderBuyCourseAsStudentDTO();
            StudentDTO teacher = studentInfoBO.getStudentDTO(orderDTO.getTeacherId());
            TeacherDTO teacherDTO = teacherInfoBO.getTeacherDTO(orderDTO.getTeacherId());
            orderBuyCourseAsStudentDTO.setStudentDTO(teacher);
            orderBuyCourseAsStudentDTO.setTeacherDTO(teacherDTO);
            orderBuyCourseAsStudentDTO.setOrderDTO(orderDTO);
            //获取课程
            switch (orderDTO.getCourseTypeEnum()) {
                case 学生悬赏: {
                    RewardDTO rewardDTO = courseStudentBO.getCourseStudentDTO(orderDTO.getCourseId());
                    orderBuyCourseAsStudentDTO.setCourse(rewardDTO);
                }
                break;
                case 老师课程: {
                    CourseDTO courseDTO = courseTeacherBO.getCourseTeacherDTOById(orderDTO.getCourseId());
                    orderBuyCourseAsStudentDTO.setCourse(courseDTO);
                }
                break;
                default:
                    throw new Exception("类型错误");
            }
            result.add(orderBuyCourseAsStudentDTO);
        }
        return result;
    }

    public List test() {
        return getOrderCourseDAO.getOrderAllInfo(null);
    }


    /**
     * 根绝问题订单的id，获取提问订单的DTO
     *
     * @param orderAskQuestionId
     * @return
     */
    public OrderAskQuestionDTO getOrderAskQuestionDTOByOrderAskQuestionId(Long orderAskQuestionId) {
        return getOrderAskQuestionDAO.get(OrderAskQuestionDTO.class, orderAskQuestionId);
    }


    /**
     * 提问者付款之后，修改提问订单信息
     *
     * @param orderAskQuestionDTO
     */
    public void updateOrderSateAfterPayAsAsker(OrderAskQuestionDTO orderAskQuestionDTO) throws Exception {
        orderAskQuestionDTO.setOrderStateEnum(OrderStateEnum.已付款);
        //设置回答存活期
        orderAskQuestionDTO.setExistingTime(Time.getPermanentDate());
        updateOrderAskQuestionDAO.update(orderAskQuestionDTO);
    }

    /**
     * 回答者回答之后，修改提问订单信息
     *
     * @param orderAskQuestionDTO
     */
    public void updateOrderSateAfterAnswerAsAnswer(OrderAskQuestionDTO orderAskQuestionDTO) throws Exception {
        orderAskQuestionDTO.setOrderStateEnum(OrderStateEnum.已回答);
        updateOrderAskQuestionDAO.update(orderAskQuestionDTO);
    }

    /**
     * 创建提问问题的订单DTO
     *
     * @param studentDTO        提问者的DTO
     * @param questionAnswerDTO 问题DTO
     */
    public OrderAskQuestionDTO createOrderAsk(StudentDTO studentDTO, QuestionAnswerDTO questionAnswerDTO, Long couponId) throws Exception {
        //生成一个订单对象
        OrderAskQuestionDTO orderAskQuestionDTO = new OrderAskQuestionDTO();
        //各种对订单赋值
        orderAskQuestionDTO.setUserId(studentDTO.getId());
        orderAskQuestionDTO.setQuestionId(questionAnswerDTO.getId());
        orderAskQuestionDTO.setAmount(questionAnswerDTO.getPrice());
        orderAskQuestionDTO.setCreatedTime(Time.getCurrentDate());
        orderAskQuestionDTO.setExistingTime(Time.getOrderAskQuestionExistingDate());
        orderAskQuestionDTO.setOrderStateEnum(OrderStateEnum.未付款);
        orderAskQuestionDTO.setReceiverId(questionAnswerDTO.getAnswerId());
        orderAskQuestionDTO.setCouponId(couponId);
        //保存这个订单
        saveOrderAskQuestionDAO.save(orderAskQuestionDTO);
        return orderAskQuestionDTO;
    }


    /**
     * 保存一个围观的订单
     *
     * @param onlookerDTO
     * @param couponDTO
     * @param questionAnswerDTO
     * @return
     * @throws Exception
     */
    public OrderBuyAnswerDTO createOrderBuyAnswerDTO(StudentDTO onlookerDTO, CouponDTO couponDTO, QuestionAnswerDTO questionAnswerDTO) throws Exception {
        //生成一个订单对象
        OrderBuyAnswerDTO orderBuyAnswerDTO = new OrderBuyAnswerDTO();
        //各种对订单赋值
        orderBuyAnswerDTO.setUserId(onlookerDTO.getId());
        orderBuyAnswerDTO.setQuestionId(questionAnswerDTO.getId());
        orderBuyAnswerDTO.setAmount(StaticVariable.ONLOOK_VIRTUAL_PRICE);
        orderBuyAnswerDTO.setCreatedTime(Time.getCurrentDate());
        orderBuyAnswerDTO.setExistingTime(Time.getPermanentDate());
        orderBuyAnswerDTO.setOrderStateEnum(OrderStateEnum.已付款);
        orderBuyAnswerDTO.setPayTime(Time.getCurrentDate());
        orderBuyAnswerDTO.setCouponId(couponDTO.getId());
        //保存这个订单
        saveOrderBuyAnswerDAO.save(orderBuyAnswerDTO);
        return orderBuyAnswerDTO;
    }


    /**
     * 根据回答者、问题获取到订单
     * @param questionAnswerDTO
     * @param answererDTO
     * @return
     * @throws Exception
     */
    public OrderAskQuestionDTO getByQuestionAndAnswer(QuestionAnswerDTO questionAnswerDTO, StudentDTO answererDTO) throws Exception {
        return getOrderAskQuestionDAO.getByQuestionAndAnswer(questionAnswerDTO, answererDTO);
    }
}
