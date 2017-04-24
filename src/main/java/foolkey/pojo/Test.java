package foolkey.pojo;

import foolkey.pojo.root.vo.TestVO;

import foolkey.pojo.send_to_client.StudentDTO;
import foolkey.tool.BeanFactory;

/**
 * Created by geyao on 2017/4/24.
 */
public class Test {



    public static void main(String[] args) {
        TestVO testVO = BeanFactory.getBean("testVO", TestVO.class);
        int i = 0;
        long time1 = System.currentTimeMillis();
        System.out.println(testVO);
        try {


            while (i < 10) {
                StudentDTO studentDTO = new StudentDTO();
                testVO.getTestBO().save(studentDTO);
                i ++ ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        System.out.println( (time2 - time1) / 1000.0 );
    }
}
