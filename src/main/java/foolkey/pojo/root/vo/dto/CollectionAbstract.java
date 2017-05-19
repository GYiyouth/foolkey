package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.AbstractDTO;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * 收藏
 * Created by admin on 2017/4/24.
 */
@Component
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CollectionAbstract extends AbstractDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //收藏人的id
    @Column(name = "user_id")
    private Long userId;

    //收藏的时间
    @Column(name = "collection_time")
    private Date collectionTime;


    @Override
    public String toString() {
        return "CollectionAbstract{" +
                "id=" + id +
                ", userId=" + userId +
                ", collectionTime=" + collectionTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }
}
