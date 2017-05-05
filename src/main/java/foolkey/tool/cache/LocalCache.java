package foolkey.tool.cache;

import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 出于性能考虑，所有操作不会判token空
 * Created by geyao on 2017/4/25.
 */
@Component("localCache")
public class LocalCache implements Cache{

    private static HashMap<String, Map> cache1 = new HashMap<>();
    static {
        for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()){
            Map map = new HashedMap();
            //一个备用的，主要目的是存储一些可能进入热门的课程
            Map mapReserve = new HashMap();
            cache1.put(technicTagEnum.toString(), map);
            cache1.put(technicTagEnum.toString()+"Reserve",mapReserve);
        }
        String[] keys = {"key", "id_token", "token_id"};
        for (String key : keys){
            cache1.put(key, new HashedMap());
        }
    }


    @Override
    public boolean isContainToken(String token) {
        return cache1.containsKey(token);
    }

    @Override
    public Map getMap(String token) {
        Map map = cache1.get(token);
        return map;
    }

    @Override
    public void replaceToken(String oldToken, String newToken) {
        cache1.put(newToken, getMap(oldToken));
        cache1.remove(oldToken);
    }

    @Override
    public void remove(String token) {
        cache1.remove(token);
    }

    @Override
    public Map getCache() {
        return cache1;
    }

    @Override
    public void put(String token, Map map) {
        cache1.put(token, map);
        System.out.println("缓存添加了" + token + " - " + map);
        System.out.println(cache1.containsKey(token));
    }
}
