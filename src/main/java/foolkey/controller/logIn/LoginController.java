package foolkey.controller.logIn;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.register.UserRegisterBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.bo.teacher.TeacherInfoBO;
import foolkey.pojo.root.vo.assistObject.RoleEnum;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import foolkey.tool.TokenCreator;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用用户名、密码登录
 * 这种情况的产生有以下原因：
 *
 * 客户端丢失了token
 * 服务器丢失了该用户的缓存  （不存在，若丢失，从数据库取）
 *
 * 需要用户提交 ：
 * RSA加密的 —— userName、passWord、AESKey
 * 其中，这里的passWord，客户端已经把它 SHA1 加密过了,即，userName先用SHA1加密，再和其他信息一样，用RSA统一加密
 * 返回结果  result  -success
 * 如果用户不存在、即用户名密码错误，则还会附加 existing - false
 * Created by geyao on 2017/4/28.
 */
@Controller
@RequestMapping("/rsa/login")
public class LoginController extends AbstractController {

    @Resource(name = "userRegisterBO")
    private UserRegisterBO userRegisterBO;
    @Resource(name = "studentInfoBO")
    private StudentInfoBO studentInfoBO;
    @Autowired
    private TeacherInfoBO teacherInfoBO;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        try {
            //request中获取JSONObject，拿取三项信息
            String userName = request.getAttribute("userName").toString();
            String passWord = request.getAttribute("passWord").toString();
            String aesKey = request.getAttribute("AESKey").toString();

            //在缓存、数据库中验证用户 用户名、密码
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(userName, passWord);
            if (studentDTO == null){
                jsonObject.put("existing", "false");
                jsonHandler.sendJSON(jsonObject, response);
                return;
            }
            //生成token
            //清空缓存、新建缓存
                //AOP来做
            //
            String token = TokenCreator.createToken(userName, passWord);
            //保存用户的aesKey
            userRegisterBO.saveStudentToCache(token, studentDTO, aesKey);

            //如果是老师，则一并返回teacherDTO
//            TeacherDTO teacherDTO = null;
//            if (studentDTO.getRoleEnum().compareTo(RoleEnum.student) != 0){
//                teacherDTO = teacherInfoBO.getTeacherDTO( studentDTO.getId() );
//                if (teacherDTO != null)
//                    jsonObject.put("teacherDTO", teacherDTO);
//            }
//            jsonObject.put("studentDTO", studentDTO);
            jsonObject.put("id", studentDTO.getId());
            jsonObject.put("token", token);
            jsonObject.put("result", "success");
            jsonHandler.sendJSON(jsonObject, response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendFailJSON(response);
        }
    }
}
