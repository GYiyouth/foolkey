package foolkey.controller.course.Reward;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.tool.StaticVariable;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * 获取我发布的所有悬赏
 * Created by ustcg on 2017/5/8.
 */
@Controller
@RequestMapping(value = "/aes/reward/getMine")
public class MineController extends AbstractController{

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private RewardBO courseStudentBO;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
//            @RequestParam("pageNo")Integer pageNo,
//            @RequestParam("studentId")Long studentId,
            HttpServletResponse response
    ){
        try{
            // 获取-分析JSON传递的明文信息
            String clearText = request.getAttribute("clearText").toString();
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            Integer pageNo = clearJSON.getInt("pageNo");
            String token = clearJSON.getString("token");
            Long studentId = studentInfoBO.getStudentDTO(token).getId();

            //获取我的所有悬赏
            ArrayList<RewardDTO> courseStudentDTOS = courseStudentBO.getMyCourseStudentDTO(studentId, pageNo, StaticVariable.PAGE_SIZE);


            //封装-传送json
            jsonObject.put("result","success");
            jsonObject.put("courseStudentDTOS",courseStudentDTOS);
            jsonHandler.sendJSON(jsonObject,response);


        }catch(Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
