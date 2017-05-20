package foolkey.pojo.root.vo;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 所有DTO的父类
 * Created by geyao on 2017/5/19.
 */
public abstract class AbstractDTO implements Serializable{


    //克隆，将源和目标对象共有的属性，赋值给目标
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
