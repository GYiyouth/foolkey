package foolkey.pojo.root.DAO.course_teacher;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by admin on 2017/4/25.
 */



@Repository("getCourseTeacherDAO")
@Transactional
public class GetCourseTeacherDAO extends GetBaseDAO<CourseDTO>{

    /**
     * 根据技术分类，查询前n条记录
     * @param technicTagEnum
     * @param resultSize
     * @return
     */
    public ArrayList<CourseDTO> findByTechnicTagEnumAndResultSize(TechnicTagEnum technicTagEnum, Integer resultSize){
        ArrayList<CourseDTO> courseTeacherDTOS = new ArrayList<>();
        String hql = "select ct from CourseTeacherDTO ct where ct.technicTagEnum = ? order by ct.sales desc,ct.averageScore desc";
        courseTeacherDTOS = findByPage(hql,1,resultSize,technicTagEnum);
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
    public ArrayList<CourseDTO> findCourseTeacherByPageLessCache(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize){
        ArrayList<CourseDTO> courseTeacherDTOS = new ArrayList<CourseDTO>();
//        String hql = "select ct from CourseTeacherDTO ct where ct.creatorId = t.id and ct.technicTagEnum = ? order by t.teacherAverageScore desc,ct.averageScore desc";
        String hql = "select ct from CourseTeacherDTO ct where ct.technicTagEnum = ? order by ct.sales desc,ct.averageScore desc";
        courseTeacherDTOS = findByPage(hql,pageNo,pageSize,technicTagEnum);
        for(CourseDTO courseTeacherDTO:courseTeacherDTOS){
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
    public ArrayList<CourseDTO> findCourseTeacherByPageMoreCache(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize){
        ArrayList<CourseDTO> courseTeacherDTOS = new ArrayList<>();
        String hql = "select ct from CourseTeacherDTO ct where ct.technicTagEnum = ? order by ct.sales desc, ct.averageScore desc";
        courseTeacherDTOS = findByPage(hql,pageNo,pageSize,technicTagEnum);
        for(CourseDTO courseTeacherDTO:courseTeacherDTOS){
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
    public ArrayList<CourseDTO> autoAddCourseTeacherDTOToCache(TechnicTagEnum technicTagEnum){
        System.out.println("?");
        ArrayList<CourseDTO> courseTeacherDTOS = new ArrayList<>();
        String hql = "select ct from CourseTeacherDTO ct where ct.technicTagEnum = ? order by ct.sales desc, ct.averageScore desc";
        courseTeacherDTOS = findByPage(hql,1,3,technicTagEnum);
        for(CourseDTO courseTeacherDTO:courseTeacherDTOS){
            System.out.println(courseTeacherDTO);
        }
        return courseTeacherDTOS;
    }


}
