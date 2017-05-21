package foolkey.handler.question;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.question.QuestionBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.QuestionStateEnum;
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
 * 获取我提问的问题（学生身份提问）
 * 参数：
 * token：用户的标识:String
 * pageNo：请求的页码:Integer
 * questionStateEnum：问题的状态（已回答、待回答）:String
 * 返回：
 * questionAskerAnswerSTCDTOS：问题-提问者-回答者封装的DTO：List<QuestionAskerAnswerSTCDTO>
 * Created by GR on 2017/5/21.
 */
@Service
public class GetQuestionAsAskerHandler extends AbstractBO {

    @Autowired
    private QuestionBO questionBO;
    @Autowired
    private StudentInfoBO studentInfoBO;

    public void execute(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObject) throws Exception {

        //读取客户端传入参数
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        //回答者的DTO
        StudentDTO askerDTO = studentInfoBO.getStudentDTO(token);
        Integer pageNo = clearJSON.getInt("pageNo");
        String questionStateEnumStr = clearJSON.getString("questionStateEnum");
        QuestionStateEnum questionStateEnum = QuestionStateEnum.valueOf(questionStateEnumStr);

        //问题DTO的集合
        List<QuestionAnswerDTO> questionAnswerDTOS = new ArrayList<>();
        questionAnswerDTOS = questionBO.getQuestionAsAsker(askerDTO, questionStateEnum, pageNo);

        //封装成 提问者-问题-回答者DTO
        List<QuestionAskerAnswerSTCDTO> questionAskerAnswerSTCDTOS = questionBO.convertQuestionAnswerDTOToQuestionAskerAnswerSTCDTO(questionAnswerDTOS);

        //返回result
        jsonObject.put("result", "success");
        jsonObject.put("questionAskerAnswerSTCDTOS", questionAskerAnswerSTCDTOS);
        jsonHandler.sendJSON(jsonObject, response);
    }
}