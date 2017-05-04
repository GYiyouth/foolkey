package foolkey.pojo.root.DAO.application_teacher_course;

import foolkey.pojo.root.DAO.base.DeleteBaseDAO;
import foolkey.pojo.root.vo.dto.ApplicationTeacherCourseDTO;
import foolkey.tool.BeanFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("deleteApplicationTeacherCourseDAO")
public class DeleteApplicationTeacherCourseDAO extends DeleteBaseDAO<ApplicationTeacherCourseDTO>{


    /**
     * 根据申请人Id，orderId删除申请
     * @param applicantId
     * @param orderId
     */
    public void delete(Long applicantId, Long orderId){
        SessionFactory sessionFactory = BeanFactory.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        String hql = "delete from foolkey.pojo.root.vo.dto.ApplicationTeacherCourseDTO atr " +
                "where atr.applicantId =:applicantId  AND atr.orderId = :orderId ";
        session.createQuery(hql)
                .setParameter("applicantId", applicantId)
                .setParameter("orderId", orderId)
                .executeUpdate();
    }

    /**
     * 删除所有老师课程的申请
     * @param orderId 订单Id
     */
    public void deleteAllByCourseId(Long orderId){
        SessionFactory sessionFactory = BeanFactory.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        String hql = "delete from ApplicationTeacherCourseDTO  atr " +
                "where atr.orderId = ?";

        session.createQuery(hql)
                .setParameter(0,orderId)
                .executeUpdate();
    }
}
