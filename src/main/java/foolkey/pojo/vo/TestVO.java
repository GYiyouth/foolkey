package foolkey.pojo.vo;

import foolkey.pojo.bo.TestBO;
import foolkey.pojo.vo.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import foolkey.tool.BeanFactory;

import javax.annotation.Resource;

/**
 * Created by geyao on 2017/3/9.
 */
@Component
public class TestVO {


    private TestBO testBO;

    public TestVO() {
        super();
    }

    public void hello(){
        System.out.println("hello World");
    }

    public static void main(String[] args) {
        TestVO testVO = BeanFactory.getBean("testVO", TestVO.class);
        int i = 0;
        long time1 = System.currentTimeMillis();
        System.out.println(testVO);
        try {

        testVO.setTestBO(BeanFactory.getBean("testBO", TestBO.class));
        while (i < 1000) {
            StudentDTO studentDTO = new StudentDTO();
            testVO.testBO.save(studentDTO);
            i ++ ;
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        System.out.println( (time2 - time1) / 1000.0 );
    }


    public TestBO getTestBO() {
        return testBO;
    }

    @Autowired
    public void setTestBO(TestBO testBO) {
        this.testBO = testBO;
    }
}
