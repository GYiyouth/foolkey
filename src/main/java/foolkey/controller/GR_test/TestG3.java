package foolkey.controller.GR_test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.schooner.MemCached.MemcachedItem;
import foolkey.pojo.root.vo.assistObject.CourseTypeEnum;
import foolkey.pojo.root.vo.assistObject.OrderStateEnum;
import foolkey.pojo.root.vo.assistObject.SchoolEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.Time;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;
import sun.applet.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.*;
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

//        private static MemCachedClient memcachedClient = null;

//        static {
//            //管理中心，点击“NoSQL高速存储”，在NoSQL高速存储“管理视图”，可以看到系统分配的IP:Port
//            //需要在内网IP上访问, 不需要账号密码
//            final String ip = "127.0.0.1";
//            final String port = "11211";
//
//            String[] servers = {ip + ":" + port};
//
//            //创建Socked连接池实例
//            SockIOPool pool = SockIOPool.getInstance();
//            pool.setServers(servers);//设置连接池可用的cache服务器列表
//            pool.setFailover(true);//设置容错开关
//            pool.setInitConn(10);//设置开始时每个cache服务器的可用连接数
//            pool.setMinConn(5);//设置每个服务器最少可用连接数
//            pool.setMaxConn(250);//设置每个服务器最大可用连接数
//            pool.setMaintSleep(30);//设置连接池维护线程的睡眠时间
//            pool.setNagle(false);//设置是否使用Nagle算法，因为我们的通讯数据量通常都比较大（相对TCP控制数据）而且要求响应及时， <br> 因此该值需要设置为false（默认是true） </br>
//            pool.setSocketTO(3000);//设置socket的读取等待超时值
//            pool.setAliveCheck(true);//设置连接心跳监测开关
//            pool.initialize();
//
//            memcachedClient = new MemCachedClient();
//        测试用，刷新整个缓存区
//            memcachedClient.flushAll();


//        }

    public static void main(String[] args) throws Exception {

        String school = "蓝翔技校";
        SchoolEnum schoolEnum;
        try{
            schoolEnum = SchoolEnum.valueOf(school);
        }catch(IllegalArgumentException e){
            schoolEnum = SchoolEnum.其他;
        }
        System.out.println(schoolEnum);


//        String str = "1993-02-18 23:34:21";
//        Date date = Time.fromStringToDate(str);
//        System.out.println(date);
//        OrderStateEnum[] orderStateEnums = {OrderStateEnum.上课中,OrderStateEnum.取消课程,OrderStateEnum.同意上课};
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("orderStateEnums",orderStateEnums);
//        System.out.println(jsonObject);
//
//
//        Object orderStateEnums1 = jsonObject.get("orderStateEnums");
//        System.out.println(orderStateEnums1);
//        String os = JSONObject.par("orderStateEnums");



//        File file = new File("D:\\A\\B\\e.txt");
//        if (file.exists()) {
//            System.out.println("文件存在");
//        } else {
//            file.createNewFile();
//            FileOutputStream fop =  new FileOutputStream(file);
//            PrintWriter pw = new PrintWriter(fop);
//            pw.write("12212asdasdadsfasdfasd");
//            pw.flush();
//        }
//        if (file.isDirectory()) {
//            System.out.println("文件夹？");
//        }

//        List<String> list = new ArrayList<>();
//        list.add("test1");
//        list.add("test2");
//        list.add("test3");
//        memcachedClient.set("test",JSON.toJSONString(list));
//        String result = memcachedClient.get("test").toString();
//        System.out.println(result);
    }

}
