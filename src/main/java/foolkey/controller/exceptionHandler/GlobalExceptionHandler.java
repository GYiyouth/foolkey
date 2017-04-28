package foolkey.controller.exceptionHandler;

import foolkey.tool.JSONHandler;
import org.springframework.http.HttpStatus;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 参考http://www.cnblogs.com/xguo/p/3163519.html
 * Created by geyao on 2017/4/28.
 */
@ControllerAdvice
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @Resource(name = "jsonHandler")
    private JSONHandler jsonHandler;


    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        e.printStackTrace();
        jsonHandler.sendFailJSON(httpServletResponse);
        return null;
    }
}
