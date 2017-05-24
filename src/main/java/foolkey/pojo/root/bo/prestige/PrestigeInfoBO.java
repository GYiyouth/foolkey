package foolkey.pojo.root.bo.prestige;

import foolkey.pojo.root.CAO.userInfo.UserCAO;
import foolkey.pojo.root.bo.order.OrderInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.dto.*;
import foolkey.tool.StaticVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 处理用户的声望
 * Created by GR on 2017/5/24.
 */
@Service
public class PrestigeInfoBO {

    @Autowired
    private UserCAO userCAO;
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private OrderInfoBO orderInfoBO;


    /**
     * 完成个人资料的填写之后，增加声望
     */
    public void completePersonalProfile(StudentDTO studentDTO) throws Exception {
        //缓存获取到用户是否是第一次完成个人资料
        if (!userCAO.isCompletePersonalProfile(studentDTO.getId())) {
            //之前没有完成
            //所有需要填写的属性数
            int allFieldCounts = 0;
            //已经填写了的属性数目
            int filledFieldCounts = 0;
            Field[] fields = studentDTO.getClass().getDeclaredFields();
            allFieldCounts = fields.length;
            for (Field field : fields) {
                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), studentDTO.getClass());
                Method getMethod = descriptor.getReadMethod();
                if (getMethod.invoke(studentDTO) != null) {
                    filledFieldCounts++;
                }
            }
            //如果填写比例已经超过了标准值，则证明已经完成个人资料的填写
            if (((double) filledFieldCounts / allFieldCounts) >= StaticVariable.PROFILE_FILLED_RATE) {
                userCAO.completePersonalProfile(studentDTO.getId());
                studentDTO.setPrestige(studentDTO.getPrestige() + StaticVariable.COMPLETE_PROFILE_RAISE_PRESTIGE);
                //更新个人资料
                studentInfoBO.updateStudent(studentDTO);
            }
        }
    }


    /**
     * 评论课程之后，学生、老师增加声望
     * <p>
     * [0,1] 不加
     * (1,3) 加1/2声望
     * [3,5) 加等额声望
     * 5 加双倍声望
     *
     * @param evaluationCourseDTO
     * @throws Exception
     */
    public void completeDealCourse(EvaluationCourseDTO evaluationCourseDTO) throws Exception {

        OrderBuyCourseDTO orderBuyCourseDTO = orderInfoBO.getOrderBuyCourseDTOByOrderId(evaluationCourseDTO.getOrderId());
        //学生信息
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(orderBuyCourseDTO.getUserId());
        //老师信息
        StudentDTO teacherDTO = studentInfoBO.getStudentDTO(orderBuyCourseDTO.getTeacherId());
        //交易产生的声望值（默认=交易额）
        Integer prestigeValue = Integer.valueOf(orderBuyCourseDTO.getAmount() + "");

        //学生就直接增加等额声望
        studentDTO.setPrestige(studentDTO.getPrestige() + prestigeValue);
        //老师要根据课程评分
        double score = evaluationCourseDTO.getScore();
        if (score == 5) {
            //双倍声望
            teacherDTO.setPrestige(teacherDTO.getPrestige() + prestigeValue * 2);
        } else if (score >= 3 && score < 5) {
            //等价声望
            teacherDTO.setPrestige(teacherDTO.getPrestige() + prestigeValue);
        } else if (score > 1 && score < 3) {
            //加一半声望
            teacherDTO.setPrestige(teacherDTO.getPrestige() + prestigeValue / 2);
        } else {

        }

        teacherDTO.setPrestige(teacherDTO.getPrestige() + prestigeValue);

        studentInfoBO.updateStudent(studentDTO);
        studentInfoBO.updateStudent(teacherDTO);
    }


    /**
     * 完成评价学生之后的声望管理
     * [0,1] -10
     * (1,3) 0
     * [3,5) +10
     * 5 +20
     *
     * @param evaluationStudentDTO
     */
    public void completeEvaluateStudent(EvaluationStudentDTO evaluationStudentDTO) {
        //被评价学生的DTO
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(evaluationStudentDTO.getAcceptor_id());
        Double score = evaluationStudentDTO.getScore();
        if (score == 5) {
            studentDTO.setPrestige(studentDTO.getPrestige() + StaticVariable.PRESTIGE_RAISE_SCORE_EQUAL_5);
        } else if (score >= 3 && score < 5) {
            studentDTO.setPrestige(studentDTO.getPrestige() + StaticVariable.PRESTIGE_RAISE_SCORE_BETWEEN_3_5);
        } else if (score > 1 && score < 3) {
            studentDTO.setPrestige(studentDTO.getPrestige() + StaticVariable.PRESTIGE_RAISE_SCORE_BETWEEN_1_3);
        } else {
            studentDTO.setPrestige(studentDTO.getPrestige() + StaticVariable.PRESTIGE_RAISE_SCORE_LESS_1);
        }
        studentInfoBO.updateStudent(studentDTO);
    }


    /**
     * 完成评价老师之后的声望管理
     * [0,1] -10
     * (1,3) 0
     * [3,5) +10
     * 5 +20
     *
     * @param evaluationTeacherDTO
     */
    public void completeEvaluateTeacher(EvaluationTeacherDTO evaluationTeacherDTO) {
        //被评价学生的DTO
        StudentDTO teacherDTO = studentInfoBO.getStudentDTO(evaluationTeacherDTO.getAcceptor_id());
        Double score = evaluationTeacherDTO.getScore();
        if (score == 5) {
            teacherDTO.setPrestige(teacherDTO.getPrestige() + StaticVariable.PRESTIGE_RAISE_SCORE_EQUAL_5);
        } else if (score >= 3 && score < 5) {
            teacherDTO.setPrestige(teacherDTO.getPrestige() + StaticVariable.PRESTIGE_RAISE_SCORE_BETWEEN_3_5);
        } else if (score > 1 && score < 3) {
            teacherDTO.setPrestige(teacherDTO.getPrestige() + StaticVariable.PRESTIGE_RAISE_SCORE_BETWEEN_1_3);
        } else {
            teacherDTO.setPrestige(teacherDTO.getPrestige() + StaticVariable.PRESTIGE_RAISE_SCORE_LESS_1);
        }
        studentInfoBO.updateStudent(teacherDTO);
    }

//    EvaluationCourseDTO
}
