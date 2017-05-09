package foolkey.pojo.root.bo.search;

import foolkey.pojo.root.DAO.course_student.GetCourseStudentDAO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.send_to_client.CourseStudentPopularDTO;
import foolkey.pojo.root.vo.dto.RewardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geyao on 2017/5/2.
 */
@Service
public class SearchRewardBO {

    @Autowired
    private GetCourseStudentDAO getCourseStudentDAO;
    @Autowired
    private RewardBO courseStudentBO;

    public List<CourseStudentPopularDTO> searchRewardDTO(ArrayList<String> keyList, ArrayList<TechnicTagEnum> techList, int pageNo) throws Exception {
        List<RewardDTO> courseStudentDTOS = new ArrayList<>();
        courseStudentDTOS = getCourseStudentDAO.findByPage("from foolkey.pojo.root.vo.dto.CourseStudentDTO course " +
                        " where course.topic like ? "
                , pageNo, 10
                , keyList.size() > 0? "%" + keyList.get(0) + "%" : "");
        //把悬赏封装为悬赏-学生DTO
        List<CourseStudentPopularDTO> result = courseStudentBO.convertCourseStudentIntoCourseStudentPopular(courseStudentDTOS);
        return result;
    }
}
