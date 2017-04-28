package foolkey.pojo.root.DAO.student;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getStudentDAO")
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
}
