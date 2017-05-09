package foolkey.pojo.root.bo.CourseTeacher;

import foolkey.pojo.root.CAO.CourseTeacher.CourseTeacherCAO;
import foolkey.pojo.root.DAO.course_teacher.GetCourseTeacherDAO;
import foolkey.pojo.root.DAO.course_teacher.SaveCourseTeacherDAO;
import foolkey.pojo.root.DAO.course_teacher.UpdateCourseTeacherDAO;
import foolkey.pojo.root.DataStructure.Node;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.*;
import foolkey.pojo.root.vo.cacheDTO.CourseTeacherPopularDTO;
import foolkey.pojo.root.vo.cacheDTO.TeacherAllInfoDTO;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import foolkey.tool.BeanFactory;
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
     * 添加热门课程到缓存
     * @param technicTagEnum
     * @param size
     */
    public void fillCourseTeacherPopularDTOToCache(TechnicTagEnum technicTagEnum, Integer size){
        if(technicTagEnum == null || size == null || size == 0){
            throw new NullPointerException("technicTagEnum/size is null");
        }else {
            try {
                ArrayList<CourseTeacherDTO> courseTeacherDTOS = getCourseTeacherDAO.findByTechnicTagEnumAndResultSize(technicTagEnum, size);
                ArrayList<CourseTeacherPopularDTO> courseTeacherPopularDTOS = new ArrayList<>();
                for (CourseTeacherDTO courseTeacherDTO : courseTeacherDTOS) {
                    TeacherAllInfoDTO teacherAllInfoDTO = teacherInfoBO.getTeacherAllInfoDTO(courseTeacherDTO.getCreatorId());
                    CourseTeacherPopularDTO courseTeacherPopularDTO = BeanFactory.getBean("courseTeacherPopularDTO", CourseTeacherPopularDTO.class);
                    courseTeacherPopularDTO.setCourseTeacherDTO(courseTeacherDTO);
                    courseTeacherPopularDTO.setTeacherAllInfoDTO(teacherAllInfoDTO);
                    courseTeacherPopularDTOS.add(courseTeacherPopularDTO);
                }
                if(courseTeacherDTOS.size()!=0) {
                    courseTeacherCAO.addCourseTeacherPopularDTOSToCache(technicTagEnum, courseTeacherPopularDTOS, DirectionEnum.tail);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取某个标签下流行的课程
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ArrayList<CourseTeacherPopularDTO> getCourseTeacherPopularDTO(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize) throws Exception{
        //请求的内容超过内存大小
        //暂时不允许超过内存大小
        if((pageNo-1)*pageSize >= StaticVariable.cacheSize) {
//            return  getCourseTeacherDAO.findCourseTeacherByPageMoreCache(technicTagEnum,pageNo,pageSize);
            return new ArrayList<>();
        }else{
            if (courseTeacherCAO.containsCourseTeacher(technicTagEnum, pageNo, pageSize)) {
                System.out.println("缓存有！");
                return courseTeacherCAO.getCourseTeacherPopularDTO(technicTagEnum, pageNo, pageSize);
            }else {
                //缓存没有则数据库彻底没了
                System.out.println("缓存没有");
                return new ArrayList<>();
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
            CourseTeacherPopularDTO courseTeacherPopularDTO = courseTeacherCAO.getCourseTeacherDTOById(id);
            if(courseTeacherPopularDTO != null && courseTeacherPopularDTO.getCourseTeacherDTO() != null){
                System.out.println("缓存有！");
               return courseTeacherPopularDTO.getCourseTeacherDTO();
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
     * 根据课程id获取到课程-老师DTO
     * @param courseId
     * @return
     * @throws Exception
     */
    public CourseTeacherPopularDTO getCourseTeacherPopularDTOByCourseId(Long courseId) throws Exception {
        // 首先根据课程id获取课程DTO
        CourseTeacherDTO courseTeacherDTO = getCourseTeacherDTOById(courseId);
        // 获取所属老师的信息
        TeacherAllInfoDTO teacherAllInfoDTO = teacherInfoBO.getTeacherAllInfoDTO(courseTeacherDTO.getCreatorId());
        // 创建课程-老师DTO，并赋值
        CourseTeacherPopularDTO courseTeacherPopularDTO = new CourseTeacherPopularDTO();
        courseTeacherPopularDTO.setCourseTeacherDTO(courseTeacherDTO);
        courseTeacherPopularDTO.setTeacherAllInfoDTO(teacherAllInfoDTO);
        return courseTeacherPopularDTO;
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
            //获取老师的所有信息
            TeacherAllInfoDTO teacherAllInfoDTO = BeanFactory.getBean("teacherAllInfoDTO",TeacherAllInfoDTO.class);

            //生成一个热门课程的DTO
            CourseTeacherPopularDTO courseTeacherPopularDTO = BeanFactory.getBean("courseTeacherPopularDTO",CourseTeacherPopularDTO.class);
            courseTeacherPopularDTO.setCourseTeacherDTO(courseTeacherDTO);
            courseTeacherPopularDTO.setTeacherAllInfoDTO(teacherAllInfoDTO);


            //获取要修改的课程所在的节点
            Node node = courseTeacherCAO.getCourseTeacherPopularNodeByCourseTeacherDTOId(courseTeacherDTO.getId());
            if(node != null) {
                CourseTeacherPopularDTO oldCourseTeacherPopularDTO = (CourseTeacherPopularDTO) node.getData();
                //判断是否修改了类别
                if(oldCourseTeacherPopularDTO.getCourseTeacherDTO().getTechnicTagEnum() == courseTeacherDTO.getTechnicTagEnum()){
                    System.out.println("没有修改技术类别---");
                    node.setData(courseTeacherPopularDTO);
                    System.out.println("秀秀秀");
                }else{
                    // 1. 删除当前类别的当前节点
                    courseTeacherCAO.deleteCourseTeacherNode(courseTeacherPopularDTO);
                    courseTeacherCAO.addCourseTeacherPopularDTOToCache(courseTeacherPopularDTO,courseTeacherDTO.getTechnicTagEnum(),DirectionEnum.head);
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

    /**
     * 把一门课程从缓存的缓冲区添加到缓存的热门去
     * @param courseTeacherPopularDTO
     */
//    public void addCourseTeacherFromReserveToCache(CourseTeacherPopularDTO courseTeacherPopularDTO){
//        if(courseTeacherPopularDTO == null){
//            throw new NullPointerException("courseTeacherPopularDTO id null");
//        }else{
//            //1. 获取热门缓冲区的节点  删除
//            Node delNode = courseTeacherCAO.getCourseTeacherNodeInReserve(courseTeacherDTO);
//            courseTeacherCAO.deleteCourseTeacherNodeInReserve(courseTeacherDTO);
//            //2.添加节点到热门区
//            courseTeacherCAO.addCourseTeacherToCache(courseTeacherDTO);
//        }
//    }


    /**
     * 展示所有的热门课程
     * @param technicTagEnum
     */
    public ArrayList<CourseTeacherPopularDTO> getAllCourseTeacherPopularDTO(TechnicTagEnum technicTagEnum){
        if(technicTagEnum == null){
            throw new NullPointerException("technicTagEnum is null");
        }else{
            return courseTeacherCAO.getAllCourseTeacherPopular(technicTagEnum);
        }
    }

}
