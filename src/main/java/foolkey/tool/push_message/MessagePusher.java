package foolkey.tool.push_message;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import org.springframework.stereotype.Component;


/**
 * Created by geyao on 2017/5/3.
 */
@Component
public class MessagePusher {

    private static final String appKey = "5151757210848";
    private static final String appSecret = "ulRdl1tnDIIO6gSuLR/O7Q==";
    private static final String packageName = "com.example.a29149.yuyuan";
    private static final Sender sender = new Sender(appSecret);
    static {
        Constants.useOfficial();
    }


    public MessagePusher() {
        super();
    }

    public static void main(String[] args) throws Exception{

        Sender sender = new Sender(appSecret);

        String messagePayload = "This is a message";
        String title = "notification title";
        String description = "notification description";
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(packageName)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result = sender.sendToUserAccount(message, 20012 + "", 10);
        System.out.println(result);
    }

    /**
     * 广播消息
     * @param messagePayload
     * @param title
     * @param description
     * @return
     * @throws Exception
     */
    public static String boradcast(
            String messagePayload, String title, String description
    )throws Exception{

        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(packageName)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result = sender.broadcastAll(message, 10);
        return result.getMessageId();
    }

    /**
     * 给某个主题发消息
     * @param topic
     * @param messagePayload
     * @param title
     * @param description
     * @return
     * @throws Exception
     */
    public static String sendToTopic(
            String topic, String messagePayload, String title, String description
    )throws Exception{
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(packageName)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result = sender.broadcast(message, topic, 10);
        return result.getMessageId();
    }

    public static Result sendToUserAccount(
            String userAccount, String messagePayload, String title, String description
    )throws Exception{
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(packageName)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result = sender.sendToUserAccount(message, userAccount, 10);
        return result;
    }
}
