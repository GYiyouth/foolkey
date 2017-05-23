package foolkey.handler.student;

import com.alibaba.fastjson.JSONException;
import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.SchoolEnum;
import foolkey.pojo.root.vo.assistObject.SexTagEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.Time;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by GR on 2017/5/23.
 */
@Service
public class UpdateStudentInfoHandler extends AbstractBO {

    @Autowired
    private StudentInfoBO studentInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) throws Exception {
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);
        String token = clearJSON.getString("token");
        //修改之前的DTO
        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        String sexTagEnumStr = clearJSON.getString("sexTagEnum");
        SexTagEnum sexTagEnum = SexTagEnum.valueOf(sexTagEnumStr);
        String organization = clearJSON.getString("organization");
        String birthdayStr;
//        try {
//             birthdayStr = clearJSON.getString("birthday");
//        }catch (net.sf.json.JSONException e){
//            birthdayStr = System.currentTimeMillis() + "";
//        }
//
//        Date birthday = Date.parse(birthdayStr);
        String technicTagEnumStr = clearJSON.getString("technicTagEnum");
        TechnicTagEnum technicTagEnum = TechnicTagEnum.valueOf(technicTagEnumStr);
        String schoolEnumStr = clearJSON.getString("schoolEnum");
        SchoolEnum schoolEnum;
        try {
            schoolEnum = SchoolEnum.valueOf(schoolEnumStr);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            schoolEnum = SchoolEnum.其他;
        }
        String githubUrl = clearJSON.getString("githubUrl");
        String nickedName = clearJSON.getString("nickedName");
        String slogan = clearJSON.getString("slogan");
        String email = clearJSON.getString("email");
        String description = clearJSON.getString("description");

        //赋新值
        studentDTO.setSexTagEnum(sexTagEnum);
        studentDTO.setOrganization(organization);
//        studentDTO.setBirthday(birthday);
        studentDTO.setTechnicTagEnum(technicTagEnum);
        studentDTO.setSchoolEnum(schoolEnum);
        studentDTO.setGithubUrl(githubUrl);
        studentDTO.setNickedName(nickedName);
        studentDTO.setSlogan(slogan);
        studentDTO.setEmail(email);
        studentDTO.setDescription(description);

        studentInfoBO.updateStudent(studentDTO);

        jsonObject.put("result", "success");
        jsonObject.put("studentDTO", studentDTO);
        jsonHandler.sendJSON(jsonObject, response);
    }
}