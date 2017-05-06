package foolkey.controller.course.CourseStudent;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.CourseStudent.CourseStudentBO;
import foolkey.pojo.root.vo.assistObject.*;
import foolkey.pojo.root.vo.cacheDTO.CourseStudentPopularDTO;
import foolkey.pojo.root.vo.cacheDTO.CourseTeacherPopularDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by ustcg on 2017/5/6.
 */
@Controller
@RequestMapping(value = "/courseStudent")
public class GetCourseStudentPopularController extends AbstractController{

    @Autowired
    private CourseStudentBO courseStudentBO;



    @RequestMapping(value = "/getRewardCoursePopular")
    public void execute(
            HttpServletRequest request,
            @RequestParam("pageNo") Integer pageNo,
            @RequestParam("technicTagEnum")TechnicTagEnum technicTagEnum,
            HttpServletResponse response
    ){
        try {
            //获取并解析JSON明文数据
//            String clearText = request.getParameter("clearText");
//            JSONObject clearJSON = JSONObject.fromObject(clearText);
//
//            Integer pageNo = clearJSON.getInt("pageNo");
//            String technicTag = clearJSON.getString("technicTagEnum");
//            TechnicTagEnum technicTagEnum = TechnicTagEnum.valueOf(technicTag);

            //获取热门的课程
            ArrayList<CourseStudentPopularDTO> courseStudentPopularDTOS = courseStudentBO.getCourseStudentPopularDTO(technicTagEnum, pageNo, 10);
            for (CourseStudentPopularDTO courseStudentPopularDTO : courseStudentPopularDTOS) {
                System.out.println("热门课程：" + courseStudentPopularDTO.getCourseStudentDTO() + "--id:" + courseStudentPopularDTO.getCourseStudentDTO().getId());
                System.out.println("所属老师："+courseStudentPopularDTO.getStudentDTO()+"---id:"+courseStudentPopularDTO.getStudentDTO().getId());
            }

            //封装-传送json
            jsonObject.put("result","success");
            jsonObject.put("courseStudentDTOS",courseStudentPopularDTOS);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
