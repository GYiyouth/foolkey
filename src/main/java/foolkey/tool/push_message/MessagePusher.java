package foolkey.tool.push_message;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

/**
 * Created by geyao on 2017/5/3.
 */
public class MessagePusher {

    public static final String appKey = "5151757210848";
    public static final String appSecret = "ulRdl1tnDIIO6gSuLR/O7Q==";
    public static final String packageName = "com.example.a29149.yuyuan";


    private static void sendMessage() throws Exception {
        Constants.useOfficial();
        Sender sender = new Sender(appKey);
        String messagePayload = "This is a message";
        String title = "notification title";
        String description = "notification description";
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(packageName)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result = sender.send(message, "1", 3);
        System.out.println(result);
    }
}
