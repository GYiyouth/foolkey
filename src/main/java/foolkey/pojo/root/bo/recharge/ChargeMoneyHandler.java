package foolkey.pojo.root.bo.recharge;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.message.MessageBO;
import foolkey.pojo.root.bo.student.StudentInfoBO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 充值，需要金额
 * Created by geyao on 2017/5/1.
 */
@Service
public class ChargeMoneyHandler extends AbstractBO{
    @Autowired
    private MessageBO messageBO;

    @Autowired
    private StudentInfoBO studentInfoBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    ) throws Exception{
        String clearText = request.getAttribute("clearText").toString();
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String token = clearJSON.getString("token");
        Double amount = clearJSON.getDouble("amount");

        StudentDTO studentDTO = studentInfoBO.getStudentDTO(token);
        studentDTO.setVirtualCurrency(studentDTO.getVirtualCurrency() + amount);
        studentInfoBO.updateStudent(studentDTO);

        jsonObject.put("result", "success");
        jsonObject.put("virtualCurrency", studentDTO.getVirtualCurrency());
        jsonHandler.sendJSON(jsonObject, response);

        messageBO.sendForRecharge(studentDTO, amount);
    }
}
