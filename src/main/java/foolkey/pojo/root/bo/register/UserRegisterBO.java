package foolkey.pojo.root.bo.register;

import foolkey.pojo.root.CAO.userInfo.UserCAO;
import foolkey.pojo.root.DAO.student.GetStudentDAO;
import foolkey.pojo.root.DAO.student.SaveStudentDAO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by geyao on 2017/4/28.
 */
@Service("userRegisterBO")
@Transactional
public class UserRegisterBO {

    @Resource(name = "getStudentDAO")
    private GetStudentDAO getStudentDAO;
    @Resource(name = "saveStudentDAO")
    private SaveStudentDAO saveStudentDAO;
    @Resource(name = "userCAO")
    private UserCAO userCAO;

    /**
     * 检验数据库有没有相同用户名
     * @param userName
     * @return
     */
    public boolean checkStudentByUserName(String userName){
        boolean flag = getStudentDAO.checkStudentDTOByUserName(userName);
        return flag;
    }

    /**
     * 检验用户的缓存区是否存在
     * @param token
     * @return
     */
    public boolean checkStudentToken(String token){
        return userCAO.containsUser(token);
    }

    /**
     * 保存用户DTO
     * @param studentDTO
     * @return
     */
    public StudentDTO saveStudent(StudentDTO studentDTO){
        saveStudentDAO.save(studentDTO);
        return studentDTO;
    }

    /**
     * 保存用户信息到缓存中
     * 初始化缓存区
     * 存储用户DTO
     * 存储对称密钥
     * @param token
     * @param studentDTO
     * @param aesKey
     */
    public void saveStudentToCache(String token, StudentDTO studentDTO, String aesKey){
        userCAO.initStudentCache(token);
        userCAO.saveStudentDTO(token, studentDTO);
        userCAO.saveStudentAESKey(token, aesKey);
    }
}
