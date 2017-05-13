package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;

/**
 * Created by GR on 2017/5/12.
 */
public class OrderBuyCourseWithStudentAsStudentSTCDTO {

    //学生信息
    private TeacherAllInfoDTO teacherAllInfoDTO;
    //订单信息
    private OrderBuyCourseDTO orderBuyCourseDTO;

    @Override
    public String toString() {
        return "OrderBuyCourseWithStudentAsStudentSTCDTO{" +
                "teacherAllInfoDTO=" + teacherAllInfoDTO +
                ", orderBuyCourseDTO=" + orderBuyCourseDTO +
                '}';
    }

    public TeacherAllInfoDTO getTeacherAllInfoDTO() {
        return teacherAllInfoDTO;
    }

    public void setTeacherAllInfoDTO(TeacherAllInfoDTO teacherAllInfoDTO) {
        this.teacherAllInfoDTO = teacherAllInfoDTO;
    }

    public OrderBuyCourseDTO getOrderBuyCourseDTO() {
        return orderBuyCourseDTO;
    }

    public void setOrderBuyCourseDTO(OrderBuyCourseDTO orderBuyCourseDTO) {
        this.orderBuyCourseDTO = orderBuyCourseDTO;
    }
}
