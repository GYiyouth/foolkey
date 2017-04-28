package foolkey.tool;


import foolkey.tool.cache.Cache;
import foolkey.tool.security.RSACoder;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来处理和json相关的东西
 * Created by geyao on 2017/2/13.
 */
@Component("jsonHandler")
public class JSONHandler {


    /**
     * 明文传输
     * @param jsonObject
     * @param response
     * @throws Exception
     */
	public void sendJSON(JSONObject jsonObject, HttpServletResponse response){
        if (jsonObject == null || response == null){
            System.out.println(JSONHandler.class);
            System.out.println("jsonObject == null || response == null");
        }
        if (!jsonObject.containsKey("result")){
            jsonObject.put("result", "fail");
        }
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonObject.toString());
            response.getWriter().flush();
            response.getWriter().close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 明文传输告知失败
     * @param response
     * @throws Exception
     */
    public void sendFailJSON(HttpServletResponse response){


        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", "fail");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonObject.toString());
            response.getWriter().flush();
            response.getWriter().close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
