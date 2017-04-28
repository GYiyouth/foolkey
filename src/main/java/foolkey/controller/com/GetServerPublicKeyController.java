package foolkey.controller.com;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.bo.security.AESKeyBO;
import foolkey.pojo.root.bo.security.RSAKeyBO;
import foolkey.pojo.root.vo.assistObject.RSAKeyDTO;
import foolkey.tool.ConverterB20X;
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
import java.io.ByteArrayOutputStream;

/**
 * Created by geyao on 2017/4/25.
 */
@Controller
@RequestMapping("/getKey")
public class GetServerPublicKeyController extends AbstractController{

    @Resource(name = "rsaKeyBO")
    private RSAKeyBO rsaKeyBO;


    @RequestMapping
    public void execute(
            HttpServletResponse response
    ) throws Exception{

        JSONObject jsonObject = new JSONObject();
        try {
            RSAKeyDTO rsaKeyDTO = rsaKeyBO.getServerRSAKeyDTO();

            jsonObject.put("publicKey", rsaKeyDTO.getPubBase64Str());
            jsonObject.put("result", "success");
        }catch (Exception e){
            e.printStackTrace();
        }
        jsonHandler.sendJSON(jsonObject, response);
    }



}
