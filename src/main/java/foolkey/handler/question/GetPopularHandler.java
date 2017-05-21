package foolkey.handler.question;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.question.QuestionBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.QuestionAnswerDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.question.QuestionAskerAnswerSTCDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 按页获取问答
 * 参数：
 * token：个人标识token
 * pageNO：页码
 * technicTagEnum：技术类别
 * 返回：
 * <p>
 * Created by GR on 2017/5/21.
 */
@Service
public class GetPopularHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private QuestionBO questionBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) throws Exception {

        //读取客户端传入参数
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        Integer pageNo = clearJSON.getInt("pageNo");
        String technicTagEnumStr = clearJSON.getString("technicTagEnum");
        TechnicTagEnum technicTagEnum = TechnicTagEnum.valueOf(technicTagEnumStr);

        //获取个人信息，进而判断是否可围观
        StudentDTO onlookerDTO = studentInfoBO.getStudentDTO(token);

        //获取热门的问答
        List<QuestionAnswerDTO> questionAnswerDTOS = questionBO.getPopularQuestionAnswerDTO(onlookerDTO, technicTagEnum, pageNo);

        //封装好,传给客户端的
        List<QuestionAskerAnswerSTCDTO> questionAskerAnswerSTCDTOS = questionBO.convertQuestionAnswerDTOToQuestionAskerAnswerSTCDTO(questionAnswerDTOS);

        //返回一个对应的list，标志是否有权限查看答案
        List<Boolean> isHavePermissionToViewS = new ArrayList<>();

        //临时存储答案的list
        List<String> answerContentTempS = new ArrayList<>();

        //加工返回结果，如果是未付款，则把答案隐藏
        for (QuestionAskerAnswerSTCDTO questionAskerAnswerSTCDTO : questionAskerAnswerSTCDTOS) {
            //临时存储答案，把传送给客户端的DTO中答案置空
            String answerContentTemp = questionAskerAnswerSTCDTO.getQuestionAnswerDTO().getAnswerContent();
            answerContentTempS.add(answerContentTemp);
            if (!questionBO.isHavePermissionToView(onlookerDTO, questionAskerAnswerSTCDTO.getQuestionAnswerDTO())) {
                //没有权限
                //答案置空
                questionAskerAnswerSTCDTO.getQuestionAnswerDTO().setAnswerContent(null);
                //标志位：没资格查看
                isHavePermissionToViewS.add(new Boolean(false));
            } else {
                //标志位：没资格查看
                isHavePermissionToViewS.add(new Boolean(true));
            }
        }
        jsonObject.put("result", "success");
        jsonObject.put("questionAskerAnswerSTCDTOS", questionAskerAnswerSTCDTOS);
        jsonObject.put("isHavePermissionToViewS", isHavePermissionToViewS);
        jsonHandler.sendJSON(jsonObject, response);

        //把置空的答案恢复之前的样子
        for (int i = 0; i < questionAskerAnswerSTCDTOS.size(); i++) {
            questionAskerAnswerSTCDTOS.get(i).getQuestionAnswerDTO().setAnswerContent(answerContentTempS.get(i));
        }

    }
}
