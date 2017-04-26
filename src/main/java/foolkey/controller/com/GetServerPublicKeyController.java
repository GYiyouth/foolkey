package foolkey.controller.com;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.security.AESKeyBO;
import foolkey.pojo.root.bo.security.RSAKeyBO;
import foolkey.pojo.root.vo.assistObject.RSAKeyDTO;
import foolkey.tool.ConverterByteBase64;
import foolkey.tool.JSONHandler;
import foolkey.tool.cache.Cache;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by geyao on 2017/4/25.
 */
@Controller
@RequestMapping("/getKey")
public class GetServerPublicKeyController extends AbstractController{

    @Resource(name = "rsaKeyBO")
    private RSAKeyBO rsaKeyBO;

    @Resource(name = "aesKeyBO")
    private AESKeyBO aesKeyBO;


    @Resource(name = "localCache")
    private Cache cache;


    @RequestMapping
    public void execute(
            HttpSession session,
            HttpServletResponse response
    ) throws Exception{

        try {
            RSAKeyDTO rsaKeyDTO = rsaKeyBO.getServerRSAKeyDTO();
            jsonObject.put(" pub ", rsaKeyDTO.getPubBase64Str());
            jsonObject.put(" pri ", rsaKeyDTO.getPriBase64Str());
            jsonObject.put("result", "success");
        }catch (Exception e){
            e.printStackTrace();
        }
        jsonHandler.sendJSON(jsonObject, response);
    }
}
