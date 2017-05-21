package foolkey.tool;

/**
 * Created by admin on 2017/4/27.
 */
public class StaticVariable {

    //设置一个永久有效的时间，暂设2012-12-21 00:00:00
    public static final String permanentDate = "2012-12-21 00:00:00";

    //定义缓存中标签下课程、问答、文章缓存大小
    public static final Integer cacheSize = 100;

    //每页展示数目
    public static final Integer pageSize = 10;

    //提问订单有效期(单位，天。)
    public static final Integer orderAskQuestion_ExistDay = 1;
    //提问的有效期(这段时间可以回答)
    public static final Integer askQuestionin_ValidDay = 7;

}
