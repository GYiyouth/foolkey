package foolkey.handler.application;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
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
 * 需要token，teacherId，courseId
 * Created by geyao on 2017/5/4.
 */
@Service
@Transactional
public class RefuseTeacherApplicationHandler extends AbstractBO{

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private RewardBO courseStudentBO;
    @Autowired
    private ApplicationInfoBO applicationInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) throws Exception{

        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);


        //获取信息
        Long courseId = clearJSON.getLong("courseId");
        Long studentId = clearJSON.getLong("teacherId"); // 实际上老师id就是老师的studentId

        RewardDTO courseStudentDTO = courseStudentBO.getCourseStudentDTO(courseId);
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(studentId);

        //删除申请
        applicationInfoBO.deleteRewardApplication(
                courseStudentDTO.getCreatorId(), studentDTO.getId());

        //返回消息
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);



    }
}
