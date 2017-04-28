package foolkey.aop.cacheMaintain;

import foolkey.pojo.root.CAO.userInfo.UserCAO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.annotation.Resource;

/**
 * 用来保证UserCAO的健壮性
 * Created by geyao on 2017/4/28.
 */
@Aspect
public class UserCAOConfirmAOP {
    @Resource(name = "userCAO")
    private UserCAO userCAO;

    /**
     * 向用户缓存区 放置StudentDTO前，要保证 该缓存区存在
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* foolkey.pojo.root.CAO.userInfo.UserCAO.saveStudentDTO(..))")
    public Object confirmSaveStudentDTO(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        String token = args[0].toString();
        if (!userCAO.containsUser(token)){
            //如果该缓存区不存在
            userCAO.initUserCache(token);
            System.out.println("AOP增强了UserCAO的健壮性 - " + this.getClass());
        }
        return joinPoint.proceed();

    }
}
