package foolkey.aop.cacheMaintain;

import foolkey.pojo.root.CAO.key.KeyCAO;
import foolkey.pojo.root.vo.assistObject.RSAKeyDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.annotation.Resource;

/**
 * Created by geyao on 2017/4/26.
 */
@Aspect
public class KeyAOP {
    @Resource(name = "keyCAO")
    private KeyCAO keyCAO;

    /**
     * 用户在从数据库获取 RSAKeyDTO 以后，使缓存更新
     * @param rsaKeyDTO
     */
    @AfterReturning(returning = "rsaKeyDTO",
            pointcut = "execution(* foolkey.pojo.root.DAO.com.*.getServer*(..))"  )
    public void updateServerRSAKeyDTO(RSAKeyDTO rsaKeyDTO){
        if (rsaKeyDTO != null)
            keyCAO.updateServerRSAKeyDTO(rsaKeyDTO);
        System.out.println("AOP实现了服务器RSAKey缓存的更新!!");
    }

    @Around("execution(* foolkey.tool.security.AESCoder.getAESKeyBase64() ) ")
    public void updateUserAESKey(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        Object rvt = joinPoint.proceed(args);
        String userToken = args[0].toString();
        String aesBase64Key = rvt.toString();
        keyCAO.updateUserAESKey(aesBase64Key, userToken);

        System.out.println("AOP实现了用户AESKey缓存的更新");
    }
}
