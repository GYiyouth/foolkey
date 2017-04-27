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

    @Resource(name = "aesKeyBO")
    private AESKeyBO aesKeyBO;


    @Resource(name = "localCache")
    private Cache cache;


    @RequestMapping
    public void execute(
            HttpServletResponse response
    ) throws Exception{

        try {
            RSAKeyDTO rsaKeyDTO = rsaKeyBO.getServerRSAKeyDTO();
            String raw = "愚人科技";
            System.out.println("原文是 \n+ " + raw);
            String cipherText = rsaKeyBO.encryptByPub(raw, rsaKeyBO.getServerRSAKeyDTO().getPubBase64Str());

            String str = rsaKeyBO.decrypyBase64StrByPri(cipherText, rsaKeyBO.getServerRSAKeyDTO().getPriBase64Str());

            System.out.println("密钥解密？ ");
            System.out.println(str.equals(raw));
            System.out.println(str);
            jsonObject.put("publicKey", rsaKeyDTO.getPubBase64Str());
            jsonObject.put("privateKey", rsaKeyDTO.getPriBase64Str());
            jsonObject.put("clearText", raw);
            jsonObject.put("cipherText", cipherText);

            String pub16 = ConverterB20X.encode(rsaKeyDTO.getPubBase64Str());
            String pri16 = ConverterB20X.encode(rsaKeyDTO.getPriBase64Str());
            String cipher16 = ConverterB20X.encode(cipherText);
            String clear16 = ConverterB20X.encode(raw);
            jsonObject.put("pub16", pub16);
            jsonObject.put("pri16", pri16);
            jsonObject.put("cipher16", cipher16);
            jsonObject.put("clear16", clear16);

            jsonObject.put("result", "success");
        }catch (Exception e){
            e.printStackTrace();
        }
        jsonHandler.sendJSON(jsonObject, response);
    }



}
