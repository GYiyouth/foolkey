package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_blacklist")
public class BlackListDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "blacked_user_id")
    private Long blackedUserId;


    @Override
    public String toString() {
        return "BlackListDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", blackedUserId=" + blackedUserId +
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

    public Long getBlackedUserId() {
        return blackedUserId;
    }

    public void setBlackedUserId(Long blackedUserId) {
        this.blackedUserId = blackedUserId;
    }
}
