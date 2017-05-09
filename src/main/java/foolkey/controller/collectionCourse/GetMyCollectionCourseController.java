package foolkey.controller.collectionCourse;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.bo.collection.CollectionBO;
import foolkey.pojo.root.vo.cacheDTO.CourseTeacherPopularDTO;
import foolkey.pojo.root.vo.dto.CollectionCourseDTO;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
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
@RequestMapping(value = "/collectionCourse")
public class GetMyCollectionCourseController extends AbstractController{

    @Autowired
    private CollectionBO collectionBO;
    @Autowired
    private CourseTeacherBO courseTeacherBO;

    @RequestMapping(value = "/getMyCollectionCourse")
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
            Long studentId = clearJSON.getLong("studentId");

            //首先获取到收藏课程DTO
            ArrayList<CollectionCourseDTO> collectionCourseDTOS = collectionBO.getMyCollectionCourseDTOS(studentId, pageNo, StaticVariable.pageSize);

            //遍历获取到课程-老师DTO
            ArrayList<CourseTeacherPopularDTO> courseTeacherPopularDTOS = new ArrayList<>();
            for(CollectionCourseDTO collectionCourseDTO:collectionCourseDTOS){
                CourseTeacherPopularDTO courseTeacherPopularDTO = courseTeacherBO.getCourseTeacherPopularDTOByCourseId(collectionCourseDTO.getCourseId());
                courseTeacherPopularDTOS.add(courseTeacherPopularDTO);
            }

            //封装-传送jsonObject
            jsonObject.put("result","success");
            jsonObject.put("courseTeacherDTOS",courseTeacherPopularDTOS);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
