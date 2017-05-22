package foolkey.controller.Evaluation.EvaluationCourse;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.evaluation.EvaluationInfoBO;
import foolkey.pojo.root.vo.dto.EvaluationCourseDTO;
import foolkey.pojo.send_to_client.EvaluationCourseSTCDTO;
import foolkey.tool.StaticVariable;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by ustcg on 2017/5/8.
 */
@Controller
@RequestMapping(value = "/evaluationCourse/getEvaluationCourse")
public class GetEvaluationCourseController extends AbstractController{

    @Autowired
    private EvaluationInfoBO evaluationInfoBO;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
//            @RequestParam("token") String token,
//            @RequestParam("pageNo")Integer pageNo,
//            @RequestParam("courseId")Long courseId,
            HttpServletResponse response
    ){
        try{
            //获取-解析JSON明文数据
            String clearText = request.getParameter("clearText");
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            Integer pageNo = clearJSON.getInt("pageNo");
            Long courseId = clearJSON.getLong("courseId");

            //获取评论
            ArrayList<EvaluationCourseDTO> evaluationCourseDTOS = evaluationInfoBO.getEvaluationCourseDTOByCourseTeacherId(courseId,pageNo, StaticVariable.PAGE_SIZE);

            //封装成评价-评价人
            ArrayList<EvaluationCourseSTCDTO> evaluationCourseSTCDTOS = evaluationInfoBO.convertEvaluationCourseDTOIntoEvaluationCourseSTCDTO(evaluationCourseDTOS);

            //封装-传送jsonObject
            jsonObject.put("result","success");
            jsonObject.put("evaluationCourseSTCDTO",evaluationCourseSTCDTOS);
            jsonHandler.sendJSON(jsonObject,response);




        }catch(Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }




    }
}
