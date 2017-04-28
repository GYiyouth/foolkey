package foolkey.pojo.root.bo.logIn_logOut;

import foolkey.pojo.root.CAO.userInfo.UserCAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户登出
 * Created by geyao on 2017/4/28.
 */
@Service(value = "logOutBO")
public class LogOutBO{
    @Resource(name = "userCAO")
    private UserCAO userCAO;

    /**
     * 消除用户缓存
     * @param token
     */
    public void removeUserCache(String token){
        if (userCAO.containsUser(token))
            userCAO.removeUserCache(token);
    }
}
