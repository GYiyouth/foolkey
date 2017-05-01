package foolkey.pojo.root.bo.message;

import foolkey.pojo.root.DAO.message_order.SaveMessageOrderDAO;
import foolkey.pojo.root.vo.dto.MessageOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by geyao on 2017/5/1.
 */
@Service @Transactional
public class MessageOrderBO {

    @Autowired
    private SaveMessageOrderDAO saveDAO;

    /**
     * 生成订单消息，主要是用于申请
     * @return
     */
    public MessageOrderDTO saveOrderMessage(
            Long acceptorId,
            Long orderId
    ){
        MessageOrderDTO message = new MessageOrderDTO();
        message.setReceiverId(acceptorId);
        message.setOrderId(orderId);
        message.setTitle("有人申请了你的课程");
        message.setContent("");
        saveDAO.save(message);
        return message;
    }
}
