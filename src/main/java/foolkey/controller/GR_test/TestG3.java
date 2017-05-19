package foolkey.controller.GR_test;

import com.alibaba.fastjson.JSON;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.schooner.MemCached.MemcachedItem;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.Time;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;
import sun.applet.Main;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Future;
//import net.spy.memcached.MemcachedClient;

/**
 * Created by GR on 2017/5/3.
 */
@Component
public class TestG3 {

//        System.out.println(Time.getDate());
//        StudentDTO studentDTO = new StudentDTO();
//        StudentDTO aimStudentDTO = new StudentDTO();
//        studentDTO.setPassWord("1221");
//        studentDTO.myClone(aimStudentDTO,studentDTO);
//        System.out.println(aimStudentDTO.getPassWord()+"?");
        //类别为空，则随机选取一个类别
//        if(studentDTO.getTechnicTagEnum() == null){
//            Random random = new Random();
//            Integer technicSize = TechnicTagEnum.values().length;
//            System.out.println("技术类别中的数量:"+technicSize);
//
//            Integer temp = random.nextInt(technicSize);
//            TechnicTagEnum technicTagEnum = TechnicTagEnum.values()[temp];
//        System.out.println(technicTagEnum);
//        }

//        TestG3 testG3 = new TestG3();
//        testG3.show(4,5,6,7,8,9);

        private static MemCachedClient memcachedClient = null;

        static {
            //管理中心，点击“NoSQL高速存储”，在NoSQL高速存储“管理视图”，可以看到系统分配的IP:Port
            //需要在内网IP上访问, 不需要账号密码
            final String ip = "127.0.0.1";
            final String port = "11211";

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
//        测试用，刷新整个缓存区
//            memcachedClient.flushAll();



        }

    public static void main(String[] args) {
//        boolean fo =  memcachedClient.set("runoob", "Free Education");
//        System.out.println(memcachedClient.get("runoob"));
//
//        StudentDTO studentDTO = new StudentDTO();
//        studentDTO.setName("ge");
//        studentDTO.setBirthday(new Date());

//        boolean fo =  memcachedClient.set("object", "Free Education");
//        MemcachedItem memcachedItem = memcachedClient.gets("object");
//        long token = memcachedItem.getCasUnique();
//        System.out.println("token" + token);
//
//        memcachedClient.set("object","gegegeg");
//        System.out.println("更了一下" + memcachedClient.get("object"));
//
//        System.out.println( memcachedItem.getValue().toString() );
//
//        System.out.println( memcachedClient.cas("object", "hahaha",token) );
////        System.out.println(memcachedClient.gets("runoob").toString());
//
//        System.out.println("cas存储之后的" + memcachedClient.get("object"));
////        System.out.println(memcachedItem.getCasUnique());

        for(int j = 0 ; j < 10 ; j++) {
            Long beginTime1 = System.currentTimeMillis();
            //get set 修改10_000
            for (int i = 0; i < 10_000; i++) {
                String temp = i + "";
                memcachedClient.set(temp, i);
                memcachedClient.get(temp);
            }
            Long endTime1 = System.currentTimeMillis();
            System.out.println("set时间：" + (endTime1 - beginTime1));


            Long beginTime2 = System.currentTimeMillis();
            // cas 修改
            for (int i = 0; i < 10_000; i++) {
                String temp = i + "";
                memcachedClient.cas(temp, i + 1, memcachedClient.gets(temp).getCasUnique());

            }
            Long endTime2 = System.currentTimeMillis();
            System.out.println("cas时间：" + (endTime2 - beginTime2));


            beginTime2 = System.currentTimeMillis();
            // cas 修改
            for (int i = 0; i < 10_000; i++) {
                String temp = i + "";
                long t = memcachedClient.gets(temp).getCasUnique();
                memcachedClient.cas(temp, i + 2, t);

            }
            endTime2 = System.currentTimeMillis();
            System.out.println("cas分开时间：" + (endTime2 - beginTime2));
        }






//        Long time1 = System.currentTimeMillis();
//        for ( int i = 0; i < 10_000; i ++ ) {
//            String studentDTOStr =  memcachedClient.get("object").toString();
//            StudentDTO studentDTO1 = JSON.parseObject(studentDTOStr, StudentDTO.class);
//            System.out.println(studentDTO1); 8658
//        }
//        Long time2 = System.currentTimeMillis();
//        System.out.println(time2 - time1);
    }

}
