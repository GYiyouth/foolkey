package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 课程订单的DTO
 * Created by ustcg on 2017/5/9.
 */
@Component("orderBuyCourseSTCDTO")
public class OrderBuyCourseAsTeacherSTCDTO {

    //课程信息
    private CourseDTO courseDTO;

    //订单-学生信息
    private List<OrderBuyCourseWithStudentAsTeacherSTCDTO> orderBuyCourseWithStudentAsTeacherSTCDTOS;

    @Override
    public String toString() {
        return "OrderBuyCourseAsTeacherSTCDTO{" +
                "courseDTO=" + courseDTO +
                ", orderBuyCourseWithStudentAsTeacherSTCDTOS=" + orderBuyCourseWithStudentAsTeacherSTCDTOS +
                '}';
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public List<OrderBuyCourseWithStudentAsTeacherSTCDTO> getOrderBuyCourseWithStudentAsTeacherSTCDTOS() {
        return orderBuyCourseWithStudentAsTeacherSTCDTOS;
    }

    public void setOrderBuyCourseWithStudentAsTeacherSTCDTOS(List<OrderBuyCourseWithStudentAsTeacherSTCDTO> orderBuyCourseWithStudentAsTeacherSTCDTOS) {
        this.orderBuyCourseWithStudentAsTeacherSTCDTOS = orderBuyCourseWithStudentAsTeacherSTCDTOS;
    }
}
