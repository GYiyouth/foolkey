package foolkey.controller.application;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.send_to_client.ApplicationRewardWithTeacherSTCDTO;
import foolkey.pojo.send_to_client.ApplicationStudentRewardAsStudentSTCDTO;
import foolkey.tool.StaticVariable;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ustcg on 2017/5/9.
 */
@Controller
@RequestMapping(value = "/application")
public class GetApplicationStudentRewardAsStudentController extends AbstractController{

    @Autowired
    private ApplicationInfoBO applicationInfoBO;
    @Autowired
    private RewardBO rewardBO;
    @Autowired
    private StudentInfoBO studentInfoBO;

    @RequestMapping(value = "/getApplicationStudentRewardAsStudentController")
    public void execute(
        HttpServletRequest request,
        HttpServletResponse response
    ){
        try {
            //获取-解析JSON明文数据
            String clearText = request.getParameter("clearText");
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            //页码
            Integer pageNo = clearJSON.getInt("pageNo");
            //学生id
            String token = clearJSON.getString("token");

            Long studentId = studentInfoBO.getStudentDTO(token).getId();

            //首先获取到我发布的悬赏DTO
            ArrayList<RewardDTO> rewardDTOS = rewardBO.getMyCourseStudentDTO(studentId,pageNo,StaticVariable.pageSize);

            List<ApplicationStudentRewardAsStudentSTCDTO> applicationStudentRewardAsStudentSTCDTOS = new ArrayList<>();
            //每一个悬赏对应一个DTO
            //里面有   悬赏DTO-老师申请DTOS
            for (RewardDTO rewardDTO:rewardDTOS){
                ApplicationStudentRewardAsStudentSTCDTO applicationStudentRewardAsStudentSTCDTO = new ApplicationStudentRewardAsStudentSTCDTO();
                //悬赏DTO
                applicationStudentRewardAsStudentSTCDTO.setRewardDTO(rewardDTO);
                //老师申请DTOS
                //首先根据悬赏获取该悬赏下，一定数目的申请老师，具体数值在 RewardLimit类下
                List<ApplicationStudentRewardDTO> applicationStudentRewardDTOS = applicationInfoBO.getRewardApplicationDTOAsStudent(rewardDTO.getId());
                List<ApplicationRewardWithTeacherSTCDTO> applicationRewardWithTeacherSTCDTOS = applicationInfoBO.convertApplicationStudentRewardDTOInToApplicationRewardWithTeacherSTCDTO(applicationStudentRewardDTOS);
                applicationStudentRewardAsStudentSTCDTO.setApplicationRewardWithTeacherSTCDTOS(applicationRewardWithTeacherSTCDTOS);

                applicationStudentRewardAsStudentSTCDTOS.add(applicationStudentRewardAsStudentSTCDTO);
            }

            //封装-传送jsonObject
            jsonObject.put("result","success");
            jsonObject.put("applicationStudentRewardAsStudentSTCDTOS",applicationStudentRewardAsStudentSTCDTOS);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }

    }
}
