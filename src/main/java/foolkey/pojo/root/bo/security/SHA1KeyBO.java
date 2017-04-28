package foolkey.pojo.root.bo.security;

import foolkey.tool.security.SHA1Coder;
import org.springframework.stereotype.Service;

/**
 * Created by geyao on 2017/4/28.
 */
@Service(value = "sha1KeyBO")
public class SHA1KeyBO {

    public String encrypt(String raw){
        return SHA1Coder.SHA1(raw);
    }
}
