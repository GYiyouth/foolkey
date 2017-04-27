package foolkey.pojo.root.CAO.userInfo;

import foolkey.pojo.root.CAO.base.AbstractCAO;
import foolkey.tool.cache.Cache;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by geyao on 2017/4/27.
 */
@Component(value = "userCAO")
public class UserCAO extends AbstractCAO{


    /**
     * 在缓存中开辟一个新的用户缓存区
     * @param token
     */
    public void saveUserInfo(String token){
        Map userMap = new HashedMap();
        Map keyMap = new HashedMap();
        Map blackListMap = new HashedMap();

        userMap.put(keyToken, keyMap);
        userMap.put(blackListToken, blackListMap);

        cache.put(token, userMap);
    }

    /**
     * 判断缓存中有无该用户的缓存
     * @param token
     * @return
     */
    public boolean containsUser(String token){
        return cache.isContainToken(token);
    }

    /**
     * 从缓存中获取用户缓存区
     * @param token
     * @return
     */
    public Map getUserMap(String token){
        return cache.getMap(token);
    }
}
