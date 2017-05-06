package foolkey.handler.course.judge;

import foolkey.pojo.root.bo.AbstractBO;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/6.
 */
@Service
@Transactional
public class EvaluateTeacherHandler extends AbstractBO{

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{

    }
}
