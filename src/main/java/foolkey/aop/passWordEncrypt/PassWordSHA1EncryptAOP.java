package foolkey.aop.passWordEncrypt;

import foolkey.pojo.root.bo.security.SHA1KeyBO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by geyao on 2017/4/28.
 */
@Aspect
public class PassWordSHA1EncryptAOP {

    @Resource(name = "sha1KeyBO")
    private SHA1KeyBO sha1KeyBO;

    /**
     * 对用户的密码进行加密，实际上不需要了，因为我把它转移到了APP端生成
     * @param joinPoint
     * @return
     * @throws Throwable
     */
//    @Around("execution(* foolkey.pojo.root.bo.student.StudentInfoBO.getStudentDTO(..) )")
    public StudentDTO encrypt(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        String userName = args[0].toString();
        String passWord = args[1].toString();

        System.out.println("用户名：" + userName);
        System.out.println("密码：" + passWord);

        System.out.println("对密码进行SHA1加密");
        args[1] = sha1KeyBO.encrypt(passWord);
        System.out.println("密文：" + args[1]);
        return (StudentDTO) joinPoint.proceed(args);
    }
}
