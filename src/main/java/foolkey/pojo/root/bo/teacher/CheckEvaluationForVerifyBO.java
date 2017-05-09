package foolkey.pojo.root.bo.teacher;

import foolkey.pojo.root.DAO.evaluation_teacher.GetEvaluationTeacherDAO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.RoleEnum;
import foolkey.pojo.root.vo.assistObject.VerifyStateEnum;
import foolkey.pojo.root.vo.dto.EvaluationTeacherDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import foolkey.tool.constant_values.TeacherVerifyNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查过往评价，未非认证老师进行认证
 * 只检查对老师的评价
 * Created by geyao on 2017/5/7.
 */
@Service
@Transactional
public class CheckEvaluationForVerifyBO {
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;
    @Autowired
    private MessageBO messageBO;
    @Autowired
    private GetEvaluationTeacherDAO getEvaluationTeacherDAO;

    /**
     * 获取过往的5次教师评价
     * @param teacher
     * @return
     */
    private List<EvaluationTeacherDTO> getEvaluationDTO(StudentDTO teacher){
        return getEvaluationTeacherDAO.findByPage("from foolkey.pojo.root.vo.dto.EvaluationTeacherDTO e " +
                " where e.acceptor_id = ? order by e.id desc ", 1
                , TeacherVerifyNum.VERIFY_NEED_EVALUATION_TIMES, teacher.getId());
    }

    /**
     * 查看最近的5次教师评价，查看是否认证成功。
     * @param teacher
     * @param teacherDTO
     */
    public void execute(StudentDTO teacher, TeacherDTO teacherDTO) throws Exception{
        List<EvaluationTeacherDTO> list = getEvaluationDTO(teacher);

        if (list.size() == TeacherVerifyNum.VERIFY_NEED_EVALUATION_TIMES){
            boolean successFlag = true;
            //已经有了5次评价
            for (EvaluationTeacherDTO evaluationTeacherDTO: list){
                if (evaluationTeacherDTO.getScore() < TeacherVerifyNum.HIGH_SCORE_DEFINE ){
                    //如果有低于3.0的差评，则认证失败
                    successFlag = false;
                    break;
                }
            }
            if (successFlag){
                //认证成功
                teacher.setRoleEnum(RoleEnum.teacher);
                teacherDTO.setVerifyState( VerifyStateEnum.verified );
                messageBO.sendForVerifyPassed(teacher);
            }else {
                //认证失败
                teacher.setRoleEnum(RoleEnum.alreadyApplied);
                teacherDTO.setVerifyState( VerifyStateEnum.verified );
                messageBO.sendForVerifyFailed(teacher);
            }
            teacherInfoBO.updateTeacherDTO(teacherDTO);
            studentInfoBO.updateStudent(teacher);
        }
    }
}
