package foolkey.pojo.root.bo.student;

import foolkey.pojo.root.CAO.userInfo.UserCAO;
import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.security.AESKeyBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.RoleEnum;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取个人信息，需要AES加密，提交token即可
 * 密文传输
 * Created by geyao on 2017/5/2.
 */
@Service
@Transactional(readOnly = true)
public class GetStudentInfoHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");

        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);

        //新建一个用于传输的DTO，密码设置为""
        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.myClone(studentDTO1, studentDTO);
        studentDTO1.setPassWord("");//如果是老师，则一并返回teacherDTO
        TeacherDTO teacherDTO = null;

        System.out.println("获取到的个人信息是\n" + studentDTO);
        if (studentDTO.getRoleEnum().compareTo(RoleEnum.student) != 0){
            teacherDTO = teacherInfoBO.getTeacherDTO( studentDTO.getId() );
            if (teacherDTO != null)
                jsonObject.put("teacherDTO", teacherDTO);
        }

        jsonObject.put("studentDTO", studentDTO1);
        jsonObject.put("result", "success");

        jsonHandler.sendJSON(jsonObject, response);
    }
}
