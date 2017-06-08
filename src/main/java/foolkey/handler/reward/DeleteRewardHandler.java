package foolkey.handler.reward;

import foolkey.pojo.root.DAO.course_student.DeleteCourseStudentDAO;
import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.Reward.RewardBO;
import foolkey.pojo.root.vo.assistObject.RewardStateEnum;
import foolkey.pojo.root.vo.dto.RewardDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 删除悬赏
 * Created by GR on 2017/5/25.
 */
@Service
@Transactional
public class DeleteRewardHandler extends AbstractBO {

    @Autowired
    private RewardBO rewardBO;
    @Autowired
    private DeleteCourseStudentDAO deleteCourseStudentDAO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) throws Exception {
        //读取客户端传入参数
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        //悬赏id
        Long rewardId = clearJSON.getLong("rewardId");

        //获取到悬赏DTO
        RewardDTO rewardDTO = rewardBO.getCourseStudentDTOById(rewardId);
        if(rewardDTO.getRewardStateEnum().equals(RewardStateEnum.已解决)){
            jsonHandler.sendFailJSON(response);
        }

        rewardBO.deleteRewardDTO(rewardDTO);

        //返回result
        jsonObject.put("result", "success");
        jsonHandler.sendJSON(jsonObject, response);
    }

}
