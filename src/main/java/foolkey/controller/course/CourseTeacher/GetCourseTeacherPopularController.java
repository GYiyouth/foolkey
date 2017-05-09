package foolkey.controller.course.CourseTeacher;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.CourseTeacher.CourseTeacherBO;
import foolkey.pojo.root.bo.RelationFollow.RelationFollowBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.cacheDTO.CourseTeacherPopularDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by admin on 2017/4/28.
 */
@Controller
@RequestMapping(value = "/courseTeacher")
public class GetCourseTeacherPopularController extends AbstractController{

    @Autowired
    private CourseTeacherBO courseTeacherBO;
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private RelationFollowBO relationFollowBO;

    @RequestMapping(value = "/getCourseTeacherPopular")
    public void execute(
            HttpServletRequest request,
//            @RequestParam("token") String token,
//            @RequestParam("pageNo") Integer pageNo,
//            @RequestParam("technicTagEnum")TechnicTagEnum technicTagEnum,
            HttpServletResponse response
            ){
        try {
            //获取并解析JSON明文数据
            String clearText = request.getParameter("clearText").toString();
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            String token =clearJSON.getString("token");
            Integer pageNo = clearJSON.getInt("pageNo");
            String technicTag = clearJSON.getString("technicTagEnum");
            TechnicTagEnum technicTagEnum = TechnicTagEnum.valueOf(technicTag);
//            获取学生的id
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);

            System.out.println("获取流行课程的studnet\n" + studentDTO);
            Long studentId = studentDTO.getId();


//            Long studentId = 20003L;

            //获取热门的课程
            ArrayList<CourseTeacherPopularDTO> courseTeacherPopularDTOS = courseTeacherBO.getCourseTeacherPopularDTO(technicTagEnum, pageNo, 10);
            ArrayList<Boolean> followFlag = new ArrayList<>();
            for (CourseTeacherPopularDTO courseTeacherPopularDTO : courseTeacherPopularDTOS) {
//                System.out.println("热门课程：" + courseTeacherPopularDTO.getCourseTeacherDTO() + "--id:" + courseTeacherPopularDTO.getCourseTeacherDTO().getId());
//                System.out.println("所属老师："+courseTeacherPopularDTO.getTeacherAllInfoDTO()+"---id:"+courseTeacherPopularDTO.getTeacherAllInfoDTO().getId());
                boolean flag = relationFollowBO.isFollower(courseTeacherPopularDTO.getTeacherAllInfoDTO().getId(),studentId);
                followFlag.add(flag);
            }

            //封装-传送json
            jsonObject.put("result","success");
            jsonObject.put("courseTeacherDTOS",courseTeacherPopularDTOS);
            jsonObject.put("followFlag",followFlag);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
