package foolkey.aop.application;

import foolkey.pojo.root.bo.application.ApplicationInfoBO;
import foolkey.pojo.root.vo.dto.ApplicationStudentRewardDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 如果已经申请了，则不应再申请，交由AOP来做
 * 防止一个人多次大量申请同一个课程
 * Created by geyao on 2017/5/9.
 */
@Aspect
public class ApplicationMaintainAOP {

    @Autowired
    private ApplicationInfoBO applicationInfoBO;


    /**
     * 存储 悬赏 申请前，要看看是否已有了申请
     * @param joinPoint
     * @param applicationStudentRewardDTO
     * @return
     * @throws Throwable
     */
    @Around("execution(* foolkey.pojo.root.bo.application.ApplicationInfoBO.save(..)) &&" +
            " args( applicationStudentRewardDTO)")
    public Object preventMultipleApply(ProceedingJoinPoint joinPoint
            , ApplicationStudentRewardDTO applicationStudentRewardDTO
    ) throws Throwable{
        System.out.println("AOP " + this.getClass());
        List applicationList = applicationInfoBO.getRewardApplicationDTo(
                applicationStudentRewardDTO.getApplicantId()
                , applicationStudentRewardDTO.getStudentId()
                , applicationStudentRewardDTO.getCourseId()
        );
        if (applicationList == null || applicationList.size() == 0){
            // 当前没有申请
            System.out.println("还没有申请记录，可以正常申请");
            return joinPoint.proceed();
        }else {
            System.out.println("重复的申请记录");
            applicationStudentRewardDTO.setApplyTime( new Date() );
            applicationInfoBO.update(applicationStudentRewardDTO);
            return null;
        }
    }
}
