package foolkey.controller;

import foolkey.pojo.root.bo.order_course.OrderInfoBO;
import foolkey.tool.ConverterB20X;
import foolkey.tool.ConverterByteBase64;
import foolkey.tool.JSONHandler;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by GR on 2017/5/13.
 */

public class Test {

    private OrderInfoBO orderInfoBO;

//    @RequestMapping
//    public void execute(HttpServletResponse response){
//        JSONHandler jsonHandler = new JSONHandler();
//        JSONObject jsonObject = new JSONObject();
//        Long time1 = System.currentTimeMillis();
//        List list = orderInfoBO.test();
//        jsonObject.put("1", orderInfoBO.test());
//        jsonHandler.sendJSON(jsonObject, response);
//    }

    public static void main(String[] args) throws Exception{
        System.out.println(

                ConverterByteBase64.base642Byte("PqLblN1r\\/KpHWoFlddshZ9\\/UOOo65PJbJeqBFUOxl8XPqHolNfn9dKxyat49ndEy7xuj9Iky02bjæ\u0084\u009A80+4kNzAQc6m\\/MKXktki1tE8nDpM5szfo2xEfw9rI+i\\/e6VMZduWqcz\\/3LXaIAJn2kRccvrahNYvæ\u0084\u009AqY6PmvvE8xvmF+KTc1k=k"
                    ).length + "\n" + ConverterByteBase64.base642Byte("PqLblN1r/KpHWoFlddshZ9/UOOo65PJbJeqBFUOxl8XPqHolNfn9dKxyat49ndEy7xuj9Iky02bj愚80+4kNzAQc6m/MKXktki1tE8nDpM5szfo2xEfw9rI+i/e6VMZduWqcz/3LXaIAJn2kRccvrahNYv愚qY6PmvvE8xvmF+KTc1k=愚").length

        );
        String cipherText = "\n123\n213\n";
        cipherText = URLEncoder.encode(cipherText, "UTF-8");
        System.out.println(cipherText);
        System.out.println( URLEncoder.encode(cipherText));

        System.out.println(URLDecoder.decode(cipherText));
    }
}
