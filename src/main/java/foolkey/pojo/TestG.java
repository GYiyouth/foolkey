package foolkey.pojo;

import foolkey.pojo.root.DAO.course_teacher.GetCourseTeacherDAO;
import foolkey.tool.BeanFactory;

/**
 * Created by admin on 2017/4/28.
 */
public class TestG {
    public static void main(String[] args) {
        GetCourseTeacherDAO getCourseTeacherDAO = BeanFactory.getBean("getCourseTeacherDAO",GetCourseTeacherDAO.class);
        String hql = "select ct from CourseTeacherDTO ct,TeacherDTO t where ct.creatorId = t.id and ct.technicTagEnum = ? order by t.teacherAverageScore desc,ct.averageScore desc";
//        getCourseTeacherDAO.test(hql,1,4, 前端);
    }
}
