package foolkey.pojo.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_collection_article")
public class CollectionArticleDTO extends CollectionAbstract{

    @Column(name = "article_id")
    private Long articleId;

    @Override
    public String toString() {
        return "CollectionArticleDTO{" +
                "articleId=" + articleId +
                '}';
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
