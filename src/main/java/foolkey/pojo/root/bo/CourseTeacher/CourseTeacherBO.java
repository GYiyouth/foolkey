package foolkey.pojo.root.bo.CourseTeacher;

import foolkey.pojo.root.CAO.CourseTeacher.CourseTeacherCAO;
import foolkey.pojo.root.DAO.course_teacher.GetCourseTeacherDAO;
import foolkey.pojo.root.DAO.course_teacher.SaveCourseTeacherDAO;
import foolkey.pojo.root.DAO.course_teacher.UpdateCourseTeacherDAO;
import foolkey.pojo.root.DataStructure.Node;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.*;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import foolkey.tool.StaticVariable;
import foolkey.tool.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by admin on 2017/4/26.
 */
@Service("courseTeacherBO")
public class CourseTeacherBO {

    @Resource(name = "localCache")
    private Cache cache;

    @Resource(name = "courseTeacherCAO")
    private CourseTeacherCAO courseTeacherCAO;

    @Resource(name = "getCourseTeacherDAO")
    private GetCourseTeacherDAO getCourseTeacherDAO;

    @Resource(name = "saveCourseTeacherDAO")
    private SaveCourseTeacherDAO saveCourseTeacherDAO;

    @Resource(name = "updateCourseTeacherDAO")
    private UpdateCourseTeacherDAO updateCourseTeacherDAO;

    @Autowired
    private TeacherInfoBO teacherInfoBO;
    @Autowired
    private StudentInfoBO studentInfoBO;

    /**
     * 获取某个标签下流行的课程
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ArrayList<CourseTeacherDTO> getPopularCourseTeacher(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize) throws Exception{
        //请求的内容超过内存大小
        if((pageNo-1)*pageSize >= StaticVariable.cacheSize) {
            return  getCourseTeacherDAO.findCourseTeacherByPageMoreCache(technicTagEnum,pageNo,pageSize);
        }else{
            if (courseTeacherCAO.contaionsCourseTeacher(technicTagEnum, pageNo, pageSize)) {
                System.out.println("缓存有！");
                return courseTeacherCAO.getCourseTeacherPopular(technicTagEnum, pageNo, pageSize);
            }else {
                System.out.println("缓存没有");
                return getCourseTeacherDAO.findCourseTeacherByPageLessCache(technicTagEnum, pageNo, pageSize);
            }
        }
    }

    /**
     * 发布课程
     * @param courseTeacherDTO
     */
    public void publishCourseTeacher(CourseTeacherDTO courseTeacherDTO){
        if(courseTeacherDTO == null){
            throw new NullPointerException("课程内容为空");
        }else{
            saveCourseTeacherDAO.save(courseTeacherDTO);
        }
    }

    /**
     * 验证一个课程是否可以被购买
     * 课程状态、课程老师的认证、封禁状态
     * @param courseDTO
     * @return
     */
    public boolean  checkCourse(CourseTeacherDTO courseDTO){
        try {
            if (courseDTO.getCourseTeacherStateEnum()
                    .compareTo(CourseTeacherStateEnum.可上课) == 0){ // 课程
                Long teacherId = courseDTO.getCreatorId();
                TeacherDTO teacherDTO = teacherInfoBO.getTeacherDTO(teacherId);
                if (teacherDTO.getVerifyState()
                        .compareTo(VerifyStateEnum.verified) == 0){ // 老师是否认证
                    StudentDTO studentDTO = studentInfoBO.getStudentDTO(teacherId);
                    if (studentDTO.getUserStateEnum()
                            .compareTo(UserStateEnum.normal) == 0 // 老师是否北封禁
                            && studentDTO.getRoleEnum()
                            .compareTo( RoleEnum.teacher) == 0){ // 学生是否是老师
                        return true;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据课程id获取课程信息
     * @param id
     * @return
     * @throws Exception
     */
    public CourseTeacherDTO getCourseTeacherDTOById(Long id) throws Exception{
        if(id == null){
            throw new NullPointerException("id is null");
        }else{
            CourseTeacherDTO courseTeacherDTO = courseTeacherCAO.getCourseTeacherDTOById(id);
            if(courseTeacherDTO != null){
                System.out.println("缓存有！");
               return courseTeacherDTO;
            }else{
                return getCourseTeacherDAO.get(CourseTeacherDTO.class,id);
            }
        }
    }

    public CourseTeacherDTO getCourseTeacherDTOById(String courseId) throws Exception {
        Long id = Long.parseLong(courseId);
        return getCourseTeacherDTOById(id);
    }

    /**
     * 修改缓存的课程信息
     * @param courseTeacherDTO
     * @return
     * @throws Exception
     */
    public void updateCourseTeacherCache(CourseTeacherDTO courseTeacherDTO) throws Exception {
        if(courseTeacherDTO == null){
            throw new NullPointerException("courseTeacherDTO is null");
        }else{
            Node node = courseTeacherCAO.getCourseTeacherNode(courseTeacherDTO);
            if(node != null) {
                CourseTeacherDTO oldCourseTeacherDTO = (CourseTeacherDTO) node.getData();
                //判断是否修改了类别
                if(oldCourseTeacherDTO.getTechnicTagEnum() == courseTeacherDTO.getTechnicTagEnum()){
                    System.out.println("没有修改技术类别");
                    node.setData(courseTeacherDTO);
                }else{
                    // 1. 删除当前类别的当前节点
                    if(node.getNext()==null){
                        node.getPrev().setNext(null);

                    }else if(node.getPrev() == null){
                        courseTeacherCAO.deleteCourseTeacherNode(courseTeacherDTO);
                    }
                }

            }

        }
    }

    /**
     * 更新数据库的内容
     * @param courseTeacherDTO
     * @throws Exception
     */
    public void updateCourseTeacherDTO(CourseTeacherDTO courseTeacherDTO) throws Exception{
        if(courseTeacherDTO == null){
            throw new NullPointerException("courseTeacherDTO is null");
        }else{
            updateCourseTeacherDAO.update(courseTeacherDTO);
        }
    }



}
