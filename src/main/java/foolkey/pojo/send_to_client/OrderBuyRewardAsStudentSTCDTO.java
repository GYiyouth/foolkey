package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;

/**
 * Created by GR on 2017/5/14.
 */
public class OrderBuyRewardAsStudentSTCDTO {

    //悬赏信息
    private RewardDTO rewardDTO;

    //订单信息
    private OrderBuyCourseDTO orderBuyCourseDTO;

    //老师信息
    private TeacherAllInfoDTO teacherAllInfoDTO;

    @Override
    public String toString() {
        return "OrderBuyRewardAsStudentSTCDTO{" +
                "rewardDTO=" + rewardDTO +
                ", orderBuyCourseDTO=" + orderBuyCourseDTO +
                ", teacherAllInfoDTO=" + teacherAllInfoDTO +
                '}';
    }

    public RewardDTO getRewardDTO() {
        return rewardDTO;
    }

    public void setRewardDTO(RewardDTO rewardDTO) {
        this.rewardDTO = rewardDTO;
    }

    public OrderBuyCourseDTO getOrderBuyCourseDTO() {
        return orderBuyCourseDTO;
    }

    public void setOrderBuyCourseDTO(OrderBuyCourseDTO orderBuyCourseDTO) {
        this.orderBuyCourseDTO = orderBuyCourseDTO;
    }

    public TeacherAllInfoDTO getTeacherAllInfoDTO() {
        return teacherAllInfoDTO;
    }

    public void setTeacherAllInfoDTO(TeacherAllInfoDTO teacherAllInfoDTO) {
        this.teacherAllInfoDTO = teacherAllInfoDTO;
    }
}
