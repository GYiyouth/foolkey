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
import foolkey.pojo.root.vo.dto.EvaluationAbstract;
import foolkey.pojo.root.vo.dto.EvaluationCourseDTO;
import foolkey.pojo.root.vo.dto.EvaluationStudentDTO;
import foolkey.pojo.root.vo.dto.EvaluationTeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public void save(EvaluationCourseDTO evaluation){
        saveEvaluationCourseDAO.save(evaluation);
    }
    public void save(EvaluationStudentDTO evaluation){
        saveEvaluationStudentDAO.save(evaluation);
    }
    public void save(EvaluationTeacherDTO  evaluation){
        saveEvaluationTeacherDAO.save(evaluation);
    }
}
