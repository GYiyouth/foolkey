package foolkey.pojo.root.DAO.blacklist;

import foolkey.pojo.root.DAO.base.DeleteBaseDAO;
import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.dto.ArticleDTO;
import foolkey.pojo.root.vo.dto.BlackListDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getBlackListDAO")
public class GetBlackListDAO extends GetBaseDAO<BlackListDTO> {
}
