package foolkey.pojo.root.bo.application;

import foolkey.pojo.root.DAO.application_student_reward.SaveApplicationStudentRewardDAO;
import foolkey.pojo.root.DAO.application_teacher_course.SaveApplicationTeacherCourseDAO;
import foolkey.pojo.root.vo.assistObject.ApplicationStateEnum;
import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import foolkey.pojo.root.vo.dto.ApplicationTeacherCourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class ApplicationInfoBO {
    @Autowired
    private SaveApplicationTeacherCourseDAO saveApplicationTeacherCourseDAO;
    @Autowired
    private SaveApplicationStudentRewardDAO saveApplicationStudentRewardDAO;

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
            Long orderId,
            Long messageId,
            Long studentId
    ){
        ApplicationStudentRewardDTO application = new ApplicationStudentRewardDTO();
        application.setApplicantId(applicantId);
        application.setOrderId(orderId);
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
}
