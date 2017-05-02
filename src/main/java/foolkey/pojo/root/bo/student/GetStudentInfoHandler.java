package foolkey.pojo.root.bo.student;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取个人信息，需要AES加密，提交token即可
 * Created by geyao on 2017/5/2.
 */
@Service
@Transactional(readOnly = true)
public class GetStudentInfoHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");

        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);

        studentDTO.setPassWord("");

        jsonObject.put("studentDTO", studentDTO);
        jsonObject.put("result", "success");

        jsonHandler.sendJSON(jsonObject, response);
    }
}
