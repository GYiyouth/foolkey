package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.AbstractDTO;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 作废
 * Created by geyao on 2017/4/24.
 */
@Component
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MessageAbstract  extends AbstractDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "acceptor_id")
    private Long receiverId;

    public MessageAbstract() {
        super();
    }

    @Override
    public String toString() {
        return "MessageAbstract{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", receiverId=" + receiverId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
