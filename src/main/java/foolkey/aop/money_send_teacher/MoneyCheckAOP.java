package foolkey.aop.money_send_teacher;

import foolkey.pojo.root.bo.transaction.TransactionInfoBO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by GR on 2017/5/13.
 */
@Aspect
public class MoneyCheckAOP {
    @Autowired
    private TransactionInfoBO transactionInfoBO;

    /**
     * 评价老师时会转账，此时要判定，如果就此账单已经转过一次账了，则不能再转账
     * @param joinPoint
     * @return
     */
    @Around(value = "execution(* foolkey.pojo.root.bo.give_money_to_user.GiveMoneyToTeacherBO.giveMoneyToTeacher(..))" )
    public Object preventSendMoneyTwice(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[]args = joinPoint.getArgs();
        StudentDTO teacher = (StudentDTO) args[0];
        OrderBuyCourseDTO orderDTO = (OrderBuyCourseDTO) args[1];
        if (transactionInfoBO.isTeacherEarndMoney(teacher, orderDTO)){
            //已经转过账了
            return null;
        }else {
            return joinPoint.proceed();
        }
    }
}
