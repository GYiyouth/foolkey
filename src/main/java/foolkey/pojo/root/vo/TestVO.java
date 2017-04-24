package foolkey.pojo.root.vo;

import foolkey.pojo.root.bo.TestBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by geyao on 2017/3/9.
 */
@Component
public class TestVO {


    private TestBO testBO;

    public TestVO() {
        super();
    }




    public TestBO getTestBO() {
        return testBO;
    }

    @Autowired
    public void setTestBO(TestBO testBO) {
        this.testBO = testBO;
    }
}
