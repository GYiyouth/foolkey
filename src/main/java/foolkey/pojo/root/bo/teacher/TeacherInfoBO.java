package foolkey.pojo.root.bo.teacher;

import foolkey.pojo.root.DAO.teacher.GetTeacherDAO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by geyao on 2017/5/1.
 */
@Service("teacherInfoBO")
@Transactional
public class TeacherInfoBO {

    @Autowired
    private GetTeacherDAO getTeacherDAO;

    public TeacherDTO getTeacherDTO(String id){
        return getTeacherDAO.getTeacherDTO( Long.parseLong(id) );
    }

    public TeacherDTO getTeacherDTO(Long id){
        return getTeacherDAO.getTeacherDTO( id );
    }
}
