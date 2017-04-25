package foolkey.pojo.root.DAO.message_feedback;

import foolkey.pojo.root.DAO.base.DeleteBaseDAO;
import foolkey.pojo.root.DAO.base.SaveBaseDAO;
import foolkey.pojo.root.vo.dto.MessageFeedbackDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("saveMessageFeedbackDAO")
public class SaveMessageFeedbackDAO extends SaveBaseDAO<MessageFeedbackDTO> {
}
