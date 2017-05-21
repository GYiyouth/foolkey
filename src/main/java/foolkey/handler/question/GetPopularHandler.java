package foolkey.handler.question;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 按页获取问答
 * 参数：
 *      token：个人标识token
 *      pageNO：页码
 *      technicTagEnum：技术类别
 * 返回：
 *
 * Created by GR on 2017/5/21.
 */
public class GetPopularHandler extends AbstractBO {

    public void execute(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObject) throws Exception {

        //读取客户端传入参数
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        Integer pageNo = clearJSON.getInt("pageNo");
        String technicTagEnumStr = clearJSON.getString("technicTagEnum");
        TechnicTagEnum technicTagEnum = TechnicTagEnum.valueOf(technicTagEnumStr);

        //获取个人信息，进而判断是否可围观




    }
}
