package foolkey.handler.student;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 为app端写的，获取一次性上传密钥
 * Created by geyao on 2017/5/22.
 */
@Service
public class GetOneTimeSign_uploadHandler extends AbstractBO {
    @Autowired
    private StudentInfoBO studentInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
//        String clearText = request.getParameter("clearText").toString();
//        JSONObject clearJSON = JSONObject.fromObject( clearText );
//
//        String token = clearJSON.getString("token");

        jsonObject.put("sign", studentInfoBO.getManyTimeSign(null));
        jsonObject.put("result", "success");

        jsonHandler.sendJSON( jsonObject, response );
    }
}
