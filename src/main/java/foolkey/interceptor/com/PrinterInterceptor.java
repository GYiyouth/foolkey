package foolkey.interceptor.com;

import foolkey.interceptor.AbstractInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * Created by geyao on 2017/4/28.
 */
@Controller("printerInterceptor")
public class PrinterInterceptor extends AbstractInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("请求的控制器Contrller是 ");
        HandlerMethod handlerMethod = (HandlerMethod) o;
        System.out.println( handlerMethod.getBean().getClass() );
        Iterator iterator = httpServletRequest.getParameterMap().keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next().toString();
            System.out.println(" key : value -----   " + key + " : " + httpServletRequest.getParameter(key));
        }
        return true;
    }
}
