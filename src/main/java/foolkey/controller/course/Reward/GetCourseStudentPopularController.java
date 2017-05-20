package foolkey.controller.course.Reward;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.*;
import foolkey.pojo.send_to_client.CourseStudentPopularDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.reward.RewardWithStudentSTCDTO;
import foolkey.tool.StaticVariable;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ustcg on 2017/5/6.
 */
@Controller
@RequestMapping(value = "/courseStudent/getRewardCoursePopular")
public class GetCourseStudentPopularController extends AbstractController{

    @Autowired
    private RewardBO courseStudentBO;
    @Autowired
    private StudentInfoBO studentInfoBO;



    @RequestMapping
    public void execute(
            HttpServletRequest request,
//            @RequestParam("pageNo") Integer pageNo,
//            @RequestParam("technicTagEnum")TechnicTagEnum technicTagEnum,
            HttpServletResponse response
    ){
        try {
            //获取并解析JSON明文数据
            String clearText = request.getParameter("clearText");
            JSONObject clearJSON = JSONObject.fromObject(clearText);

            Integer pageNo = clearJSON.getInt("pageNo");
            String token =clearJSON.getString("token");

            //获取该登陆者的信息，方便后面传参(登陆者的技术类别)
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);

            //类别为空，则随机选取一个类别
            TechnicTagEnum technicTagEnum = null;
            if(studentDTO.getTechnicTagEnum() == null){
                Integer technicSize = TechnicTagEnum.values().length;
                System.out.println("技术类别中的数量:"+technicSize);
                Random random = new Random();
                Integer temp = random.nextInt(technicSize);
                technicTagEnum = TechnicTagEnum.values()[0];
                System.out.println(temp + "   "  + technicTagEnum);
            }else{
                technicTagEnum = studentDTO.getTechnicTagEnum();
            }



            //获取热门的悬赏
            List<RewardWithStudentSTCDTO> rewardWithStudentSTCDTOS = courseStudentBO.getCourseStudentPopularDTO(technicTagEnum, pageNo, StaticVariable.pageSize);
            for (RewardWithStudentSTCDTO RewardWithStudentSTCDTO : rewardWithStudentSTCDTOS) {
                System.out.println("热门悬赏：" + RewardWithStudentSTCDTO.getRewardDTO() + "--id:" + RewardWithStudentSTCDTO.getRewardDTO().getId());
                System.out.println("所属学生："+RewardWithStudentSTCDTO.getStudentDTO()+"---id:"+RewardWithStudentSTCDTO.getStudentDTO().getId());
            }

            //封装-传送json
            jsonObject.put("result","success");
            jsonObject.put("rewardCourseDTOS",rewardWithStudentSTCDTOS);
            jsonHandler.sendJSON(jsonObject,response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
