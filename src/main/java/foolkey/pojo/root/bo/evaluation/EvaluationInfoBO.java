package foolkey.pojo.root.bo.evaluation;

import foolkey.handler.course.judge.EvaluateTeacherHandler;
import foolkey.pojo.root.DAO.evaluation_course.DeleteEvaluationCourseDAO;
import foolkey.pojo.root.DAO.evaluation_course.GetEvaluationCourseDAO;
import foolkey.pojo.root.DAO.evaluation_course.SaveEvaluationCourseDAO;
import foolkey.pojo.root.DAO.evaluation_course.UpdateEvaluationCourseDAO;
import foolkey.pojo.root.DAO.evaluation_student.DeleteEvaluationStudentDAO;
import foolkey.pojo.root.DAO.evaluation_student.GetEvaluationStudentDAO;
import foolkey.pojo.root.DAO.evaluation_student.SaveEvaluationStudentDAO;
import foolkey.pojo.root.DAO.evaluation_student.UpdateEvaluationStudentDAO;
import foolkey.pojo.root.DAO.evaluation_teacher.DeleteEvaluationTeacherDAO;
import foolkey.pojo.root.DAO.evaluation_teacher.GetEvaluationTeacherDAO;
import foolkey.pojo.root.DAO.evaluation_teacher.SaveEvaluationTeacherDAO;
import foolkey.pojo.root.DAO.evaluation_teacher.UpdateEvaluationTeacherDAO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.*;
import foolkey.pojo.send_to_client.EvaluationCourseSTCDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geyao on 2017/5/6.
 */
@Service
public class EvaluationInfoBO {
    @Autowired
    private GetEvaluationCourseDAO getEvaluationCourseDAO;
    @Autowired
    private GetEvaluationStudentDAO getEvaluationStudentDAO;
    @Autowired
    private GetEvaluationTeacherDAO getEvaluationTeacherDAO;

    @Autowired
    private SaveEvaluationCourseDAO saveEvaluationCourseDAO;
    @Autowired
    private SaveEvaluationStudentDAO saveEvaluationStudentDAO;
    @Autowired
    private SaveEvaluationTeacherDAO saveEvaluationTeacherDAO;

    @Autowired
    private UpdateEvaluationCourseDAO updateEvaluationCourseDAO;
    @Autowired
    private UpdateEvaluationStudentDAO updateEvaluationStudentDAO;
    @Autowired
    private UpdateEvaluationTeacherDAO updateEvaluationTeacherDAO;

    @Autowired
    private DeleteEvaluationCourseDAO deleteEvaluationCourseDAO;
    @Autowired
    private DeleteEvaluationStudentDAO deleteEvaluationStudentDAO;
    @Autowired
    private DeleteEvaluationTeacherDAO deleteEvaluationTeacherDAO;

    @Autowired
    private StudentInfoBO studentInfoBO;


    public void save(EvaluationCourseDTO evaluation){
        saveEvaluationCourseDAO.save(evaluation);
    }
    public void save(EvaluationStudentDTO evaluation){
        saveEvaluationStudentDAO.save(evaluation);
    }
    public void save(EvaluationTeacherDTO  evaluation){
        saveEvaluationTeacherDAO.save(evaluation);
    }

    public EvaluationCourseDTO getEvaluationCourseDTO(Long id){
        return getEvaluationCourseDAO.get(EvaluationCourseDTO.class, id);
    }

    public EvaluationStudentDTO getEvaluationStudentDTO(Long id){
        return getEvaluationStudentDAO.get(EvaluationStudentDTO.class, id);
    }

    public EvaluationTeacherDTO getEvaluationTeacherDTO(Long id){
        return getEvaluationTeacherDAO.get(EvaluationTeacherDTO.class, id);
    }


    /**
     * 分页显示某门课的评论
     * @param courseTeacherId
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ArrayList<EvaluationCourseDTO> getEvaluationCourseDTOByCourseTeacherId(Long courseTeacherId, Integer pageNo, Integer pageSize) throws Exception{
        String hql = "select ec from EvaluationCourseDTO ec where ec.courseId = ?";
        return getEvaluationCourseDAO.findByPage(hql,pageNo,pageSize,courseTeacherId);
    }

    /**
     * 分页显示某个老师的评论
     * @param teacherId
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ArrayList<EvaluationTeacherDTO> getEvaluationTeacherDTOByTeacherId(Long teacherId, Integer pageNo, Integer pageSize) throws Exception{
        String hql = "select et from EvaluationTeacherDTO et where et.acceptor_id = ?";
        return getEvaluationTeacherDAO.findByPage(hql,pageNo,pageSize,teacherId);
    }

    /**
     * 分页显示对这个学生的评论
     * @param studentId
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ArrayList<EvaluationStudentDTO> getEvaluationStudentDTOByStudentId(Long studentId, Integer pageNo, Integer pageSize) throws Exception{
        String hql = "select es from EvaluationStudentDTO es where es.acceptor_id = ?";
        return getEvaluationStudentDAO.findByPage(hql,pageNo,pageSize,studentId);
    }


    /**
     * 把评价信息封装为评价-评价人DTO
     * @param evaluationCourseDTOS
     * @return
     * @throws Exception
     */
    public ArrayList<EvaluationCourseSTCDTO> convertEvaluationCourseDTOIntoEvaluationCourseSTCDTO(List<EvaluationCourseDTO> evaluationCourseDTOS) throws Exception{
        ArrayList<EvaluationCourseSTCDTO> evaluationCourseSTCDTOS = new ArrayList<>();
        for(EvaluationCourseDTO evaluationCourseDTO:evaluationCourseDTOS){
            EvaluationCourseSTCDTO evaluationCourseSTCDTO = new EvaluationCourseSTCDTO();
            //获取评价人的信息
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(evaluationCourseDTO.getCreatorId());
            evaluationCourseSTCDTO.setStudentDTO(studentDTO);
            evaluationCourseSTCDTO.setEvaluationCourseDTO(evaluationCourseDTO);
            evaluationCourseSTCDTOS.add(evaluationCourseSTCDTO);
        }
        return evaluationCourseSTCDTOS;
    }
}
