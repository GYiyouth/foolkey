package foolkey.aop.cacheMaintain;

import foolkey.pojo.root.bo.prestige.PrestigeInfoBO;
import foolkey.pojo.root.vo.dto.EvaluationCourseDTO;
import foolkey.pojo.root.vo.dto.EvaluationStudentDTO;
import foolkey.pojo.root.vo.dto.EvaluationTeacherDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 声望操作的AOP
 * Created by GR on 2017/5/24.
 */
@Aspect
public class PrestigeAOP {

    @Autowired
    private PrestigeInfoBO prestigeInfoBO;

    /**
     * 更新学生信息之后，对声望更新
     * 如果填写资料比较齐全，超过一定比例，则奖励声望
     *
     * @param studentDTO
     */
    @AfterReturning(returning = "studentDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.student.*.update(..))")
    public void updateStudentDTO(StudentDTO studentDTO) throws Exception {
        if (studentDTO != null) {
            prestigeInfoBO.completePersonalProfile(studentDTO);
        }
        System.out.println("更新个人资料之后，完成对声望的更新（包括缓存）");
    }

    /**
     * 完成买课交易之后（指评价完成后），对声望的操作
     *
     * @param evaluationCourseDTO
     */
    @AfterReturning(returning = "evaluationCourseDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.evaluation_course.*.save(..))")
    public void updateOrderBuyCourseDTO(EvaluationCourseDTO evaluationCourseDTO) throws Exception {
        if (evaluationCourseDTO != null) {
            prestigeInfoBO.completeDealCourse(evaluationCourseDTO);
        }
        System.out.println("评价课程之后，完成对声望的更新（包括缓存）");
    }

    /**
     * 评价学生之后，完成对学生声望得更新
     *
     * @param evaluationStudentDTO
     */
    @AfterReturning(returning = "evaluationStudentDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.evaluation_student.*.save(..))")
    public void saveEvaluationStudentDTO(EvaluationStudentDTO evaluationStudentDTO) throws Exception {
        if (evaluationStudentDTO != null) {
            prestigeInfoBO.completeEvaluateStudent(evaluationStudentDTO);
        }
        System.out.println("对学生评价之后，完成对声望的更新（包括缓存）");
    }

    /**
     * 评价老师之后，完成对学生声望得更新
     *
     * @param evaluationTeacherDTO
     */
    @AfterReturning(returning = "evaluationTeacherDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.evaluation_teacher.*.save(..))")
    public void saveEvaluationTeacherDTO(EvaluationTeacherDTO evaluationTeacherDTO) throws Exception {
        if (evaluationTeacherDTO != null) {
            prestigeInfoBO.completeEvaluateTeacher(evaluationTeacherDTO);
        }
        System.out.println("对老师评价之后，完成对声望的更新（包括缓存）");
    }


}
