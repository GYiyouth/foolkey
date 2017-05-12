package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Component;

/**
 * 课程订单的DTO
 * Created by ustcg on 2017/5/9.
 */
@Component("orderBuyCourseSTCDTO")
public class OrderBuyCourseSTCDTO {

    //课程信息
    private CourseDTO courseTeacherDTO;

    //老师信息
    private TeacherAllInfoDTO teacherAllInfoDTO;

    //订单信息
    private OrderBuyCourseDTO orderBuyCourseDTO;

    //学生信息
    private StudentDTO studentDTO;

    @Override
    public String toString() {
        return "OrderBuyCourseSTCDTO{" +
                "courseTeacherDTO=" + courseTeacherDTO +
                ", teacherAllInfoDTO=" + teacherAllInfoDTO +
                ", orderBuyCourseDTO=" + orderBuyCourseDTO +
                ", studentDTO=" + studentDTO +
                '}';
    }

    public CourseDTO getCourseTeacherDTO() {
        return courseTeacherDTO;
    }

    public void setCourseTeacherDTO(CourseDTO courseTeacherDTO) {
        this.courseTeacherDTO = courseTeacherDTO;
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

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }
}
