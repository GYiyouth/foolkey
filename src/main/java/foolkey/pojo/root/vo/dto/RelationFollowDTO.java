package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.AbstractDTO;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 关注的关系表
 * Created by geyao on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_relation_follow")
public class RelationFollowDTO  extends AbstractDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //被专注者id
    @Column(name = "owner_id")
    private Long ownerId;

    //关注者id
    @Column(name = "follower_id")
    private Long followId;

    public RelationFollowDTO() {
        super();
    }

    @Override
    public String toString() {
        return "RelationFollowDTO{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", followId=" + followId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }
}
