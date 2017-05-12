package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;

/**
 * 学生端获取课程订单
 * Created by GR on 2017/5/12.
 */
public class OrderBuyCourseAsStudentSTCDTO {

    //课程信息
    private CourseDTO courseDTO;

    //订单信息
    private OrderBuyCourseDTO orderBuyCourseDTO;

    //老师信息
    private TeacherAllInfoDTO teacherAllInfoDTO;

    @Override
    public String toString() {
        return "OrderBuyCourseAsStudentSTCDTO{" +
                "courseDTO=" + courseDTO +
                ", orderBuyCourseDTO=" + orderBuyCourseDTO +
                ", teacherAllInfoDTO=" + teacherAllInfoDTO +
                '}';
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
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
