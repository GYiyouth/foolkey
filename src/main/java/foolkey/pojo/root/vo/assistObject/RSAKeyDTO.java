package foolkey.pojo.root.vo.assistObject;

import foolkey.tool.security.RSACoder;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by geyao on 2017/4/26.
 */
@Component("rsaKeyDTO")
@Entity
@Table(name = "rsakey")
public class RSAKeyDTO {
    @Id
    @Column(name = "id", length = 64)
    private Long id;
    @Column(name = "public_base64_String", length = 2000)
    private String pubBase64Str ;
    @Column(name = "private_base64_String", length = 2000)
    private String priBase64Str;

    @Column(name = "user_id", length = 64)
    private Long userId;

    public RSAKeyDTO() {
        super();
    }

    @Override
    public String toString() {
        return "RSAKeyDTO{" +
                "id=" + id +
                ", pubBase64Str='" + pubBase64Str + '\'' +
                ", priBase64Str='" + priBase64Str + '\'' +
                ", userId=" + userId +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPubBase64Str() {
        return pubBase64Str;
    }

    public void setPubBase64Str(String pubBase64Str) {
        this.pubBase64Str = pubBase64Str;
    }

    public String getPriBase64Str() {
        return priBase64Str;
    }

    public void setPriBase64Str(String priBase64Str) {
        this.priBase64Str = priBase64Str;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
