package foolkey.pojo.root.bo.application;

import foolkey.pojo.root.DAO.application_teacher_course.SaveApplicationTeacherCourseDAO;
import foolkey.pojo.root.vo.assistObject.ApplicationStateEnum;
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
    private SaveApplicationTeacherCourseDAO dao;

    /**
     * 生成对老师课程的申请消息
     * @return
     */
    public ApplicationTeacherCourseDTO saveApplicationForTeacherCourse(
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
        dao.save(application);
        return application;
    }
}
