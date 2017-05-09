package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Component;

/**
 * Created by ustcg on 2017/5/9.
 */
@Component("orderBuyRewardSTCDTO")
public class OrderBuyRewardSTCDTO {

    //悬赏
    private RewardDTO courseStudentDTO;

    //订单信息
    private OrderBuyCourseDTO orderBuyCourseDTO;

    //老师信息
    private TeacherAllInfoDTO teacherAllInfoDTO;

    //学生的信息
    private StudentDTO studentDTO;

    @Override
    public String toString() {
        return "OrderBuyRewardSTCDTO{" +
                "courseStudentDTO=" + courseStudentDTO +
                ", orderBuyCourseDTO=" + orderBuyCourseDTO +
                ", teacherAllInfoDTO=" + teacherAllInfoDTO +
                ", studentDTO=" + studentDTO +
                '}';
    }

    public RewardDTO getCourseStudentDTO() {
        return courseStudentDTO;
    }

    public void setCourseStudentDTO(RewardDTO courseStudentDTO) {
        this.courseStudentDTO = courseStudentDTO;
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

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }
}
