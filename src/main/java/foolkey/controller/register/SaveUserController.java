package foolkey.controller.register;

import foolkey.controller.AbstractController;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by geyao on 2017/4/26.
 */
@Controller
@RequestMapping("/rsa/register")
public class SaveUserController extends AbstractController{

    @RequestMapping
    public void execute(
            HttpServletRequest request
    ){
        JSONObject decryptJSON = JSONObject.fromObject( request.getAttribute("decryptJSON") );
        System.out.println(decryptJSON);
        Map map = (Map) request.getAttribute("decryptMap");
        Iterator iterator = decryptJSON.keys();
        while (iterator.hasNext()){
            String key = iterator.next().toString();
            System.out.println(key + " - " + decryptJSON.get(key));
            System.out.println();
        }
        System.out.println();
        System.out.println(map.keySet());
        System.out.println(map.entrySet());
    }
}
