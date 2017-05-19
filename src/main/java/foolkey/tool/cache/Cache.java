package foolkey.tool.cache;

import com.alibaba.fastjson.JSON;
import com.danga.MemCached.MemCachedClient;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by geyao on 2017/4/25.
 */
@Component
public interface Cache {

    //不同级别key之间的分隔符
    public static final String separator = "_YY_";


    boolean isContainToken(String key);

    String getString(String key);



    void remove(String key);

    void set(String key, String value);

    void add(String key, String value);
}
