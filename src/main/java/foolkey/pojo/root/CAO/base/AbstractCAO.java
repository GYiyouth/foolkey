package foolkey.pojo.root.CAO.base;

import foolkey.tool.cache.Cache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by geyao on 2017/4/27.
 */
@Component
public abstract class AbstractCAO {

    protected static final String keyToken = "key";
    protected static final String userInfoToken = "studentDTO";
    protected static final String teacherInfoToken = "teacherDTO";
    protected static final String blackListToken = "blackList";
    protected static final String rsaKeyDTOToken = "rsaKeyDTO";
    protected static final String aesKeyToken = "aesKeyToken";
    protected static final String tokenToken = "token";
    protected static final String completePersonalProfileFlag = "profileFlag";

    //以下缓存群，在各个技术标签下均有
    protected static final String coursePopularToken = "coursePopular";
    protected static final String rewardPopularToken = "rewardPopular";
    protected static final String articleToken = "article";
    protected static final String question = "question";

    protected static final String studentId_token = "id_token";
    protected static final String studentToken_id = "token_id";

    @Resource(name = "localCache")
    protected Cache cache;
}
