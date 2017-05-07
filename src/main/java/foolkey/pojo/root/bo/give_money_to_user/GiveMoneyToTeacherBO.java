package foolkey.pojo.root.bo.give_money_to_user;

import foolkey.pojo.root.DAO.transaction_user_earned_money.SaveTransactionUserEarnedMoneyDAO;
import foolkey.pojo.root.vo.assistObject.OrderTypeEnum;
import foolkey.pojo.root.vo.assistObject.RoleEnum;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TransactionUserEarnedMoneyDTO;
import foolkey.tool.constant_values.MoneyRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 老师上完课以后，给老师分钱
 * Created by geyao on 2017/5/7.
 */
@Service
@Transactional
public class GiveMoneyToTeacherBO {
    @Autowired
    private SaveTransactionUserEarnedMoneyDAO saveTransactionUserEarnedMoneyDAO;


    /**
     * 老师上课以后，给老师分钱，这里分钱，是优惠券之前的钱
     * 会修改老师现金，但不会保存
     * @param teacher
     * @param orderDTO
     * @return
     */
    public TransactionUserEarnedMoneyDTO giveMoneyToTeacher(
            StudentDTO teacher, OrderBuyCourseDTO orderDTO
    ){
        if (teacher.getRoleEnum().compareTo( RoleEnum.alreadyApplied) == 0){
            //对于非认证老师，就只有虚拟币增加
            Double virtual = orderDTO.getAmount() * MoneyRate.TEACHER_GET_MONEY_RATE;
            teacher.setVirtualCurrency( teacher.getVirtualCurrency() + virtual );
        }

        TransactionUserEarnedMoneyDTO transactionUserEarnedMoneyDTO = new TransactionUserEarnedMoneyDTO();
        transactionUserEarnedMoneyDTO.setAcceptUserId(teacher.getId());
        transactionUserEarnedMoneyDTO.setAmount( orderDTO.getAmount() );
        transactionUserEarnedMoneyDTO.setCreatedTime( new Date() );
        transactionUserEarnedMoneyDTO.setOrderId( orderDTO.getId() );
        switch (orderDTO.getCourseTypeEnum() ){
            case 学生悬赏:{
                transactionUserEarnedMoneyDTO.setOrderTypeEnum( OrderTypeEnum.studentCourse );
            }break;
            case 老师课程:{
                transactionUserEarnedMoneyDTO.setOrderTypeEnum( OrderTypeEnum.teacherCourse );
            }break;
        }

        saveTransactionUserEarnedMoneyDAO.save(transactionUserEarnedMoneyDTO);

        Double cash = orderDTO.getAmount() / MoneyRate.VIRTUAL_REAL_RATE * MoneyRate.TEACHER_GET_MONEY_RATE;

        teacher.setCash( teacher.getCash() + cash );

        return transactionUserEarnedMoneyDTO;
    }
}
