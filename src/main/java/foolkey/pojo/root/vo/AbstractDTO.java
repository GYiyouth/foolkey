package foolkey.pojo.root.vo;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 所有DTO的父类
 * Created by geyao on 2017/5/19.
 */
public abstract class AbstractDTO implements Serializable{


    /**
     * 方法暂时不可用，有bug
     * @param target
     * @param source
     * @param targetClass
     * @throws Exception
     */
    private static void myClone(AbstractDTO target, AbstractDTO source, Class targetClass) throws Exception{
        Field[] fields = targetClass.getDeclaredFields();
        for (Field field : fields) {
            PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), targetClass);
            Method setMethod = descriptor.getWriteMethod();
            Method getMethod = descriptor.getReadMethod();
            setMethod.invoke(target, getMethod.invoke(source));
        }
    }
}
