package foolkey.controller.collectionCourse;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.course.CourseBO;
import foolkey.pojo.root.bo.collection.CollectionBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.CollectionCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.course.CourseWithTeacherSTCDTO;
import foolkey.tool.StaticVariable;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by ustcg on 2017/5/8.
 */
@Controller
@RequestMapping(value = "/collectionCourse/getMyCollection")
public class GetMyCollectionController extends AbstractController{

    @Autowired
    private CollectionBO collectionBO;
    @Autowired
    private CourseBO courseTeacherBO;
    @Autowired
    private StudentInfoBO studentInfoBO;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
//            @RequestParam("token")String token,
//            @RequestParam("pageNo")Integer pageNo,
//            @RequestParam("studentId")Long studentId,
            HttpServletResponse response
    ){
        try {
            //获取-解析JSON明文数据
            String clearText = request.getParameter("clearText");
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            Integer pageNo = clearJSON.getInt("pageNo");
            String token = clearJSON.getString("token");

            StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);

            //首先获取到收藏课程DTO
            ArrayList<CollectionCourseDTO> collectionCourseDTOS = collectionBO.getMyCollectionCourseDTOS(studentDTO.getId(), pageNo, StaticVariable.PAGE_SIZE);

            //遍历获取到课程-老师DTO
            ArrayList<CourseWithTeacherSTCDTO> courseWithTeacherSTCDTOS = new ArrayList<>();
            for(CollectionCourseDTO collectionCourseDTO:collectionCourseDTOS){
                CourseWithTeacherSTCDTO courseWithTeacherSTCDTO = courseTeacherBO.getCourseWithTeacherSTCDTOByCourseId(collectionCourseDTO.getCourseId());
                courseWithTeacherSTCDTOS.add(courseWithTeacherSTCDTO);
            }

            //封装-传送jsonObject
            jsonObject.put("result","success");
            jsonObject.put("courseWithTeacherSTCDTOS",courseWithTeacherSTCDTOS);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
