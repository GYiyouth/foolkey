package foolkey.pojo.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_article")
public class ArticleDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "content")
    private String content;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "last_opera_time")
    private Date lastOperaTime;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "collections")
    private Integer collections;

    @Column(name = "dislikes")
    private Integer dislikes;

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", teacherId=" + teacherId +
                ", lastOperaTime=" + lastOperaTime +
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

    public Date getLastOperaTime() {
        return lastOperaTime;
    }

    public void setLastOperaTime(Date lastOperaTime) {
        this.lastOperaTime = lastOperaTime;
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
