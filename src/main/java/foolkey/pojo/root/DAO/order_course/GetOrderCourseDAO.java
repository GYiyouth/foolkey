package foolkey.pojo.root.DAO.order_course;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.dto.OrderBuyAnswerDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getOrderCourseDAO")
public class GetOrderCourseDAO extends GetBaseDAO<OrderBuyCourseDTO>{

    /**
     * 获取学生角色的 某种类别 订单
     * @param studentId
     * @return
     */
    public List<OrderBuyCourseDTO> getCourseOrderAsStudent(Long studentId, OrderStateEnum orderState){
        List<OrderBuyCourseDTO> list = (List<OrderBuyCourseDTO>)
                hibernateTemplate.find("from foolkey.pojo.root.vo.dto.OrderBuyCourseDTO t " +
                        "where t.userId = ? and t.orderStateEnum = ?", studentId, orderState);
        return list;
    }

    /**
     * 获取学生悬赏的订单
     * @param rewardId 悬赏课程的Id
     * @return
     */
    public OrderBuyCourseDTO getRewardOrderByCourseId(Long rewardId, OrderStateEnum state){
        List <OrderBuyCourseDTO> list =
                (List<OrderBuyCourseDTO>)
                hibernateTemplate.find(
                "from OrderBuyCourseDTO ob where ob.courseId = ? " +
                        "and ob.orderStateEnum = ?", rewardId, state
        );
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    public List<OrderBuyCourseDTO> findAllByStudentId(Long studentId){
        return
                (List<OrderBuyCourseDTO>)
                        hibernateTemplate.find(
                                "from OrderBuyCourseDTO ob where ob.userId = ?", studentId);
    }


    /**
     * 获取老师下面哪些课程处于规定的状态
     * @param teacherId
     * @param courseTypeEnum
     * @param pageNo
     * @param pageSize
     * @param params
     * @return
     */
    public List<Long> findCourseIdByArbitraryStateCondition(Long teacherId, CourseTypeEnum courseTypeEnum,Integer pageNo, Integer pageSize, final Object... params){
        System.out.println("课程种类："+courseTypeEnum);
        List<Long> list = hibernateTemplate.execute(new HibernateCallback<List<Long>>() {
            @Override
            public List<Long> doInHibernate(Session session) throws HibernateException {
                String hql = "select obc.courseId from OrderBuyCourseDTO obc where obc.teacherId = ? and obc.courseTypeEnum = ? ";
                for( int i = 0 ,len =  params.length ; i<len;i++){
                    if(i==0) {
                        //第一个需要时and关键字
                        hql += "and (obc.orderStateEnum = ? ";
                    }else{
                        //后面的都是or关键字
                        hql += "or obc.orderStateEnum = ?";
                    }
                }
                //最后排序，把相同状态的放一起
                hql += ") group by obc.courseId";

                //执行Hibernate分页查询
                Query query = session.createQuery(hql);
                query.setParameter(0,teacherId);
                query.setParameter(1,courseTypeEnum);
                //为包含占位符的HQL语句设置参数
                //注意是从第三个参数开始的
                for (int i = 0, len = params.length; i < len; i++) {
                    query.setParameter((i+2) , params[i]);
                }
                List<Long> result = query.setFirstResult((pageNo - 1) * pageSize)
                        .setMaxResults(pageSize)
                        .list();
                return result;
            }
        });
        return list;
    }

    /**
     * 根据课程id，获取下面有哪些学生
     * @param courseId
     * @param courseTypeEnum
     * @param pageNo
     * @param pageSize
     * @param params
     * @return
     */
    public List<OrderBuyCourseDTO> getOrderBuyCourseDTO(Long courseId, CourseTypeEnum courseTypeEnum, Integer pageNo, Integer pageSize, Object... params){
        System.out.println("课程类型--："+courseTypeEnum);
        System.out.println("课程id:"+courseId);
        List<OrderBuyCourseDTO> list = hibernateTemplate.execute(new HibernateCallback<List<OrderBuyCourseDTO>>() {
            @Override
            public List<OrderBuyCourseDTO> doInHibernate(Session session) throws HibernateException {
                String hql = " from OrderBuyCourseDTO obc where obc.courseId = ? and obc.courseTypeEnum = ? ";
                for( int i = 0 ,len =  params.length ; i<len;i++){
                    if(i==0) {
                        //第一个需要时and关键字
                        hql += "and (obc.orderStateEnum = ? ";
                    }else{
                        //后面的都是or关键字
                        hql += "or obc.orderStateEnum = ? ";
                    }

                }
                hql += ")";
                //最后排序，把相同状态的放一起
//                hql += " group by obc.courseId";

                //执行Hibernate分页查询
                Query query = session.createQuery(hql);
                query.setParameter(0,courseId);
                query.setParameter(1,courseTypeEnum);
                //为包含占位符的HQL语句设置参数
                //注意是从第二个参数开始的
                for (int i = 0, len = params.length; i < len; i++) {
                    query.setParameter((i+2) , params[i]);

                }
                List<OrderBuyCourseDTO> result = query.setFirstResult((pageNo - 1) * pageSize)
                        .setMaxResults(pageSize)
                        .list();
                for(OrderBuyCourseDTO orderBuyCourseDTO:result){
                    System.out.println("测试结果："+orderBuyCourseDTO);
                }
                return result;
            }
        });
        return list;

    }
}
