package foolkey.handler.application;

import com.xiaomi.xmpush.server.Result;
import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拒绝一个老师对于悬赏任务的申请
 * AES加密
 * 需要token， applicationId
 * Created by geyao on 2017/5/4.
 */
@Service
@Transactional
public class RefuseTeacherApplicationHandler extends AbstractBO{


    @Autowired
    private ApplicationInfoBO applicationInfoBO;
    @Autowired
    private MessageBO messageBO;
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private RewardBO rewardBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) throws Exception{


        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);


        //获取信息
        Long applicationId = clearJSON.getLong("applicationId");

        //获取到要删除的悬赏DTO
        ApplicationStudentRewardDTO applicationStudentRewardDTO = applicationInfoBO.getRewardApplicationDTO(applicationId);

        //删除申请
        applicationInfoBO.deleteRewardApplication( applicationId );
        //返回消息
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);


        //给老师发消息
        //获取悬赏发布者信息
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(applicationStudentRewardDTO.getStudentId());
        StudentDTO teacherDTO = studentInfoBO.getStudentDTO(applicationStudentRewardDTO.getApplicantId());
        RewardDTO rewardDTO = rewardBO.getCourseStudentDTOById(applicationStudentRewardDTO.getRewardId());
        messageBO.sendForPayRewardRefuse(studentDTO,teacherDTO,rewardDTO);


    }
}
