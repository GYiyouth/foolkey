package foolkey.pojo.root.bo.coupon;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.CouponDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 获取我的优惠券，需要带上token
 * Created by geyao on 2017/5/2.
 */
@Service
@Transactional(readOnly = true)
public class GetMyCouponHandler extends AbstractBO{

    @Autowired
    private GetCouponBO getCouponBO;
    @Autowired
    private StudentInfoBO studentInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        int pageNum = clearJSON.getInt("pageNo");
        int pageSize = clearJSON.getInt("pageSize");

        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);

        List<CouponDTO> couponDTOList = getCouponBO.getCouponDTOList(
                studentDTO.getId() + "", pageNum, pageSize
        );

        jsonObject.put("couponList", couponDTOList);
        jsonObject.put("result", "success");

        jsonHandler.sendJSON(jsonObject, response);
    }
}
