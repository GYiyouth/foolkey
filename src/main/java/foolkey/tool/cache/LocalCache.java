package foolkey.tool.cache;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 出于性能考虑，所有操作不会判token空
 * Created by geyao on 2017/4/25.
 */
@Component("localCache")
public class LocalCache implements Cache{



    private static MemCachedClient memcachedClient = null;

    static {
        //管理中心，点击“NoSQL高速存储”，在NoSQL高速存储“管理视图”，可以看到系统分配的IP:Port
        //需要在内网IP上访问, 不需要账号密码
        final String ip = "10.66.238.185";
        final String port = "9101";

        String[] servers = {ip + ":" + port};

        //创建Socked连接池实例
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(servers);//设置连接池可用的cache服务器列表
        pool.setFailover(true);//设置容错开关
        pool.setInitConn(10);//设置开始时每个cache服务器的可用连接数
        pool.setMinConn(5);//设置每个服务器最少可用连接数
        pool.setMaxConn(250);//设置每个服务器最大可用连接数
        pool.setMaintSleep(30);//设置连接池维护线程的睡眠时间
        pool.setNagle(false);//设置是否使用Nagle算法，因为我们的通讯数据量通常都比较大（相对TCP控制数据）而且要求响应及时， <br> 因此该值需要设置为false（默认是true） </br>
        pool.setSocketTO(3000);//设置socket的读取等待超时值
        pool.setAliveCheck(true);//设置连接心跳监测开关
        pool.initialize();

        memcachedClient = new MemCachedClient();
        //测试用，刷新整个缓存区
        memcachedClient.flushAll();

        for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()){
            Map map = new HashedMap();
            Map mapReserve = new HashMap();
            memcachedClient.set(technicTagEnum.toString(), map);
            memcachedClient.set(technicTagEnum.toString()+"Reserve",mapReserve);
        }
        String[] keys = {"key", "id_token", "token_id"};
        for (String key : keys){
            memcachedClient.set(key, new HashedMap());
        }

    }



    @Override
    public boolean isContainToken(String token) {
        return memcachedClient.get(token) != null;
    }

    @Override
    public Map getMap(String token) {
        Map map = (Map) memcachedClient.get(token);
        return map;
    }

    @Override
    public void replaceToken(String oldToken, String newToken) {
        memcachedClient.set( newToken, getMap( oldToken) );
        memcachedClient.delete( oldToken );
//        cache1.put(newToken, getMap(oldToken));
//        cache1.remove(oldToken);
    }

    @Override
    public void remove(String token) {
        memcachedClient.delete(token);
    }

    @Override
    public MemCachedClient getCache() {
        return memcachedClient;
    }

    @Override
    public void put(String token, Map map) {
        memcachedClient.set(token, map);
//        System.out.println("缓存添加了" + token + " - " + map);
//        System.out.println(cache1.containsKey(token));
    }
}
