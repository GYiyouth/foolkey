package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Component;

/**
 * Created by ustcg on 2017/5/9.
 */
@Component("applicationStudentRewardSTCDTO")
public class ApplicationStudentRewardSTCDTO {

    //申请悬赏DTO
    private ApplicationStudentRewardDTO applicationStudentRewardDTO;
    //悬赏DTO
    private RewardDTO rewardDTO;
    //学生（悬赏创建者）DTO
    private StudentDTO studentDTO;
    //老师（悬赏申请者）DTO
    private TeacherAllInfoDTO teacherAllInfoDTO;

    @Override
    public String toString() {
        return "ApplicationStudentRewardSTCDTO{" +
                "applicationStudentRewardDTO=" + applicationStudentRewardDTO +
                ", rewardDTO=" + rewardDTO +
                ", studentDTO=" + studentDTO +
                ", teacherAllInfoDTO=" + teacherAllInfoDTO +
                '}';
    }

    public ApplicationStudentRewardDTO getApplicationStudentRewardDTO() {
        return applicationStudentRewardDTO;
    }

    public void setApplicationStudentRewardDTO(ApplicationStudentRewardDTO applicationStudentRewardDTO) {
        this.applicationStudentRewardDTO = applicationStudentRewardDTO;
    }

    public RewardDTO getRewardDTO() {
        return rewardDTO;
    }

    public void setRewardDTO(RewardDTO rewardDTO) {
        this.rewardDTO = rewardDTO;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public TeacherAllInfoDTO getTeacherAllInfoDTO() {
        return teacherAllInfoDTO;
    }

    public void setTeacherAllInfoDTO(TeacherAllInfoDTO teacherAllInfoDTO) {
        this.teacherAllInfoDTO = teacherAllInfoDTO;
    }
}
