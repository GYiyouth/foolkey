package foolkey.interceptor;

import foolkey.tool.JSONHandler;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/4/28.
 */
@Component
public abstract class AbstractInterceptor implements HandlerInterceptor{

    @Resource(name = "jsonHandler")
    protected JSONHandler jsonHandler;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 对编码进行预处理
     * @param cipherText
     * @return
     */
    protected String cipherPreHandler(String cipherText){
        if (cipherText == null || cipherText.equals(""))
            return "";
        cipherText = cipherText.replaceFirst("愚","\n");
        cipherText = cipherText.replaceFirst("愚","\n");
        cipherText = cipherText.substring(0, cipherText.length() -1 );
        return cipherText;
    }


}
