package foolkey.pojo.root.bo.teacher;

import foolkey.pojo.root.DAO.student.GetStudentDAO;
import foolkey.pojo.root.DAO.teacher.GetTeacherDAO;
import foolkey.pojo.root.DAO.teacher.SaveTeacherDAO;
import foolkey.pojo.root.DAO.teacher.UpdateTeacherDAO;
import foolkey.pojo.send_to_client.TeacherAllInfoDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import foolkey.tool.BeanFactory;
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
    @Autowired
    private GetStudentDAO getStudentDAO;
    @Autowired
    private UpdateTeacherDAO updateTeacherDAO;
    @Autowired
    private SaveTeacherDAO saveTeacherDAO;

    public TeacherDTO getTeacherDTO(String id){
        return getTeacherDAO.getTeacherDTO( Long.parseLong(id) );
    }

    public TeacherDTO getTeacherDTO(Long id){
        return getTeacherDAO.getTeacherDTO( id );
    }

    public TeacherDTO updateTeacherDTO(TeacherDTO teacherDTO) throws Exception{
        updateTeacherDAO.update(teacherDTO);
        return teacherDTO;
    }

    public TeacherAllInfoDTO getTeacherAllInfoDTO(Long id) {
        StudentDTO studentDTO = getStudentDAO.get(StudentDTO.class,id);
        System.out.println("学生:"+studentDTO);
        TeacherDTO teacherDTO = getTeacherDAO.get(TeacherDTO.class,id);
        System.out.println("老师："+teacherDTO);
//        TeacherAllInfoDTO teacherAllInfoDTO = BeanFactory.getBean("teacherAllInfoDTO",TeacherAllInfoDTO.class);
        TeacherAllInfoDTO teacherAllInfoDTO = new TeacherAllInfoDTO();
        teacherAllInfoDTO.clone(studentDTO,teacherDTO);
        return teacherAllInfoDTO;
    }

    public TeacherDTO save(TeacherDTO teacherDTO){
        saveTeacherDAO.save(teacherDTO);
        return teacherDTO;
    }
}
