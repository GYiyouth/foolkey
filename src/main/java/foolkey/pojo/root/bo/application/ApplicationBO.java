package foolkey.pojo.root.bo.application;

import foolkey.pojo.root.DAO.application_teacher_course.SaveApplicationTeacherCourseDAO;
import foolkey.pojo.root.vo.dto.ApplicationTeacherCourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class ApplicationBO {
    @Autowired
    private SaveApplicationTeacherCourseDAO dao;

    /**
     * 生成对老师课程的申请消息
     * @return
     */
    public ApplicationTeacherCourseDTO saveApplicationForTeacherCourse(
            Long applicantId,
            Long courseId
    ){
        ApplicationTeacherCourseDTO application = new ApplicationTeacherCourseDTO();
        application.setApplicantId(applicantId);
        application.setCourseTeacherId(courseId);
        dao.save(application);
        return application;
    }
}
