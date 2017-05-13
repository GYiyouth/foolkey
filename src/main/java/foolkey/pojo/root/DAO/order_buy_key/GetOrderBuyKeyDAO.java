package foolkey.pojo.root.DAO.order_buy_key;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.dto.OrderBuyCourseDTO;
import foolkey.pojo.root.vo.dto.OrderBuyKeyDTO;
import foolkey.tool.BeanFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getOrderBuyKeyDAO")
public class GetOrderBuyKeyDAO extends GetBaseDAO<OrderBuyKeyDTO>{
}
