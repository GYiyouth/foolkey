package foolkey.pojo.send_to_client;

import foolkey.pojo.root.vo.dto.EvaluationCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Component;

/**
 * 发送到客户端的课程评价DTO
 * Created by ustcg on 2017/5/9.
 */
@Component("evaluationCourseSTCDTO")
public class EvaluationCourseSTCDTO {

    //评价DTO
    private EvaluationCourseDTO evaluationCourseDTO;
    //学生(评价人)DTO
    private StudentDTO studentDTO;


    @Override
    public String toString() {
        return "EvaluationCourseSTCDTO{" +
                "evaluationCourseDTO=" + evaluationCourseDTO +
                ", studentDTO=" + studentDTO +
                '}';
    }

    public EvaluationCourseDTO getEvaluationCourseDTO() {
        return evaluationCourseDTO;
    }

    public void setEvaluationCourseDTO(EvaluationCourseDTO evaluationCourseDTO) {
        this.evaluationCourseDTO = evaluationCourseDTO;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }
}
