package foolkey.pojo;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import foolkey.pojo.root.bo.register.UserRegisterBO;
import foolkey.pojo.root.bo.security.RSAKeyBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.BeanFactory;
import foolkey.tool.ConverterByteBase64;
import foolkey.tool.security.RSACoder;
import foolkey.tool.security.StringMatchRate;
import net.sf.json.JSONObject;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by geyao on 2017/4/24.
 */
public class Test {



    public static String foo() throws Exception{

        //管理中心，点击“NoSQL高速存储”，在NoSQL高速存储“管理视图”，可以看到系统分配的IP:Port
        //需要在内网IP上访问, 不需要账号密码
        final String ip = "10.66.238.185";
        final String port = "9101";

        String message = new String("start test qcloud cmem - " + ip + ":" + port);

        MemCachedClient memcachedClient = null;

        try{
            //设置缓存服务器列表，当使用分布式缓存的时，可以指定多个缓存服务器
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

            //将数据存入缓存
            memcachedClient.set("cmem", "qcloud cmem service");

            //将数据存入缓存,并设置失效时间
            Date date = new Date(1000);
            memcachedClient.set("test_expire", "test_expire_value", date);

            //获取缓存数据
            message += ("get: cmem = " + memcachedClient.get("cmem"));
            message += ("get: test_expire = " + memcachedClient.get("test_expire"));

            //向cmem存入多个数据
            for(int i = 0; i < 100; i++){
                String key = "key-" + i;
                String value = "value-" + i;
                memcachedClient.set(key, value);
            }

            message += ("set 操作完成");
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (memcachedClient != null) {
            memcachedClient = null;
        }
        return message;
    }


}
