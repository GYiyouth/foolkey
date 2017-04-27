package foolkey.interceptor.com;

import foolkey.pojo.root.bo.security.RSAKeyBO;
import foolkey.pojo.root.vo.assistObject.RSAKeyDTO;
import foolkey.tool.ConverterByteBase64;
import foolkey.tool.cache.Cache;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * 非对称加密的信息，解谜，密文
 * 【cipherText】
 * Created by geyao on 2017/4/26.
 */
@Controller(value = "rsaDecryptInterceptor")
public class RSADecryptInterceptor implements HandlerInterceptor {
    @Resource(name = "rsaKeyBO")
    private RSAKeyBO rsaKeyBO;
    @Resource(name = "localCache")
    private Cache cache;


    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object o
    )throws Exception {
        try {
            request.setCharacterEncoding("UTF-8");

            //获取密文
            String cipherText = request.getAttribute("cipherText").toString();
            //拿取私钥
            RSAKeyDTO rsaKeyDTO= rsaKeyBO.getServerRSAKeyDTO();
            String priKeyStr = rsaKeyDTO.getPriBase64Str();

            System.out.println("密文的hashCode " + cipherText.hashCode());
            //解密
            String decryptText = rsaKeyBO.decrypyBase64StrByPri(cipherText, priKeyStr);
            System.out.println("解密后 ");
            System.out.println(decryptText);

            //转换为json，处理信息
            JSONObject decryptJSON = JSONObject.fromObject(decryptText);
            Map map = new HashedMap();
            Iterator iterator = decryptJSON.keys();
            while (iterator.hasNext()){
                String key = iterator.next().toString();
                map.put(key, decryptJSON.get(key));
            }
            request.setAttribute("decryptJSON", decryptJSON);
            request.setAttribute("decryptMap", map);
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        return;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        return;
    }
}
