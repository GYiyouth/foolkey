package foolkey.pojo.root.bo.RelationFollow;

import foolkey.pojo.root.DAO.relation_follow.GetRelationFollowDAO;
import foolkey.pojo.root.vo.dto.RelationFollowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ustcg on 2017/5/7.
 */
@Service("relationFollowBO")
public class RelationFollowBO {

    @Autowired
    private RelationFollowDTO relationFollowDTO;
    @Autowired
    private GetRelationFollowDAO getRelationFollowDAO;


    /**
     * 查看是不是已关注
     * @param ownerId
     * @param followerId
     * @return
     * @throws Exception
     */
    public boolean isFollower(Long ownerId,Long followerId) throws Exception{
        if(ownerId == null || followerId == null){
            throw new NullPointerException("id is null");
        }else{
            if(getRelationFollowDAO.getRelationFollowDTO(ownerId,followerId)==null)
                return false;
            else
                return true;
        }
    }
}
