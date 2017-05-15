package foolkey.tool.cache;

import com.danga.MemCached.MemCachedClient;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by geyao on 2017/4/25.
 */
@Component
public interface Cache {

    MemCachedClient getCache();

    boolean isContainToken(String token);

    Map getMap(String token);

    void replaceToken(String oldToken, String newToken);

    void remove(String token);

    void put(String token, Map map);
}
