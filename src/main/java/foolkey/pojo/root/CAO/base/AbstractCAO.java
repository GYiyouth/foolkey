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

    //以下缓存群，在各个技术标签下均有
    protected static final String courseTeacherToken = "courseTeacherToken";
    protected static final String courseStudentToken = "courseStudentToken";
    protected static final String articleToken = "articleToken";
    protected static final String question = "question";

    protected static final String studentId_token = "id_token";
    protected static final String studentToken_id = "token_id";

    @Resource(name = "localCache")
    protected Cache cache;
}
