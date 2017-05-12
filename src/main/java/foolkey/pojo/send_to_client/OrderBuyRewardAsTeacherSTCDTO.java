package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 悬赏订单的DTO
 * Created by ustcg on 2017/5/9.
 */
@Component("orderBuyRewardSTCDTO")
public class OrderBuyRewardAsTeacherSTCDTO {

    //悬赏信息
    private RewardDTO rewardDTO;

    //订单-学生信息
    private List<OrderBuyCourseWithStudentAsTeacherSTCDTO> orderBuyCourseWithStudentAsTeacherSTCDTOS;

    @Override
    public String toString() {
        return "OrderBuyRewardAsTeacherSTCDTO{" +
                "rewardDTO=" + rewardDTO +
                ", orderBuyCourseWithStudentAsTeacherSTCDTOS=" + orderBuyCourseWithStudentAsTeacherSTCDTOS +
                '}';
    }

    public RewardDTO getRewardDTO() {
        return rewardDTO;
    }

    public void setRewardDTO(RewardDTO rewardDTO) {
        this.rewardDTO = rewardDTO;
    }

    public List<OrderBuyCourseWithStudentAsTeacherSTCDTO> getOrderBuyCourseWithStudentAsTeacherSTCDTOS() {
        return orderBuyCourseWithStudentAsTeacherSTCDTOS;
    }

    public void setOrderBuyCourseWithStudentAsTeacherSTCDTOS(List<OrderBuyCourseWithStudentAsTeacherSTCDTO> orderBuyCourseWithStudentAsTeacherSTCDTOS) {
        this.orderBuyCourseWithStudentAsTeacherSTCDTOS = orderBuyCourseWithStudentAsTeacherSTCDTOS;
    }
}
