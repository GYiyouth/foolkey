package foolkey.aop.controllerTrace;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by geyao on 2017/4/29.
 */
@Aspect
public class RSALog {


    @AfterReturning(returning = "cipherText", pointcut = "execution(* foolkey.pojo.root.bo.security.RSAKeyBO.en*(..))")
    public void printEncrypt(String cipherText){
        System.out.println("AOP RSA加密后");
        System.out.println(cipherText);
    }


    @AfterReturning(returning = "clearText", pointcut = "execution(* foolkey.pojo.root.bo.security.RSAKeyBO.de*(..))")
    public void printDecrypt(String clearText){
        System.out.println("AOP RSA解密后");
        System.out.println(clearText);
    }

    
}
