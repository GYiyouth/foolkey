package foolkey.pojo.root.DAO.course_teacher;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */



@Repository("getCourseTeacherDAO")
@Transactional
public class GetCourseTeacherDAO extends GetBaseDAO<CourseTeacherDTO>{

    /**
     * 根据技术分类，获取类别下面的所有热门课程
     * 此时内存没存满
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     */
    public ArrayList<CourseTeacherDTO> findCourseTeacherByPageLessCache(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize){
        ArrayList<CourseTeacherDTO> courseTeacherDTOS = new ArrayList<CourseTeacherDTO>();
//        String hql = "select ct from CourseTeacherDTO ct where ct.creatorId = t.id and ct.technicTagEnum = ? order by t.teacherAverageScore desc,ct.averageScore desc";
        String hql = "select ct from CourseTeacherDTO ct where ct.technicTagEnum = ? order by ct.sales desc,ct.averageScore desc";
        courseTeacherDTOS = findByPage(hql,pageNo,pageSize,technicTagEnum);
        for(CourseTeacherDTO courseTeacherDTO:courseTeacherDTOS){
            System.out.println(courseTeacherDTO);
        }
        return courseTeacherDTOS;
    }

    /**
     * 根据技术分类，获取类别下面的所有热门课程
     * 此时内存没存满
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     */
    public ArrayList<CourseTeacherDTO> findCourseTeacherByPageMoreCache(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize){
        ArrayList<CourseTeacherDTO> courseTeacherDTOS = new ArrayList<>();
        String hql = "select ct from CourseTeacherDTO ct where ct.technicTagEnum = ? order by ct.sales desc, ct.averageScore desc";
        courseTeacherDTOS = findByPage(hql,pageNo,pageSize,technicTagEnum);
        for(CourseTeacherDTO courseTeacherDTO:courseTeacherDTOS){
            System.out.println(courseTeacherDTO);
        }
        return courseTeacherDTOS;
    }

    /**
     * 根据技术分类，获取类别下面的所有热门课程
     * 自动任务中的添加100门课程
     * @param technicTagEnum
     * @return
     */
    public ArrayList<CourseTeacherDTO> autoAddCourseTeacherDTOToCache(TechnicTagEnum technicTagEnum){
        System.out.println("?");
        ArrayList<CourseTeacherDTO> courseTeacherDTOS = new ArrayList<>();
        String hql = "select ct from CourseTeacherDTO ct where ct.technicTagEnum = ? order by ct.sales desc, ct.averageScore desc";
        courseTeacherDTOS = findByPage(hql,1,3,technicTagEnum);
        for(CourseTeacherDTO courseTeacherDTO:courseTeacherDTOS){
            System.out.println(courseTeacherDTO);
        }
        return courseTeacherDTOS;
    }


}
