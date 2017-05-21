package foolkey.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static foolkey.tool.StaticVariable.ASK_QUESTION_VALID_DAY;
import static foolkey.tool.StaticVariable.ORDER_ASK_QUESTION_EXIST_DAY;
import static foolkey.tool.StaticVariable.PERMANENT_DATE;

/**
 * Created by GR on 2017/2/27.
 */
public class Time {

    /**
     * 获取当前时间String
     * 格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间Date
     * @return
     * @throws Exception
     */
    public static Date getCurrentDate() throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(getCurrentTime());
    }

    /**
     * 获取提问订单的有效期
     * @return
     * @throws Exception
     */
    public static Date getOrderAskQuestionExistingDate() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = new GregorianCalendar();
        Date date = new Date();
        c.setTime(date);//设置参数时间
        c.add(Calendar.DATE, ORDER_ASK_QUESTION_EXIST_DAY);//把日期往后增加。负数往前移动
        date=c.getTime();
        String str = sdf.format(date);
        return sdf.parse(str);
    }


    /**
     * 获取提问订单的有效期
     * @return
     * @throws Exception
     */
    public static Date getAskQuestioninValidDate() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = new GregorianCalendar();
        Date date = new Date();
        c.setTime(date);//设置参数时间
        c.add(Calendar.DATE, ASK_QUESTION_VALID_DAY);//把日期往后增加1天。负数往前移动
        date=c.getTime(); //这个时间就是日期往后推一天的结果
        String str = sdf.format(date);
        return sdf.parse(str);
    }

    /**
     * 获得永久有效时间
     *
     * @return
     * @throws Exception
     */
    public static Date getPermanentDate()throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(PERMANENT_DATE);
    }

    public static String getYear(){
        Date date = new Date();
        String year = String.format("%tY", date);
        return year;
    }

    public static String getMonth() {
        Date date = new Date();
        String month = String.format("%tm", date);
        return month;
    }

    public static String getDay() {
        Date date = new Date();
        String day = String.format("%td", date);
        return day;
    }

    public static String getHour() {
        Date date = new Date();
        String hour = String.format("%tH", date);
        return hour;
    }

    public static String getMinute() {
        Date date = new Date();
        String minute = String.format("%tM", date);
        return minute;
    }

    public static String getDateStr() {

        Time time = new Time();
        return (time.getYear()+time.getMonth()+ time.getDay());

    }

    public static Date getDate() {
        return new Date();
    }

    public static String getDeadTime() {
        String year = getYear();
        int deadYear = Integer.parseInt(year) + 10;
        return (deadYear + "-" + getMonth() + "-" + getDay() + " " +  getHour() + ":" + getMinute() + ":" + getSecond());
    }

    public static String getSecond() {
        Date date = new Date();
        String second = String.format("%tS", date);
        return second;
    }

    public static String getTime() {
        return (getYear() + "-" + getMonth() + "-" + getDay() + " " +  getHour() + ":" + getMinute() + ":" + getSecond());
    }

    public static String getGrade(){
        Integer year = Integer.parseInt(getYear());
        int month = Integer.parseInt(getMonth());
        if (month<9)
            year--;
        return year.toString();
    }
}
