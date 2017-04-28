package foolkey.aop.cacheMaintain;

import foolkey.pojo.root.CAO.userInfo.UserCAO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.annotation.Resource;

/**
 * Created by geyao on 2017/4/28.
 */
@Aspect
public class StudentDTOAOP {
    @Resource(name = "userCAO")
    private UserCAO userCAO;

    /**
     * 从数据库获取 用户信息 以后，使缓存更新
     * @param joinPoint
     */
    @Around(value = "execution(* foolkey.pojo.root.DAO.student.GetStudentDAO.getStudentDTO(..))")
    public Object updateStudentDTO(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        System.out.println(this.getClass());
        System.out.println("用户名：" + args[0]);
        System.out.println("密码：" + args[1]);
        String token = args[0].toString() + args[1].toString();
        Object rvt = joinPoint.proceed();
        if (rvt != null) {
            userCAO.saveStudentDTO(token, (StudentDTO) rvt);
            System.out.println("AOP实现了服务器 " + token + " \n缓存的更新!!");
        }else {
            System.out.println("AOP未实现用户StudentDTO更新，因为不存在该用户");
        }
        return rvt;
    }


}
