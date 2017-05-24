package foolkey.pojo.root.DAO.application_student_reward;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.assistObject.ApplicationStateEnum;
import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import foolkey.tool.StaticVariable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getApplicationStudentRewardDAO")
public class GetApplicationStudentRewardDAO extends GetBaseDAO<ApplicationStudentRewardDTO> {

    //根据申请状态，获取老师对悬赏的申请
    public List<ApplicationStudentRewardDTO> getApplicationRewardAsTeacher(Long teacherId, Integer pageNo, ApplicationStateEnum applicationStateEnum) {
        String hql = "from ApplicationStudentRewardDTO asr where asr.applicantId = ? and asr.state = ? order by asr.applyTime desc";
        return findByPage(hql, pageNo, StaticVariable.PAGE_SIZE, teacherId, applicationStateEnum);

    }
}
