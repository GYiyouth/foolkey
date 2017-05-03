package foolkey.pojo;

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

/**
 * Created by geyao on 2017/4/24.
 */
public class Test {



    public static void main(String[] args) throws Exception{

        StudentDTO studentDTO = new StudentDTO();
        StudentDTO studentDTO1 = new StudentDTO();

        studentDTO.setId(1L);


        Field[] fields1 = studentDTO.getClass().getDeclaredFields();

        PropertyDescriptor descriptor = new PropertyDescriptor(fields1[0].getName(), studentDTO1.getClass());
        Method setMethod = descriptor.getWriteMethod();
        Method getMethod = descriptor.getReadMethod();
        setMethod.invoke(studentDTO1, getMethod.invoke(studentDTO) );
        System.out.println(studentDTO1);
    }


}
