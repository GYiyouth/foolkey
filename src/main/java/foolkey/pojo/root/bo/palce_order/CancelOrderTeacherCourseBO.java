package foolkey.pojo.root.bo.palce_order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 取消订单
 * 需要提交订单号
 * AES加密，将订单的状态改变为cancel、
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional
public class CancelOrderTeacherCourseBO {

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception{
        String clearText = request.getAttribute("clearText").toString();
    }
}
