package foolkey.pojo;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import foolkey.pojo.root.bo.register.UserRegisterBO;
import foolkey.pojo.root.bo.security.RSAKeyBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.BeanFactory;
import foolkey.tool.ConverterB20X;
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



    public static void main(String[] args) throws Exception{

        String me = "IzMoTA2I35qvWYv/Qy21i9GM477eo93m8bN+vksZHoYac49rRTT4UeI8E5vFgLBxskI9nkZj+kGB愚r62BiBSFGvcnMj4yqLsyBPfitg2Ut9ldP2KAJeDG5Im9Nv+cbLqdDkUSGwTjSEGLQuW7+MJTxTy4愚V3bgImih2OKSRZYWKcU=愚";
        System.out.println(me.getBytes().length);
    }


}
