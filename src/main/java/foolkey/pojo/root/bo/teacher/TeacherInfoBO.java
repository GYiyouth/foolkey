package foolkey.pojo.root.bo.teacher;

import foolkey.pojo.root.CAO.userInfo.UserCAO;
import foolkey.pojo.root.DAO.student.GetStudentDAO;
import foolkey.pojo.root.DAO.teacher.GetTeacherDAO;
import foolkey.pojo.root.DAO.teacher.SaveTeacherDAO;
import foolkey.pojo.root.DAO.teacher.UpdateTeacherDAO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.AbstractDTO;
import foolkey.pojo.send_to_client.TeacherAllInfoDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
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
    @Autowired
    private UpdateTeacherDAO updateTeacherDAO;
    @Autowired
    private SaveTeacherDAO saveTeacherDAO;
    @Autowired
    private UserCAO userCAO;
    @Autowired
    private StudentInfoBO studentInfoBO;

    /**
     * 获取老师信息，先问缓存，再问数据库
     * @param id
     * @return
     */
    public TeacherDTO getTeacherDTO(String id){
        return getTeacherDTO( Long.parseLong( id ) );
    }
    public TeacherDTO getTeacherDTO(Long id){

        TeacherDTO teacherDTO;

        String token = userCAO.getUserToken( id );

        if ( userCAO.containTeacherDTO(token))
            teacherDTO = userCAO.getTeacherDTO( token );
        else {
            //缓存没有，从数据库取
            teacherDTO = getTeacherDAO.getTeacherDTO( id );
        }
        return teacherDTO;
    }

    /**
     * 更新用户信息
     * 先更数据库，再更缓存
     * @param teacherDTO
     * @return
     * @throws Exception
     */
    public void updateTeacherDTO(TeacherDTO teacherDTO) throws Exception{
        updateTeacherDAO.update(teacherDTO);
        userCAO.saveTeacherDTO( userCAO.getUserToken( teacherDTO.getId()),
                    teacherDTO
                );
    }

    public TeacherAllInfoDTO getTeacherAllInfoDTO(Long id) throws Exception{
        StudentDTO studentDTO = studentInfoBO.getStudentDTO( id );
        System.out.println("学生:"+studentDTO);
        TeacherDTO teacherDTO = getTeacherDTO( id );
        System.out.println("老师:"+teacherDTO);
//        TeacherAllInfoDTO teacherAllInfoDTO = BeanFactory.getBean("teacherAllInfoDTO",TeacherAllInfoDTO.class);
        TeacherAllInfoDTO teacherAllInfoDTO = new TeacherAllInfoDTO();
        teacherAllInfoDTO.clone(studentDTO, teacherDTO);
        return teacherAllInfoDTO;
    }

    /**
     * 保存用户信息，先存数据库，再存缓存
     * @param teacherDTO
     * @return
     */
    public TeacherDTO save(TeacherDTO teacherDTO){
        saveTeacherDAO.save(teacherDTO);
        userCAO.saveTeacherDTO( userCAO.getUserToken( teacherDTO.getId()),
                teacherDTO
        );
        return teacherDTO;
    }
}
