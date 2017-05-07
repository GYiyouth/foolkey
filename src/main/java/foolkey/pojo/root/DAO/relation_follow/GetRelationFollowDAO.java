package foolkey.pojo.root.DAO.relation_follow;

import foolkey.pojo.root.DAO.base.GetBaseDAO;
import foolkey.pojo.root.vo.dto.RelationFollowDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getRelationFollowDAO")
@Transactional
public class GetRelationFollowDAO extends GetBaseDAO<RelationFollowDTO>{

    /**
     * 根据关注者与被关注者的id，获取到DTO
     * @param ownerId
     * @param followerId
     * @return
     */
    public RelationFollowDTO getRelationFollowDTO(Long ownerId,Long followerId){
        List<RelationFollowDTO> list = (List<RelationFollowDTO>) hibernateTemplate.find("select rf from RelationFollowDTO rf where rf.ownerId = ? and rf.followId = ?",ownerId, followerId);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
