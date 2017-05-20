package foolkey.pojo.send_to_client.reward;

import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;

/**
 * 悬赏-学生 信息DTO
 * Created by GR on 2017/5/20.
 */
public class RewardWithStudentSTCDTO {

    //悬赏信息
    private RewardDTO rewardDTO;

    //学生（悬赏发布者）信息
    private StudentDTO studentDTO;

    @Override
    public String toString() {
        return "RewardWithStudentSTCDTO{" +
                "rewardDTO=" + rewardDTO +
                ", studentDTO=" + studentDTO +
                '}';
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
}
