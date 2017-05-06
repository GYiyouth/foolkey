package foolkey.pojo.root.bo.message;

//import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Result;
import foolkey.pojo.root.vo.dto.*;
import foolkey.tool.push_message.MessagePusher;
import org.springframework.stereotype.Service;

/**
 * Created by geyao on 2017/5/5.
 */
@Service("messageBO")
public class MessageBO {

    /**
     * 老师申请学生的reward之后，发送消息
     * @param applicationDTO
     * @return
     */
    public Result sendForApplication (
            ApplicationStudentRewardDTO applicationDTO,
            StudentDTO applicantDTO,
            CourseStudentDTO courseDTO
    ) throws Exception{
        String messagePayLoad = "messagePayLoad";
        String title = "有人申请了你的悬赏课程 ！";
        String description = applicantDTO.getName() + "申请了你的悬赏【" +
                courseDTO.getTopic() + "】请，尽快查看吧~";

        return
        MessagePusher.sendToUserAccount(
                applicationDTO.getStudentId() + "",
                messagePayLoad,
                title,
                description
        );
    }

    /**
     * 学生购买老师课程后，发送消息
     * @param applicationDTO
     * @param applicantDTO
     * @param courseDTO
     * @return
     * @throws Exception
     */
    public Result sendForApplication (
            ApplicationTeacherCourseDTO applicationDTO,
            StudentDTO applicantDTO,
            CourseTeacherDTO courseDTO
    ) throws Exception{
        String messagePayLoad = "messagePayLoad";
        String title = "有人购买了你的课程 ！";
        String description = applicantDTO.getName() + "购买了你的课程【" +
                courseDTO.getTopic() + "】请，尽快查看吧~";

        return
                MessagePusher.sendToUserAccount(
                        applicationDTO.getTeacherId() + "",
                        messagePayLoad,
                        title,
                        description
                );
    }

    /**
     * 成功充值的消息
     * @param studentDTO
     * @param amount
     * @return
     * @throws Exception
     */
    public Result sendForRecharge(
            StudentDTO studentDTO,
            Double amount
    )throws Exception{
        String messagePayLoad = "";
        String title = "充值成功！";
        String description = "您已成功充值" + amount + "虚拟币";
        return
                MessagePusher.sendToUserAccount(
                        studentDTO.getId() + "", messagePayLoad, title, description
                );
    }
}
