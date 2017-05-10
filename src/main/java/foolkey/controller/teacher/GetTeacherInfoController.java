package foolkey.controller.teacher;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.send_to_client.TeacherAllInfoDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 根据老师的id，获取老师的信息
 * 参数（token；老师id：teacherId）
 * Created by ustcg on 2017/5/10.
 */
@Controller
@RequestMapping(value = "/teacher/getTeacherInfo")
public class GetTeacherInfoController extends AbstractController{

    @Autowired
    private TeacherInfoBO teacherInfoBO;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            //获取-解析JSON明文数据
            String clearText = request.getParameter("clearText");
            JSONObject clearJSON = JSONObject.fromObject(clearText);

//            String token = clearJSON.getString("token");
            Long teacherId = clearJSON.getLong("teacherId");

            //获取老师信息
            TeacherAllInfoDTO teacherAllInfoDTO = teacherInfoBO.getTeacherAllInfoDTO(teacherId);

            //封装-传送JSON
            jsonObject.put("result","success");
            jsonObject.put("teacherAllInfoDTO",teacherAllInfoDTO);
            jsonHandler.sendJSON(jsonObject,response);


        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
