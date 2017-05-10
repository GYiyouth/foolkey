package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ustcg on 2017/5/9.
 */
@Component("applicationStudentRewardAsStudentSTCDTO")
public class ApplicationStudentRewardAsStudentSTCDTO {

    //悬赏DTO
    private RewardDTO rewardDTO;
    //老师申请的DTOS
    private List<ApplicationRewardWithTeacherSTCDTO> applicationRewardWithTeacherSTCDTOS;

    @Override
    public String toString() {
        return "ApplicationStudentRewardAsStuentSTCDTO{" +
                "rewardDTO=" + rewardDTO +
                ", applicationRewardWithTeacherSTCDTOS=" + applicationRewardWithTeacherSTCDTOS +
                '}';
    }

    public RewardDTO getRewardDTO() {
        return rewardDTO;
    }

    public void setRewardDTO(RewardDTO rewardDTO) {
        this.rewardDTO = rewardDTO;
    }

    public List<ApplicationRewardWithTeacherSTCDTO> getApplicationRewardWithTeacherSTCDTOS() {
        return applicationRewardWithTeacherSTCDTOS;
    }

    public void setApplicationRewardWithTeacherSTCDTOS(List<ApplicationRewardWithTeacherSTCDTO> applicationRewardWithTeacherSTCDTOS) {
        this.applicationRewardWithTeacherSTCDTOS = applicationRewardWithTeacherSTCDTOS;
    }
}
