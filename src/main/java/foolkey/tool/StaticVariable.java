package foolkey.tool;

/**
 * Created by admin on 2017/4/27.
 */
public class StaticVariable {

    //设置一个永久有效的时间，暂设2012-12-21 00:00:00
    public static final String PERMANENT_DATE = "2012-12-21 00:00:00";

    //定义缓存中标签下课程、问答、文章缓存大小
    public static final Integer CACHE_SIZE = 100;

    //每页展示数目
    public static final Integer PAGE_SIZE = 10;

    //提问订单有效期(单位，天。)
    public static final Integer ORDER_ASK_QUESTION_EXIST_DAY = 1;
    //提问的有效期(这段时间可以回答)
    public static final Integer ASK_QUESTION_VALID_DAY = 7;

    //围观价格
    public static final Double ONLOOK_VIRTUAL_PRICE = 100.0;

}
