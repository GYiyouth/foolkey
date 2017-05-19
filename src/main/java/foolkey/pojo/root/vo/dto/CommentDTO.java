package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.AbstractDTO;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * 评论，暂时不做
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_comment")
public class CommentDTO extends AbstractDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //评论人的id
    @Column(name = "creator_id")
    private Long creatorId;

    //评论文章的id
    @Column(name = "article_id")
    private Long articleId;

    //评论的是哪条评论
    @Column(name = "comment_id")
    private Long commentId;

    //评论时间
    @Column(name = "comment_time")
    private Date commentTime;

    //评论的内容
    @Column(name = "content")
    private String content;

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", articleId=" + articleId +
                ", commentId=" + commentId +
                ", commentTime=" + commentTime +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
