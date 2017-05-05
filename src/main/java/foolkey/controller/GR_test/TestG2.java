package foolkey.controller.GR_test;

import java.lang.annotation.Annotation;

/**
 * Created by GR on 2017/5/3.
 */
public class TestG2 implements TestG1{
    @Override
    public Class<? extends Annotation> annotationType() {
        System.out.println("启动....");
        return null;
    }
}
