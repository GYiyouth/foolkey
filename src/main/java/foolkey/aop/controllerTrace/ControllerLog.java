package foolkey.aop.controllerTrace;

import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 当一个controller执行时，在控制台输出它
 * Created by geyao on 2017/4/28.
 */
@Aspect
public class ControllerLog {

    //对于controller类无效
//    @Around("execution(* foolkey.controller..*.execute(..))")
    public Object classLog(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        String method = joinPoint.getSignature().getName();
        String clazzName = joinPoint.getTarget().getClass().getName();
        String kind = joinPoint.getKind();
        System.out.println("执行对象：" + clazzName);
        System.out.println("执行方法：" + method);
        System.out.println("执行种类" + kind);
        Object rvt = joinPoint.proceed();
        System.out.println("执行结果：");
        System.out.println(rvt);
        return rvt;
    }

    /**
     * 对于每次发送JSON，都打印出来
     * @param arg0
     */
    @Before(value = "execution(* foolkey.tool.JSONHandler.sendJSON(..)) && args(arg0, ..)")
    public void JSONHandlerLog(JSONObject arg0){
        System.out.println("AOP返回的json值是");
        System.out.println(arg0.toString());
    }

    //似乎对于类方法无效
    @Before(value = "execution(* foolkey.tool.JSONHandler.sendFailJSON(..))")
    public void failJSONHandlerLog(JoinPoint joinPoint){
        System.out.println("AOP拦截器未通过");
        System.out.println(joinPoint.getTarget().getClass().getName());
    }
}
