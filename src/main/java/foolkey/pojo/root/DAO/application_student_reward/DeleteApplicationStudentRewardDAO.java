package foolkey.pojo.root.DAO.application_student_reward;

import foolkey.pojo.root.DAO.base.DeleteBaseDAO;
import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import foolkey.tool.BeanFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("deleteApplicationStudentRewardDAO")
public class DeleteApplicationStudentRewardDAO extends DeleteBaseDAO<ApplicationStudentRewardDTO>{

    /**
     * 删除学生悬赏申请
     * @param teacherId 申请的老师Id
     * @param studentId 处理的学生Id
     */
    public void delete(Long teacherId, Long studentId){
//        SessionFactory sessionFactory = BeanFactory.getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();

        String hql = " from foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO asr " +
                "where asr.applicantId = ?  AND asr.studentId = ? ";
//        session.createQuery(hql)
//                .setParameter("applicantId", teacherId)
//                .setParameter("studentId", studentId)
//                .executeUpdate();
        List<ApplicationStudentRewardDTO> list = (List<ApplicationStudentRewardDTO>)
                hibernateTemplate.find(hql, teacherId, studentId);
        hibernateTemplate.deleteAll( list );

    }

    /**
     * 删除所有学生悬赏申请
     * @param courseId 悬赏orderId
     */
    public void deleteAllByCourseId(Long courseId){
//        SessionFactory sessionFactory = BeanFactory.getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();

        String hql = "from ApplicationStudentRewardDTO  asr " +
                "where asr.rewardId = ?";
//
//        session.createQuery(hql)
//                .setParameter(0,courseId)
//                .;

        List<ApplicationStudentRewardDTO> list = (List<ApplicationStudentRewardDTO>)
                hibernateTemplate.find(hql, courseId);
        hibernateTemplate.deleteAll( list );
    }

}
