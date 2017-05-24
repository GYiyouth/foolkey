package foolkey.handler.application;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.ApplicationStateEnum;
import foolkey.pojo.root.vo.assistObject.StudentBaseEnum;
import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.ApplicationRewardWithTeacherSTCDTO;
import foolkey.pojo.send_to_client.application.ApplicationRewardWithStudentSTCDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 老师身份，获取我对悬赏的申请(待处理)
 * Created by GR on 2017/5/24.
 */
@Service
public class GetMyApplicationRewardAsTeacherHandler extends AbstractBO {


    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private ApplicationInfoBO applicationInfoBO;
    @Autowired
    private RewardBO rewardBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) throws Exception {

        //读取客户端传入参数
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        StudentDTO teacherDTO = studentInfoBO.getStudentDTO(token);
        Integer pageNo = clearJSON.getInt("pageNo");

        //获取到所有申请
        List<ApplicationStudentRewardDTO> applicationStudentRewardDTOS = applicationInfoBO.getApplicationRewardAsTeacher(teacherDTO.getId(), pageNo, ApplicationStateEnum.processing);

        List<ApplicationRewardWithStudentSTCDTO> applicationRewardWithStudentSTCDTOS = new ArrayList<>();

        for(ApplicationStudentRewardDTO applicationStudentRewardDTO:applicationStudentRewardDTOS){
            ApplicationRewardWithStudentSTCDTO applicationRewardWithStudentSTCDTO = new ApplicationRewardWithStudentSTCDTO();
            //申请DTO
            applicationRewardWithStudentSTCDTO.setApplicationStudentRewardDTO(applicationStudentRewardDTO);
            //悬赏DTO
            RewardDTO rewardDTO = rewardBO.getCourseStudentDTOById(applicationStudentRewardDTO.getRewardId());
            applicationRewardWithStudentSTCDTO.setRewardDTO(rewardDTO);
            //学生DTO
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(applicationStudentRewardDTO.getStudentId());
            applicationRewardWithStudentSTCDTO.setStudentDTO(studentDTO);

            applicationRewardWithStudentSTCDTOS.add(applicationRewardWithStudentSTCDTO);
        }

        jsonObject.put("result","success");
        jsonObject.put("applicationRewardWithStudentSTCDTOS",applicationRewardWithStudentSTCDTOS);
        jsonHandler.sendJSON(jsonObject,response);

    }
}
