package foolkey.pojo.root.bo.CourseStudent;

import foolkey.pojo.root.CAO.CourseStudent.CourseStudentCAO;
import foolkey.pojo.root.DAO.course_student.GetCourseStudentDAO;
import foolkey.pojo.root.DAO.course_student.SaveCourseStudentDAO;
import foolkey.pojo.root.DAO.course_student.UpdateCourseStudentDAO;
import foolkey.pojo.root.DAO.course_teacher.SaveCourseTeacherDAO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.CourseStudentStateEnum;
import foolkey.pojo.root.vo.assistObject.DirectionEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.cacheDTO.CourseStudentPopularDTO;
import foolkey.pojo.root.vo.cacheDTO.CourseTeacherPopularDTO;
import foolkey.pojo.root.vo.cacheDTO.TeacherAllInfoDTO;
import foolkey.pojo.root.vo.dto.CourseStudentDTO;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.BeanFactory;
import foolkey.tool.StaticVariable;
import foolkey.tool.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by ustcg on 2017/4/30.
 */
@Service("courseStudentBO")
public class CourseStudentBO {

    @Resource(name = "localCache")
    private Cache cache;

    @Resource(name = "saveCourseStudentDAO")
    private SaveCourseStudentDAO saveCourseStudentDAO;
    @Autowired
    private GetCourseStudentDAO getCourseStudentDAO;
    @Autowired
    private UpdateCourseStudentDAO updateCourseStudentDAO;
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private CourseStudentCAO courseStudentCAO;




    /**
     * 添加最新的悬赏到缓存
     * @param technicTagEnum
     * @param size
     */
    public void fillCourseStudentPopularDTOToCache(TechnicTagEnum technicTagEnum, Integer size){
        if(technicTagEnum == null || size == null || size == 0){
            throw new NullPointerException("technicTagEnum/ size is null");
        }else {
            try {
                // 1.从数据库读取最新的size条记录
                ArrayList<CourseStudentDTO> courseStudentDTOS = getCourseStudentDAO.findByTechnicTagEnumAndResultSize(technicTagEnum, CourseStudentStateEnum.待接单, size);
                ArrayList<CourseStudentPopularDTO> courseStudentPopularDTOS = new ArrayList<>();
                for (CourseStudentDTO courseStudentDTO : courseStudentDTOS) {
                    StudentDTO studentDTO = studentInfoBO.getStudentDTO(courseStudentDTO.getCreatorId());
                    CourseStudentPopularDTO courseStudentPopularDTO = new CourseStudentPopularDTO();
                    courseStudentPopularDTO.setStudentDTO(studentDTO);
                    courseStudentPopularDTO.setCourseStudentDTO(courseStudentDTO);
                    courseStudentPopularDTOS.add(courseStudentPopularDTO);
                }
                //添加到缓存中
                //尾插
                if(courseStudentPopularDTOS.size()!=0) {
                    courseStudentCAO.addCourseStudentPopularDTOSToCache(technicTagEnum, courseStudentPopularDTOS, DirectionEnum.tail);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 分页获取某个标签下最新的悬赏
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ArrayList<CourseStudentPopularDTO> getCourseStudentPopularDTO(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize) throws Exception{
        //请求的内容超过内存大小
        //暂时不允许超过内存大小
        if((pageNo-1)*pageSize >= StaticVariable.cacheSize) {
            return new ArrayList<>();
        }else{
            if (courseStudentCAO.containsCourseStudent(technicTagEnum, pageNo, pageSize)) {
                System.out.println("缓存有！");
                return courseStudentCAO.getCourseStudentPopularDTO(technicTagEnum, pageNo, pageSize);
            }else {
                //缓存没有则数据库彻底没了
                System.out.println("缓存没有");
                return new ArrayList<>();
            }
        }
    }


    /**
     * 根据课程id获取悬赏信息
     * @param id
     * @return
     * @throws Exception
     */
    public CourseStudentDTO getCourseStudentDTOById(Long id) throws Exception{
        if(id == null){
            throw new NullPointerException("id is null");
        }else{
            CourseStudentDTO courseStudentDTO = courseStudentCAO.getCourseStudentDTOByCourseStudentId(id);
            if(courseStudentDTO != null ){
                System.out.println("缓存有！");
                return courseStudentDTO;
            }else{
                return getCourseStudentDAO.get(CourseStudentDTO.class,id);
            }
        }
    }


    /**
     * 发布悬赏
     * @param courseStudentDTO
     */
    public void publishCourseStudent(CourseStudentDTO courseStudentDTO){
        if(courseStudentDTO == null){
            throw new NullPointerException("课程内容为空");
        }else{
            saveCourseStudentDAO.save(courseStudentDTO);
        }
    }

    /**
     * 获取悬赏任务
     * @param id
     * @return
     */
    public CourseStudentDTO getCourseStudentDTO(Long id){
        return getCourseStudentDAO.get(CourseStudentDTO.class, id);
    }

    /**
     * 更新学生悬赏任务
     * @param courseDTO
     * @return
     */
    public CourseStudentDTO update(CourseStudentDTO courseDTO){
        updateCourseStudentDAO.update(courseDTO);
        return courseDTO;
    }
}
