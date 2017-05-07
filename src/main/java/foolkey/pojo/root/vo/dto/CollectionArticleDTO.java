package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 收藏的文章，暂时不做
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
