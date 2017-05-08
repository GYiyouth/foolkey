package foolkey.controller.studentInfo;

import foolkey.controller.AbstractController;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ustcg on 2017/5/8.
 */
@Controller
@RequestMapping(value = "getMyCollectionCourseController")
public class GetMyCollectionCourseController extends AbstractController{

    public void execute(
            HttpServletRequest request,
            @RequestParam("token")String token,
            @RequestParam("studentId")String studentId,
            HttpServletResponse response
    ){
        try {
            //获取并解析JSON明文数据
//            String clearText = request.getParameter("clearText");
//            JSONObject clearJSON = JSONObject.fromObject(clearText);
//            String token = clearJSON.getString("token");
//            Long studentId = clearJSON.getLong("studentId");






        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }

    }
}
