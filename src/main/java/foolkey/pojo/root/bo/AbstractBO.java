package foolkey.pojo.root.bo;

import foolkey.tool.JSONHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by geyao on 2017/5/1.
 */
@Component
public abstract class AbstractBO {
    @Autowired
    public JSONHandler jsonHandler;
}
