package foolkey.controller.GR_test;

import foolkey.controller.AbstractController;
import foolkey.pojo.Test;
import foolkey.pojo.root.bo.Course.CourseBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GR on 2017/5/3.
 */
@Controller
@RequestMapping(value = "/test")
public class GetAllCourseTeacherPopular extends AbstractController{

    @Resource(name = "courseTeacherBO")
    private CourseBO courseTeacherBO;

    @Autowired
    private StudentInfoBO studentInfoBO;
    @RequestMapping(value = "/getCourseTeacherPopular")
    public void execute(
            HttpServletRequest request,
//            @RequestParam("pageNo") Integer pageNo,
            @RequestParam("technicTagEnum")TechnicTagEnum technicTagEnum,
            HttpServletResponse response
    ){
        try {

//            ArrayList<CourseTeacherDTO> courseTeacherDTOArrayList = courseTeacherBO.getAllCourseTeacherDTOInPopular(technicTagEnum);
//            for (CourseTeacherDTO courseTeacherDTO : courseTeacherDTOArrayList) {
//                System.out.println("------" + courseTeacherDTO + "--id:" + courseTeacherDTO.getId());
//            }
//
            jsonObject.put("result","success");
//            jsonObject.put("courseTeacherDTOS",courseTeacherDTOArrayList);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }


    @RequestMapping
    public void execute(
            HttpServletResponse response
    )throws Exception{
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("result", Test.foo());
        jsonHandler.sendJSON(jsonObject, response);
    }

}
