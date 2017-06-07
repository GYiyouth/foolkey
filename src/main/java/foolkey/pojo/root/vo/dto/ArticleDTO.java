package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.AbstractDTO;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * 文章，暂时不做
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_article")
public class ArticleDTO extends AbstractDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //标题
    @Column(name = "topic")
    private String topic;

    //内容
    @Column(name = "content")
    private String content;

    //老师id
    @Column(name = "teacher_id")
    private Long teacherId;

    //最后操作时间
    @Column(name = "last_opera_time")
    private Date lastOperateTime;

    //点赞数
    @Column(name = "likes")
    private Integer likes;

    //收集数
    @Column(name = "collections")
    private Integer collections;

    //踩数
    @Column(name = "dislikes")
    private Integer dislikes;

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", teacherId=" + teacherId +
                ", lastOperaTime=" + lastOperateTime +
                ", likes=" + likes +
                ", collections=" + collections +
                ", dislikes=" + dislikes +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long techerId) {
        this.teacherId = techerId;
    }

    public Date getLastOperateTime() {
        return lastOperateTime;
    }

    public void setLastOperateTime(Date lastOperaTime) {
        this.lastOperateTime = lastOperaTime;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getCollections() {
        return collections;
    }

    public void setCollections(Integer collections) {
        this.collections = collections;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }
}
