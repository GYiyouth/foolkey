package foolkey.pojo.root.bo.pay_order;

import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 从账户扣款
 *
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class PayForOrderBO {

    public void pay(StudentDTO studentDTO, OrderBuyCourseDTO order){

    }
}
