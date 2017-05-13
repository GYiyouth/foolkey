package foolkey.pojo.root.bo.transaction;

import foolkey.pojo.root.DAO.transaction_user_earned_money.GetTransactionUserEarnedMoneyDAO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GR on 2017/5/13.
 */
@Service
public class TransactionInfoBO {
    @Autowired
    private GetTransactionUserEarnedMoneyDAO getTransactionUserEarnedMoneyDAO;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 判断用户是否根据在某个订单里已经获取了钱
     * @param teacher
     * @param orderDTO
     * @return
     */
    public boolean isTeacherEarndMoney(StudentDTO teacher, OrderBuyCourseDTO orderDTO){
        String hql = "from TransactionUserEarnedMoneyDTO t where t.acceptUserId = ? and t.orderId = ?";
//        hibernateTemplate.find(hql);
        List list;
        list = getTransactionUserEarnedMoneyDAO.find( hql, teacher.getId(), orderDTO.getId());
        if (list.size() > 0)
            return true;
        else
            return false;
    }
}
