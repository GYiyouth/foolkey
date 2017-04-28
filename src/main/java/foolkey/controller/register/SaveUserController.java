package foolkey.controller.register;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.CAO.userInfo.UserCAO;
import foolkey.pojo.root.bo.register.UserRegisterBO;
import foolkey.pojo.root.bo.security.AESKeyBO;
import foolkey.pojo.root.bo.security.SHA1KeyBO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.cache.Cache;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/4/26.
 */
@Controller
@RequestMapping("/rsa/register")
public class SaveUserController extends AbstractController {

    @Resource(name = "userRegisterBO")
    private UserRegisterBO userRegisterBO;
    @Resource(name = "aesKeyBO")
    private AESKeyBO aesKeyBO;
    @Resource(name = "sha1KeyBO")
    private SHA1KeyBO sha1KeyBO;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        JSONObject jsonObject = new JSONObject();
        try {
            String userName = request.getAttribute("userName").toString();
            String passWord = request.getAttribute("passWord").toString();
            String aesKey = request.getAttribute("aesKey").toString();
            //密码进行加密
            passWord = sha1KeyBO.encrypt(passWord);
            //生成token
            String token = userName + passWord;

            boolean flag = userRegisterBO.checkStudentByUserName(userName);
            boolean tokenFlag = userRegisterBO.checkStudentToken(token);

            if (flag == false && tokenFlag == false){
                //保存用户信息到数据库和缓存
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setUserName(userName);
                studentDTO.setPassWord(passWord);
                userRegisterBO.saveStudent(studentDTO);
                userRegisterBO.saveStudentToCache(token, studentDTO, aesKey);

                //返回AES加密后的用户token
                String tokenCipher = aesKeyBO.encrypt(token, aesKey);
                jsonObject.put("tokenCipher", tokenCipher);
                jsonObject.put("result", "success");
            }else {

            }
            jsonHandler.sendJSON(jsonObject, response);
        }catch (Exception e){
            e.printStackTrace();
            jsonHandler.sendJSON(jsonObject, response);
        }
    }
}
