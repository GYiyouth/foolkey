package foolkey.controller.application;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import foolkey.pojo.send_to_client.ApplicationStudentRewardSTCDTO;
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
            Long studentId = clearJSON.getLong("studentId");
            //悬赏id
            Long courseId = clearJSON.getLong("courseId");

            //首先获取到申请我的悬赏的申请DTO
            List<ApplicationStudentRewardDTO> applicationStudentRewardDTOS = applicationInfoBO.getRewardApplicationDTOAsStudent(studentId,courseId,pageNo, StaticVariable.pageSize);

            //封装申请DTO
            List<ApplicationStudentRewardSTCDTO> applicationStudentRewardSTCDTOS = applicationInfoBO.converApplicationStudentRewardDTOInToApplicationStudentRewardSTCDTO(applicationStudentRewardDTOS);

            //封装-传送jsonObject
            jsonObject.put("result","success");
            jsonObject.put("applicationStudentRewardSTCDTOS",applicationStudentRewardSTCDTOS);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }

    }
}
