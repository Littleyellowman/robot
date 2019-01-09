package com.robot.util;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @ClassName: DateUtil
 * @Description: 时间处理类
 * @Author:
 * @Version: V1.00 （版本号）
 * @CreateDate:  2018年12月22日 下午12:12:43
 */
public class DateUtil {
    private static Logger LOGGER = Logger.getLogger(DateUtil.class);

    /**
     * Format String : yyyy-MM-dd HH:mm:ss
     */
    public static final String DateFormat1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * Format String : yyyy-MM-dd
     */
    public static final String DateFormat2 = "yyyy-MM-dd";

    /**
     * Format String : yyyyMMdd
     */
    public static final String DateFormat3 = "yyyyMMdd";

    /**
     * Format String : yyyyMMdd HHmmss
     */
    public static final String DateFormat4 = "yyyyMMdd HHmmss";

    /**
     * Format String : yyyy-MM-dd HH:mm
     */
    public static final String DateFormat5 = "yyyy-MM-dd HH:mm";

    /**
     * Format String : yyyyMMdd HH:mm
     */
    public static final String DateFormat6 = "yyyyMMdd HH:mm";

    /**
     * Format String : yyyy年MM月dd日
     */
    public static final String DateFormat7 = "yyyy年MM月dd日";

    /**
     * 获取当前时间
     *
     * @return Date对象
     */
    public static Date getDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 返回当前时间
     *
     * @param format
     *            时间格式
     * @return string 当前时间指定格式字符串
     */
    public static String getDate(String format) {
        return getStringDate(getDate(), format);
    }

    /**
     * 按照固定格式化
     *
     * @param date
     *            Date
     * @param method
     *            时间格式
     * @return 制定的时间格式
     */
    public static String getStringDate(Date date, String method) {
        SimpleDateFormat sdf = new SimpleDateFormat(method);
        String ret = null;
        try {
            ret = sdf.format(date);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        return ret;
    }

    /**
     * 获取前几天或者后天时间
     *
     * @param dateStr
     *            'yyyyMMdd'
     * @param days
     *            天数
     * @return Date时间
     */
    public static Date getDate(String dateStr, int days) {
        return getDate(getDate(dateStr, DateFormat3), days);
    }

    /**
     * 传入String类型时间返回Date
     *
     * @param stringDate
     *            时间
     * @param method
     *            格式
     * @return 返回Date
     */
    public static Date getDate(String stringDate, String method) {
        SimpleDateFormat sdf = new SimpleDateFormat(method);
        Date ret = null;
        try {
            String integerDate = stringDate.replaceAll("-", "").replaceAll("/", "").replaceAll("年", "")
                    .replaceAll("月", "").replaceAll("日", "").replaceAll("：", ":");
            ret = sdf.parse(integerDate);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        return ret;
    }

    /**
     *
     * @Title: getDateByString
     * @Description: 强字符串转换为日期
     * @Author:
     * @Version: V1.00 （版本号）
     * @CreateDate: 2018年11月8日 下午4:33:51
     * @Parameters: @param s
     * @Parameters: @return
     * @Parameters: @throws ParseException
     * @Return Date
     * @Throws
     */
    public static Date getDateByString(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(s);
        return date;
    }

    /**
     *
     * @Title: getDatePoor
     * @Description: 获取两个时间的时间差：几天几时分钟
     * @Author:
     * @Version: V1.00 （版本号）
     * @CreateDate: 2018年8月20日 上午10:15:35
     * @Parameters: @param endDate
     * @Parameters: @param nowDate
     * @Parameters: @return
     * @Return String
     * @Throws
     */
    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        String time = "";
        if (day == 0) {
            if (hour == 0) {
                if (min == 0) {
                    time = sec + "秒";
                } else {
                    time = min + "分钟";
                }
            } else {
                time = hour + "小时" + min + "分钟";
            }
        } else {
            time = day + "天" + hour + "小时" + min + "分钟";
        }
        return time;
    }

    /**
     * 获取两时间差的天数
     *
     * @param beginDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return 天数
     */
    public static int getDayCount(Date beginDate, Date endDate) {
        int count = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        while (calendar.getTime().before(endDate)) {
            count++;
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return count;
    }

    /**
     *
     * @Title: getMonth
     * @Description: 获取+1，后一个月，-1前一个月
     * @Author:
     * @Version: V1.00 （版本号）
     * @CreateDate: 2018年12月19日 下午3:17:42
     * @Parameters: @param data
     * @Parameters: @param month
     * @Parameters: @return
     * @Return String
     * @Throws
     */
    public static Date getMonth(Date data, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.MONTH, month);
        /*
         * date = calendar.getTime(); return DateUtil.getStringDate(date,
         * DateUtil.DateFormat2);
         */
        return calendar.getTime();
    }

    /**
     *
     * @Title: getDate
     * @Description: 获取后几天或前时间：+1后一天，-1前一天
     * @Author:
     * @Version: V1.00 （版本号）
     * @CreateDate: 2018年12月19日 下午2:11:08
     * @Parameters: @param date
     * @Parameters: @param days
     * @Parameters: @return
     * @Return Date
     * @Throws
     */
    public static Date getDate(Date date, int days) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.DATE, days);
        return now.getTime();
    }

    /**
     *
     * @Title: getTime
     * @Description: 计算几个月零几天前/后的时间
     * @Author:
     * @Version: V1.00 （版本号）
     * @CreateDate: 2018年12月19日 下午3:26:17
     * @Parameters: @param data
     * @Parameters: @param month
     * @Parameters: @param day
     * @Parameters: @return
     * @Return Date
     * @Throws
     */
    public static Date getTime(Date data, int month, int days) {
        Date getMoth = getMonth(data, month);
        Date d = getDate(getMoth, days);
        return d;
    }

    /**
     * 传入LONG 返回 时分秒
     *
     * @param diff
     * @return
     */
    public static String LongToString(long diff) {
        String showtime = "";
        long oneSecond = 1000;
        long oneMinute = oneSecond * 60;
        long oneHour = oneMinute * 60;
        long hours = diff / oneHour;
        diff -= hours * oneHour;
        long minutes = diff / oneMinute;
        diff -= minutes * oneMinute;
        long seconds = diff / oneSecond;
        if (hours > 0)
            showtime += hours + "时";
        if (minutes > 0)
            showtime += minutes + "分";
        if (seconds > 0)
            showtime += seconds + "秒";
        return showtime;
    }
}
