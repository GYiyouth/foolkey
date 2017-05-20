package foolkey.aop.cacheMaintain;

import foolkey.pojo.root.CAO.Reward.RewardCAO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.assistObject.DirectionEnum;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.reward.RewardWithStudentSTCDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ustcg on 2017/5/6.
 */
@Aspect
public class CourseStudentAOP {


    @Autowired
    private RewardCAO rewardCAO;
    @Autowired
    private StudentInfoBO studentInfoBO;


    /**
     * 更新悬赏之后对缓存更新
     * @param jp
     * @param rewardDTO
     */
    @AfterReturning(returning = "rewardDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_student.*.update(..))"  )
    public void updateCourseStudentDTO(JoinPoint jp, RewardDTO rewardDTO){
        if(rewardDTO != null){
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(rewardDTO.getCreatorId());
            RewardWithStudentSTCDTO rewardWithStudentSTCDTO = new RewardWithStudentSTCDTO();
            rewardWithStudentSTCDTO.setStudentDTO(studentDTO);
            rewardWithStudentSTCDTO.setRewardDTO(rewardDTO);
            rewardCAO.updateRewardWithStudent(rewardDTO.getTechnicTagEnum(), rewardWithStudentSTCDTO);
        }

        System.out.println("更新悬赏之后，完成对缓存的更新");
    }

    /**
     * 创建悬赏
     * @param rewardDTO
     */
    @AfterReturning(returning = "rewardDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.course_student.*.save(..))")
    public void addCourseTeacherDTOToCache(RewardDTO rewardDTO){
        if(rewardDTO != null){
//            courseTeacherCAO.addNewCourseTeacherToCache(courseTeacherDTO);
            StudentDTO studentDTO = studentInfoBO.getStudentDTO(rewardDTO.getCreatorId());
            RewardWithStudentSTCDTO rewardWithStudentSTCDTO = new RewardWithStudentSTCDTO();
            rewardWithStudentSTCDTO.setRewardDTO(rewardDTO);
            rewardWithStudentSTCDTO.setStudentDTO(studentDTO);
            rewardCAO.addRewardWithStudentToCache(rewardDTO.getTechnicTagEnum(), rewardWithStudentSTCDTO, DirectionEnum.head);
        }
        System.out.println("添加悬赏之后，完成对缓存的更新");
    }


}
