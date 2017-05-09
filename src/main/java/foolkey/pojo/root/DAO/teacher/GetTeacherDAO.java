package foolkey.pojo.root.DAO.teacher;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getTeacherDAO")
@Transactional
public class GetTeacherDAO extends GetBaseDAO<TeacherDTO>{

    /**
     * 根据id获取老师信息
     * @param id
     * @return
     */
    public TeacherDTO getTeacherDTO(Long id){
        return hibernateTemplate.get(TeacherDTO.class, id);
    }
}
