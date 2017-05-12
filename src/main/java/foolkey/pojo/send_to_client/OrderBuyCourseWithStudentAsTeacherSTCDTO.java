package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;

/**
 * 某个课程下，学生-订单信息DTO
 * Created by GR on 2017/5/12.
 */
public class OrderBuyCourseWithStudentAsTeacherSTCDTO {

    //学生信息
    private StudentDTO studentDTO;
    //订单信息
    private OrderBuyCourseDTO orderBuyCourseDTO;

    @Override
    public String toString() {
        return "OrderBuyCourseWithStudentAsTeacherSTCDTO{" +
                "studentDTO=" + studentDTO +
                ", orderBuyCourseDTO=" + orderBuyCourseDTO +
                '}';
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public OrderBuyCourseDTO getOrderBuyCourseDTO() {
        return orderBuyCourseDTO;
    }

    public void setOrderBuyCourseDTO(OrderBuyCourseDTO orderBuyCourseDTO) {
        this.orderBuyCourseDTO = orderBuyCourseDTO;
    }
}
