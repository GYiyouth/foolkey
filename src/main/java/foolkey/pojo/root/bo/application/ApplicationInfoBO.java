package foolkey.pojo.root.bo.application;

import foolkey.pojo.root.DAO.application_student_reward.DeleteApplicationStudentRewardDAO;
import foolkey.pojo.root.DAO.application_student_reward.GetApplicationStudentRewardDAO;
import foolkey.pojo.root.DAO.application_student_reward.SaveApplicationStudentRewardDAO;
import foolkey.pojo.root.DAO.application_student_reward.UpdateApplicationStudentRewardDAO;
import foolkey.pojo.root.DAO.application_teacher_course.DeleteApplicationTeacherCourseDAO;
import foolkey.pojo.root.DAO.application_teacher_course.GetApplicationTeacherCourseDAO;
import foolkey.pojo.root.DAO.application_teacher_course.SaveApplicationTeacherCourseDAO;
import foolkey.pojo.root.DAO.application_teacher_course.UpdateApplicationTeacherCourseDAO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.pojo.root.vo.assistObject.ApplicationStateEnum;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class ApplicationInfoBO {
    @Autowired
    private GetApplicationTeacherCourseDAO getApplicationTeacherCourseDAO;
    @Autowired
    private GetApplicationStudentRewardDAO getApplicationStudentRewardDAO;
    @Autowired
    private SaveApplicationTeacherCourseDAO saveApplicationTeacherCourseDAO;
    @Autowired
    private SaveApplicationStudentRewardDAO saveApplicationStudentRewardDAO;
    @Autowired
    private DeleteApplicationStudentRewardDAO deleteApplicationStudentRewardDAO;
    @Autowired
    private DeleteApplicationTeacherCourseDAO deleteApplicationTeacherCourseDAO;
    @Autowired
    private UpdateApplicationStudentRewardDAO updateApplicationStudentRewardDAO;
    @Autowired
    private UpdateApplicationTeacherCourseDAO updateApplicationTeacherCourseDAO;
    @Autowired
    private RewardBO courseStudentBO;
    @Autowired
    private CourseBO courseTeacherBO;
    @Autowired
    private OrderInfoBO orderInfoBO;

    /**
     * 生成对老师课程的申请消息，并存储
     * @return
     */
    public ApplicationTeacherCourseDTO createApplicationForTeacherCourse(
            Long applicantId,
            Long orderId,
            Long messageId,
            Long teacherId
    ){
        ApplicationTeacherCourseDTO application = new ApplicationTeacherCourseDTO();
        application.setApplicantId(applicantId);
        application.setOrderId(orderId);
        application.setMessageId(messageId);
        application.setTeacherId(teacherId);
        application.setApplyTime(new Date());
        application.setState( ApplicationStateEnum.processing);
        return application;
    }

    public ApplicationStudentRewardDTO createApplicationForStudentReward(
            Long applicantId,
            Long courseId,
            Long messageId,
            Long studentId
    ){
        ApplicationStudentRewardDTO application = new ApplicationStudentRewardDTO();
        application.setApplicantId(applicantId);
        application.setCourseId(courseId);
        application.setMessageId(messageId);
        application.setStudentId(studentId);
        application.setApplyTime(new Date());
        application.setState( ApplicationStateEnum.processing);
        return application;
    }



    /**
     * 存储 到数据库
     * @param application
     * @return
     */
    public ApplicationTeacherCourseDTO save(ApplicationTeacherCourseDTO application){
                saveApplicationTeacherCourseDAO.save(application);
                return application;
    }

    public ApplicationStudentRewardDTO save(ApplicationStudentRewardDTO applicaiton){
        saveApplicationStudentRewardDAO.save(applicaiton);
        return applicaiton;
    }

    /**
     * 删除对学生悬赏的申请
     * @param studentId
     * @param teacherId
     */
    public void deleteRewardApplication(Long studentId, Long teacherId){
        deleteApplicationStudentRewardDAO.delete(teacherId, studentId);
    }

    /**
     * 删除对老师课程的申请
     * @param applicantId
     * @param orderId
     */
    public void deleteTeacherCourseApplication(Long applicantId, Long orderId){
        deleteApplicationTeacherCourseDAO.delete(applicantId, orderId);
    }

    /**
     * 根据orderId删除课程申请
     * @param orderId
     * @param courseType
     */
    public void deleteAllApplicationByOrderId(Long orderId, CourseTypeEnum courseType) throws Exception{
        OrderBuyCourseDTO orderDTO = orderInfoBO.getCourseOrder(orderId + "");
        if (courseType.compareTo(CourseTypeEnum.学生悬赏) == 0) {
            RewardDTO courseStudentDTO = courseStudentBO.getCourseStudentDTO(orderDTO.getCourseId());
            deleteApplicationStudentRewardDAO.deleteAllByCourseId(courseStudentDTO.getId());
        }
        if (courseType.compareTo(CourseTypeEnum.老师课程) == 0) {
            CourseDTO courseTeacherDTO = courseTeacherBO.getCourseTeacherDTOById(orderDTO.getCourseId());
            deleteApplicationTeacherCourseDAO.deleteAllByCourseId(courseTeacherDTO.getId());
        }
    }

    /**
     * 根据id获取申请DTO，学生课程的申请
     * @param applicationId
     * @return
     */
    public ApplicationStudentRewardDTO getRewardApplicationDTO(Long applicationId) {
        return getRewardApplicationDTO(applicationId);
    }

    /**
     * 获取 学生悬赏 的申请
     * @param teacherId 教师id
     * @param studentId 学生id
     * @param courseId 悬赏id
     * @return
     */
    public List<ApplicationStudentRewardDTO> getRewardApplicationDTo(Long teacherId, Long studentId, Long courseId){
        List<ApplicationStudentRewardDTO> list =
                getApplicationStudentRewardDAO.findByPage(
                        "from ApplicationStudentRewardDTO asr where asr.applicantId = ? " +
                                " and asr.studentId = ? and asr.courseId = ?", 1, 20
                        , teacherId, studentId, courseId
                );
        return list;
    }

    /**
     * 获取 老师课程 的申请
     * @param teacherId 教师id
     * @param studentId 学生id
     * @param orderId 订单id
     * @return
     */
    public List<ApplicationTeacherCourseDTO> getTeacherCourseApplicationDTO(Long teacherId, Long studentId, Long orderId){
        List<ApplicationTeacherCourseDTO> list =
                getApplicationTeacherCourseDAO.findByPage(
                        "from ApplicationTeacherCourseDTO atr where atr.applicantId = ? " +
                                " and atr.teacherId = ? and atr.orderId = ?", 1, 20
                        , studentId, teacherId, orderId
                );
        return list;
    }

    /**
     * 更新
     * @param applicationStudentRewardDTO
     * @return
     */
    public ApplicationStudentRewardDTO update(ApplicationStudentRewardDTO applicationStudentRewardDTO){
        updateApplicationStudentRewardDAO.update(applicationStudentRewardDTO);
        return applicationStudentRewardDTO;
    }

    /**
     * 更新
     * @param applicationTeacherCourseDTO
     * @return
     */
    public ApplicationTeacherCourseDTO update(ApplicationTeacherCourseDTO applicationTeacherCourseDTO){
        updateApplicationTeacherCourseDAO.update(applicationTeacherCourseDTO);
        return applicationTeacherCourseDTO;
    }
}
