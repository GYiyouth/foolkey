package foolkey.pojo;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import foolkey.pojo.root.bo.register.UserRegisterBO;
import foolkey.pojo.root.bo.security.RSAKeyBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.send_to_client.TeacherAllInfoDTO;
import foolkey.tool.BeanFactory;
import foolkey.tool.ConverterB20X;
import foolkey.tool.ConverterByteBase64;
import foolkey.tool.security.RSACoder;
import foolkey.tool.security.StringMatchRate;
import net.sf.json.JSONObject;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by geyao on 2017/4/24.
 */
public class Test {

    Date date;



    public static void main(String[] args) throws Exception{

        TeacherAllInfoDTO teacherAllInfoDTO = new TeacherAllInfoDTO();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("学生");
        studentDTO.setBirthday( new Date( ));
        myClone( teacherAllInfoDTO, TeacherAllInfoDTO.class, studentDTO, StudentDTO.class );
        System.out.println( teacherAllInfoDTO.getName() );
        System.out.println( teacherAllInfoDTO.getBirthday() );
        studentDTO.getBirthday().setTime(123);
        studentDTO.setName( "学生2" );
        System.out.println( teacherAllInfoDTO.getBirthday() );
        System.out.println( teacherAllInfoDTO.getName() );

        Test test1 = new Test();
        test1.date = new Date();
        Test test2 = new Test();
        test2.date = test1.date;
        System.out.println(test2.date);
        test1.date.setTime(123);
        System.out.println(test2.date);
        System.out.println(test1.date);
    }

    public static void myClone(Object target, Class targetClass, Object source, Class sourceClass) throws Exception{
        Field[] fields = sourceClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                PropertyDescriptor sourceDescriptor = new PropertyDescriptor(field.getName(), sourceClass);
                PropertyDescriptor targetDescriptor = new PropertyDescriptor(field.getName(), targetClass);
                Method setMethod = targetDescriptor.getWriteMethod();
                Method getMethod = sourceDescriptor.getReadMethod();
                setMethod.invoke(target, getMethod.invoke(source));
            }catch (IntrospectionException exception){
                //源对象里有一个属性，目标对象里没有
            }

        }
    }


}
