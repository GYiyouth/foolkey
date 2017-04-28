package foolkey.controller;

import foolkey.tool.JSONHandler;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;

/**
 * Created by geyao on 2017/4/26.
 */
@Component
public abstract class AbstractController {

    @Resource(name = "jsonHandler")
    protected JSONHandler jsonHandler;

    protected JSONObject jsonObject = new JSONObject();

    /**
     * 前置函数
     */
    @ModelAttribute
    private void init(){
        jsonObject.clear();
        return;
    }
}
