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


    /**
     * 发送给老师，申请悬赏通过
     * @param studentDTO
     * @param teacher
     * @param courseDTO
     * @return
     * @throws Exception
     */
    public Result sendForPayReward(
            StudentDTO studentDTO, //学生悬赏的拥有者
            StudentDTO teacher, //老师，收件人
            CourseStudentDTO courseDTO //课程
    )throws Exception{
        String messagePayLoad = "sendForPayReWard";
        String title = "有人同意了你的悬赏申请！";
        String description = studentDTO.getName() + " 的 " + courseDTO.getTopic();
        return
                MessagePusher.sendToUserAccount(
                        teacher.getId() + "", messagePayLoad, title, description
                );
    }

    /**
     * 发送给老师，认证通过
     * @param teacher
     * @return
     * @throws Exception
     */
    public Result sendForVerifyPassed(
            StudentDTO teacher
    )throws Exception{
        String messagePayLoad = "sendForVerifyPassed";
        String title = "恭喜您，教师认证通过";
        String description = "解锁了 发布课程、接受提问 功能！";
        return
                MessagePusher.sendToUserAccount(
                        teacher.getId() + "", messagePayLoad, title, description
                );
    }
    /**
     * 发送给老师，认证失败
     * @param teacher
     * @return
     * @throws Exception
     */
    public Result sendForVerifyFailed(
            StudentDTO teacher
    )throws Exception{
        String messagePayLoad = "sendForVerifyFailed";
        String title = "很抱歉，教师认证未通过";
        String description = "由于最近的一次中差评，您的教师认证申请未通过";
        return
                MessagePusher.sendToUserAccount(
                        teacher.getId() + "", messagePayLoad, title, description
                );
    }
}
