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

    @Resource(name = "localCache")
    private  Cache cache;


    /**
     * 加密传输
     * @param jsonObject 未加密的jsonObject
     * @param response
     * @param token
     * @throws Exception
     */
	public void sendJSON(JSONObject jsonObject, HttpServletResponse response, String token) throws Exception {
		if (jsonObject == null || response == null){
            System.out.println(JSONHandler.class);
            System.out.println("传输json失败 \n jsonObject == null || response == null");
        }
		if (!jsonObject.containsKey("result")){
			jsonObject.put("result", "fail");
		}

		if (cache.isContainToken(token))
		    throw new IOException("缓存中无对应的token " + this.getClass());

		JSONObject jsonObject1 = new JSONObject();
        RSACoder rsaCoder = (RSACoder) cache.getMap(token).get("rsaCoder");

//		jsonObject1.put("result", rsaCoder.encryptByPrivateKey( jsonObject.toString() ));
        System.out.println("加密前 " + jsonObject);
        System.out.println("加密后 " + jsonObject1);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonObject1.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

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
