package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import org.springframework.stereotype.Component;

/**
 * Created by ustcg on 2017/5/10.
 */
@Component
public class ApplicationRewardWithTeacherSTCDTO {

    //老师（申请者）信息
    private TeacherAllInfoDTO teacherAllInfoDTO;
    //申请信息
    private ApplicationStudentRewardDTO applicationStudentRewardDTO;

    @Override
    public String toString() {
        return "ApplicationRewardWithTeacherSTCDTO{" +
                "teacherAllInfoDTO=" + teacherAllInfoDTO +
                ", applicationStudentRewardDTO=" + applicationStudentRewardDTO +
                '}';
    }

    public TeacherAllInfoDTO getTeacherAllInfoDTO() {
        return teacherAllInfoDTO;
    }

    public void setTeacherAllInfoDTO(TeacherAllInfoDTO teacherAllInfoDTO) {
        this.teacherAllInfoDTO = teacherAllInfoDTO;
    }

    public ApplicationStudentRewardDTO getApplicationStudentRewardDTO() {
        return applicationStudentRewardDTO;
    }

    public void setApplicationStudentRewardDTO(ApplicationStudentRewardDTO applicationStudentRewardDTO) {
        this.applicationStudentRewardDTO = applicationStudentRewardDTO;
    }
}
