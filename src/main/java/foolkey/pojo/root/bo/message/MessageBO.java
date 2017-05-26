package foolkey.pojo.root.bo.message;

//import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Result;
import foolkey.pojo.root.bo.student.StudentInfoBO;
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
            RewardDTO courseDTO
    ) throws Exception{
        String messagePayLoad = "messagePayLoad";
        String title = "有人申请了你的悬赏课程 ！";
        String description = "【" +
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
            CourseDTO courseDTO
    ) throws Exception{
        String messagePayLoad = "messagePayLoad";
        String title = "有人购买了你的课程 ！";
        String description = applicantDTO.getNickedName() + "购买了你的课程【" +
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
            RewardDTO courseDTO //课程
    )throws Exception{
        String messagePayLoad = "sendForPayReWard";
        String title = "有人同意了你的悬赏申请！";
        String description = studentDTO.getNickedName() + " 的 " + courseDTO.getTopic();
        return
                MessagePusher.sendToUserAccount(
                        teacher.getId() + "", messagePayLoad, title, description
                );
    }

    /**
     * 发送给老师，申请悬赏未通过
     * @param studentDTO
     * @param teacher
     * @param rewardDTO
     * @return
     * @throws Exception
     */
    public Result sendForPayRewardRefuse(
            StudentDTO studentDTO, //学生悬赏的拥有者
            StudentDTO teacher, //老师，收件人
            RewardDTO rewardDTO //课程
    )throws Exception{
        String messagePayLoad = "sendForPayRewardRefuse";
        String title = "有人拒绝了你的悬赏申请！";
        String description = studentDTO.getNickedName() + " 的 " + rewardDTO.getTopic();
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

    /**
     * 申请关闭交易时，给老师发送的消息
     * @param student
     * @param teacher
     * @param courseName
     * @return
     * @throws Exception
     */
    public Result sendForRefundToTeacher(
            StudentDTO student,
            StudentDTO teacher,
            String courseName
    )throws Exception{
        String messagePayLoad = "sendForVerifyFailed";
        String title = "有人申请了退款，请尽快处理哦";
        String description = "课程：" + courseName ;
        return
                MessagePusher.sendToUserAccount(
                        teacher.getId() + "", messagePayLoad, title, description
                );
    }

    /**
     * 退款成功后，给学生发送的消息
     * @param student
     * @return
     * @throws Exception
     */
    public Result sendForRefundSuccessToStudent(
            StudentDTO student
    )throws Exception{
        String messagePayLoad = "sendForVerifyFailed";
        String title = "退款已通过";
        String description = "您的退款申请已经被老师同意，退款稍后会到账";
        return
                MessagePusher.sendToUserAccount(
                        student.getId() + "", messagePayLoad, title, description
                );
    }

    /**
     * 退款失败后，给学生发送的消息
     * @param student
     * @return
     * @throws Exception
     */
    public Result sendForRefundFailedToStudent(
            StudentDTO student
    )throws Exception{
        String messagePayLoad = "sendForVerifyFailed";
        String title = "您的退款申请未通过";
        String description = "请和老师协调退款，或向系统求助";
        return
                MessagePusher.sendToUserAccount(
                        student.getId() + "", messagePayLoad, title, description
                );
    }

    /**
     * 开始上课后，给学生发消息
     * @param studentDTO
     * @return
     * @throws Exception
     */
    public Result sendForStartClass(
            StudentDTO studentDTO,
            CourseAbstract courseAbstract
    )throws Exception{
        String messagePayLoad = "sendForStartClass";
        String title = "开始上课啦！";
        String description = courseAbstract.getTopic() + " 开始上课啦";
        return
                MessagePusher.sendToUserAccount(
                        studentDTO.getId() + "", messagePayLoad, title, description
                );
    }

    /**
     * 下课后，给学生发送消息
     * @param studentDTO
     * @param courseAbstract
     * @return
     * @throws Exception
     */
    public Result sendForEndClass(
            StudentDTO studentDTO,
            CourseAbstract courseAbstract
    )throws Exception{
        String messagePayLoad = "sendForEndClass";
        String title = "已下课，请尽快评价";
        String description = courseAbstract.getTopic() + "已经下课，请尽快给老师反馈";
        return
                MessagePusher.sendToUserAccount(
                        studentDTO.getId() + "", messagePayLoad, title, description
                );
    }

    /**
     * 学生评价老师后，给老师发送消息
     * @param teacher
     * @return
     * @throws Exception
     */
    public Result sendForEvaluation(
            StudentDTO teacher
    )throws Exception{
        String messagePayLoad = "sendForEvaluation";
        String title = "学生已经对您进行了评价";
        String description =  "请切换老师后，在个人中心查询余额变化";
        return
                MessagePusher.sendToUserAccount(
                        teacher.getId() + "", messagePayLoad, title, description
                );
    }


    /**
     * 通知提问者问题被回答
     * @param asker
     * @return
     * @throws Exception
     */
    public Result sendToAskerOfAnswer(
            StudentDTO asker
    ) throws Exception {
        String messagePayLoad = "sendToAskerOfAnswer";
        String title = "你的问题已经回答了";
        String description =  "去查看一下吧！";
        return
                MessagePusher.sendToUserAccount(
                        asker.getId() + "", messagePayLoad, title, description
                );
    }

    /**
     * 通知回答者有人提了一个问题
     * @param answerer
     * @return
     * @throws Exception
     */
    public Result sendToAnswererOfAsk(
            StudentDTO answerer
    ) throws Exception {
        String messagePayLoad = "sendToAnswererOfAsk";
        String title = "有人向你提了一个问题";
        String description =  "去查看一下吧！";
        return
                MessagePusher.sendToUserAccount(
                        answerer.getId() + "", messagePayLoad, title, description
                );
    }

    /**
     * 通知提问者有人围观了
     * @param asker
     * @return
     * @throws Exception
     */
    public Result sendToAskerOfOnlook(
            StudentDTO asker
    ) throws Exception {
        String messagePayLoad = "sendToAskerOfOnlook";
        String title = "有人围观了你提问的问题";
        String description =  "去查看一下吧！";
        return
                MessagePusher.sendToUserAccount(
                        asker.getId() + "", messagePayLoad, title, description
                );
    }

    /**
     * 通知回答者有人围观了
     * @param answerer
     * @return
     * @throws Exception
     */
    public Result sendToAnswererOfOnlook(
            StudentDTO answerer
    ) throws Exception {
        String messagePayLoad = "sendToAnswererOfOnlook";
        String title = "有人围观了你回答的问题";
        String description =  "去查看一下吧！";
        return
                MessagePusher.sendToUserAccount(
                        answerer.getId() + "", messagePayLoad, title, description
                );
    }

}
