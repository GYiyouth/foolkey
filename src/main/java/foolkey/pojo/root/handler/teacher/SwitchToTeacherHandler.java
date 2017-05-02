package foolkey.pojo.root.handler.teacher;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 切换到教师身份
 * 明文传输，需要token
 * 返回是否认证的标志 verified - no, verified, processing 未认证、已认证、认证中
 * Created by geyao on 2017/5/2.
 */
@Service
@Transactional(readOnly = true)
public class SwitchToTeacherHandler extends AbstractBO{
    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String token = request.getParameter("token");

        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);

        TeacherDTO teacherDTO = teacherInfoBO.getTeacherDTO(studentDTO.getId());

        if (teacherDTO == null)
            jsonObject.put("verified", "no");
        else {
            jsonObject.put("verified", teacherDTO.getVerifyState().toString());
            jsonObject.put("teacherDTO", teacherDTO);
            jsonObject.put("result", "success");
        }

        jsonHandler.sendJSON(jsonObject, response);
    }
}
