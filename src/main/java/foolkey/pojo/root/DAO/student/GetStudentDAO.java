package foolkey.pojo.root.DAO.student;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getStudentDAO")
@Transactional
public class GetStudentDAO extends GetBaseDAO<StudentDTO>{



    @Resource(name = "hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    public boolean checkStudentDTOByUserName(String userName){
        List list = hibernateTemplate.find(
                "from foolkey.pojo.root.vo.dto.StudentDTO s " +
                        " where s.userName = ?" , userName);
        if (list.size() > 0){
            return true;
        }else
            return false;
    }

    /**
     * 获取用户DTO
     * @param userName 用户名
     * @param passWord 密码，密文
     * @return
     */
    public StudentDTO getStudentDTO(String userName, String passWord){
        List<StudentDTO> list = ( List<StudentDTO> )
                hibernateTemplate.find(
                        "from foolkey.pojo.root.vo.dto.StudentDTO s " +
                                "where s.userName = ? and s.passWord = ?"
                        , userName, passWord
                );
        if (list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
