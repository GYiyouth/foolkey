package foolkey.pojo.root.DAO.course_teacher;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static foolkey.pojo.root.vo.assistObject.TechnicTagEnum.*;

/**
 * Created by admin on 2017/4/25.
 */



@Repository("getCourseTeacherDAO")
public class GetCourseTeacherDAO extends GetBaseDAO<CourseTeacherDTO>{

    public List<CourseTeacherDTO> findCourseTeacherByPage(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize){
        List<CourseTeacherDTO> courseTeacherDTOS = new ArrayList<>();
        String hql = "select ct from CourseTeacherDTO ct,TeacherDTO t where ct.creatorId = t.id and ct.technicTagEnum = ? order by t.teacherAverageScore desc,ct.averageScore desc";
        courseTeacherDTOS = findByPage(hql,pageNo,pageSize,technicTagEnum);
        for(CourseTeacherDTO courseTeacherDTO:courseTeacherDTOS){
            System.out.println(courseTeacherDTO);
        }
        return courseTeacherDTOS;

    }

    /*
    public List<CourseTeacherDTO> findByPage(final String hql, final int pageNo, final int pageSize, final Object... params) {
        List<CourseTeacherDTO> list = hibernateTemplate.execute(new HibernateCallback<List<CourseTeacherDTO>>() {
            @Override
            public List<CourseTeacherDTO> doInHibernate(Session session) throws HibernateException {
                //执行Hibernate分页查询
                Query query = session.createQuery("select ct from CourseTeacherDTO ct,TeacherDTO t where ct.industryTagEnum.name = ? and ct.creatorId = t.id order by t.teacherAverageScore desc,ct.averageScore desc");
                //为包含占位符的HQL语句设置参数
                for (int i = 0, len = params.length; i < len; i++) {
                    query.setParameter(i + "", params[i]);
                }
                List<CourseTeacherDTO> result = query.setFirstResult((pageNo - 1) * pageSize)
                        .setMaxResults(pageSize)
                        .list();
                return result;
            }
        });
        return list;
    }
    */
    public void test(final String hql, final int pageNo, final int pageSize, final Object... params){
        List<CourseTeacherDTO> list = hibernateTemplate.execute(new HibernateCallback<List<CourseTeacherDTO>>() {
            @Override
            public List<CourseTeacherDTO> doInHibernate(Session session) throws HibernateException {
                //执行Hibernate分页查询
                System.out.println("hql:"+hql);
                Query query = session.createQuery(hql);
                //为包含占位符的HQL语句设置参数
                for (int i = 0, len = params.length; i < len; i++) {
                    query.setParameter(i, params[i]);
                }
                List<CourseTeacherDTO> result = query.setFirstResult((pageNo - 1) * pageSize)
                        .setMaxResults(pageSize)
                        .list();

                for(CourseTeacherDTO courseTeacherDTO:result){
                    System.out.println("---"+courseTeacherDTO+"----"+courseTeacherDTO.getTechnicTagEnum()+"----"+courseTeacherDTO.getId());
                }
                return result;
            }
        });
    }
}
