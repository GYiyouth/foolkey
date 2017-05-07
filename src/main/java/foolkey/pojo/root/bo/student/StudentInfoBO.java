package foolkey.pojo.root.bo.student;

import foolkey.pojo.root.CAO.userInfo.UserCAO;
import foolkey.pojo.root.DAO.student.GetStudentDAO;
import foolkey.pojo.root.DAO.student.UpdateStudentDAO;
import foolkey.pojo.root.bo.security.SHA1KeyBO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.TokenCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 验证用户是否存在等方法，在 foolkey.pojo.root.bo.register.UserRegisterBO
 * Created by geyao on 2017/4/28.
 */
@Service(value = "studentInfoBO")
@Transactional
public class StudentInfoBO {

    @Resource(name = "getStudentDAO")
    private GetStudentDAO getStudentDAO;
    @Autowired
    private UpdateStudentDAO updateStudentDAO;
    @Resource(name = "userCAO")
    private UserCAO userCAO;
    @Resource(name = "sha1KeyBO")
    private SHA1KeyBO sha1KeyBO;

    /**
     * 获取学生信息
     * 先问缓存，再问数据库
     * @param userName 用户名明文
     * @param passWord 密码明文
     * @return
     */
    public StudentDTO getStudentDTO(String userName, String passWord){
        //对密码进行 SHA1加密
            //让APP来做
        //生成token
        String token = userName + passWord;
        //根据token问缓存
        if (userCAO.containStudentDTO(token))
            return userCAO.getStudentDTO(token);
        else {//根据用户名密码问数据库
            return getStudentDAO.getStudentDTO(userName, passWord);
        }
        //如果不存在，返回null
    }

    /**
     * 获取学生信息，直接根据token
     * @param token
     * @return
     */
    public StudentDTO getStudentDTO(String token){
        //根据token问缓存
        if (userCAO.containStudentDTO(token))
            return userCAO.getStudentDTO(token);
        else {//缓存中没有这个人的信息，让它重新登录吧，仅知道token的情况下，无法从数据库取
            return null;
        }
    }

    /**
     * 获取学生信息，根据id
     * @param id
     * @return
     */
    public StudentDTO getStudentDTO(Long id){
        return getStudentDAO.get(StudentDTO.class, id);
    }

    /**
     * 更新用户信息，同时更新数据库和缓存
     * @param studentDTO
     */
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        updateStudentDAO.update(studentDTO);
        String token = TokenCreator.createToken(studentDTO.getUserName(), studentDTO.getPassWord());
        userCAO.saveStudentDTO(token, studentDTO);
        return studentDTO;
    }


    /**
     * 把学生信息添加到缓存
     */
    public void fillStudentDTOToCache(){
        ArrayList<StudentDTO> studentDTOS = getStudentDAO.findAll(StudentDTO.class);
        for(StudentDTO studentDTO:studentDTOS){
            String token = TokenCreator.createToken(studentDTO.getUserName(),studentDTO.getPassWord());
            userCAO.saveStudentDTO(token,studentDTO);
        }
    }
}
