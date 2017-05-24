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

    //围观问答的价格
    public static final Double ONLOOK_VIRTUAL_PRICE = 100.0;

    //判定是否填写完个人资料的标准
    public static final Double PROFILE_FILLED_RATE = 0.7;

    //第一次填完个人资料，增加的声望值
    public static final Integer COMPLETE_PROFILE_RAISE_PRESTIGE = 10;

    //评价老师或学生  加减分情况
    //  1. 得分[0,1]（包括1），扣分
    public static final Integer PRESTIGE_RAISE_SCORE_LESS_1 = -10;
    //  2. 得分在(1,3)，不得分
    public static final Integer PRESTIGE_RAISE_SCORE_BETWEEN_1_3 = 0;
    //  3. 得分在[3-5)，+10
    public static final Integer PRESTIGE_RAISE_SCORE_BETWEEN_3_5 = 10;
    //  4. 得分5,+20
    public static final Integer PRESTIGE_RAISE_SCORE_EQUAL_5 = 20;
}
