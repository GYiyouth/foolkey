package foolkey.controller.coupon;

import foolkey.controller.AbstractController;
import foolkey.handler.coupon.GetMyCouponHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取我的优惠券
 * aes加密
 * Created by geyao on 2017/5/2.
 */
@Controller
@RequestMapping("/aes/getMyCoupon")
public class GetMyCouponController extends AbstractController {

    @Autowired
    private GetMyCouponHandler couponHandler;

    @RequestMapping
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        couponHandler.execute(request, response, jsonObject);
    }
}
