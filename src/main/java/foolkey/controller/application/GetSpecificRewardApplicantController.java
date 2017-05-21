package foolkey.controller.application;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.send_to_client.ApplicationRewardWithTeacherSTCDTO;
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
 * 根据悬赏id，获取所有的老师申请信息
 * 参数：clearText(页码：pageNo；token；悬赏：rewardId)
 * Created by ustcg on 2017/5/10.
 */
@Controller
@RequestMapping("/aes/reward/getApplicant")
public class GetSpecificRewardApplicantController extends AbstractController{

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private ApplicationInfoBO applicationInfoBO;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            //获取-解析JSON明文数据
            String clearText = request.getAttribute("clearText").toString();
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            //页码
            Integer pageNo = clearJSON.getInt("pageNo");
            //学生id
            String token = clearJSON.getString("token");
            Long studentId = studentInfoBO.getStudentDTO(token).getId();
            //悬赏Id
            Long rewardId = clearJSON.getLong("rewardId");

            //获取某个悬赏下所有申请
            List < ApplicationStudentRewardDTO > applicationStudentRewardDTOS = applicationInfoBO.getRewardApplicationDTOAsStudent(rewardId, pageNo, StaticVariable.pageSize);

            //封装成老师-申请DTO
            List < ApplicationRewardWithTeacherSTCDTO > applicationRewardWithTeacherSTCDTOS = applicationInfoBO.convertApplicationStudentRewardDTOInToApplicationRewardWithTeacherSTCDTO(applicationStudentRewardDTOS);


            //封装-传送jsonObject
            jsonObject.put("result", "success");
            jsonObject.put("applicationRewardWithTeacherSTCDTOS", applicationRewardWithTeacherSTCDTOS);
            jsonHandler.sendJSON(jsonObject, response);
        } catch (Exception e) {
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
