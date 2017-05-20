package foolkey.interceptor.com;

import foolkey.interceptor.AbstractInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Created by geyao on 2017/4/28.
 */
@Controller("printerInterceptor")
public class PrinterInterceptor extends AbstractInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");

        System.out.println( "\n" + "请求的控制器Contrller是 " + "\n");
        HandlerMethod handlerMethod = (HandlerMethod) o;
        System.out.println( handlerMethod.getBean().getClass() );
        for (Object o1 : httpServletRequest.getParameterMap().keySet()) {
            String key = o1.toString();
            System.out.println(" key : value -----   " + key + " : " + httpServletRequest.getParameter(key));
        }

        return true;
    }
}
