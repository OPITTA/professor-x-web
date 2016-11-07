package com.br.professor_x_web.util;

import com.br.professor_x_web.model.StringPair;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期
 *
 * @author caoxin
 */
public class DateUtil {

    private static DateUtil dateUtil;

    private DateUtil() {
    }

    public static DateUtil getInstance() {
        if (DateUtil.dateUtil == null) {
            DateUtil.dateUtil = new DateUtil();
        }
        return DateUtil.dateUtil;
    }

    private static final String FORMAT_DATE_1 = "yyyy-MM-dd";
    private static final String FORMAT_DATE_2 = "yyyyMMdd";
    private static final String FORMAT_DATE_3 = "yyyy-MM";
    private static final String FORMAT_DATETIME_1 = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat FORMAT_DATE_1_PATTERN = new SimpleDateFormat(FORMAT_DATE_1);
    private static final SimpleDateFormat FORMAT_DATE_2_PATTERN = new SimpleDateFormat(FORMAT_DATE_2);
    private static final SimpleDateFormat FORMAT_DATE_3_PATTERN = new SimpleDateFormat(FORMAT_DATE_3);
    private static final SimpleDateFormat FORMAT_DATETIME_1_PATTERN = new SimpleDateFormat(FORMAT_DATETIME_1);

    /**
     *
     * @param date
     * @return yyyy-MM-dd
     */
    public static String formatForDate1(Date date) {
        return FORMAT_DATE_1_PATTERN.format(date);
    }

    /**
     *
     * @param date
     * @return yyyyMMdd
     */
    public static String formatForDate2(Date date) {
        return FORMAT_DATE_2_PATTERN.format(date);
    }

    /**
     *
     * @param date
     * @return yyyyMMdd
     */
    public static String formatForDate3(Date date) {
        return FORMAT_DATE_3_PATTERN.format(date);
    }

    /**
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatForDatetime1(Date date) {
        return FORMAT_DATETIME_1_PATTERN.format(date);
    }

    /**
     *
     * @param str yyyy-MM-dd
     * @return
     */
    public static Date getDate1(String str) {
        try {
            return FORMAT_DATE_1_PATTERN.parse(str);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param str yyyyMMdd
     * @return
     */
    public static Date getDate2(String str) {
        try {
            return FORMAT_DATE_2_PATTERN.parse(str);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param str yyyyMM
     * @return
     */
    public static Date getDate3(String str) {
        try {
            return FORMAT_DATE_3_PATTERN.parse(str);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param str yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date getDatetime1(String str) {
        try {
            return FORMAT_DATETIME_1_PATTERN.parse(str);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 时间差
     *
     * @param startTime
     * @param endTime
     * @return 时间差 毫秒
     */
    public static long timeDiffer(Date startTime, Date endTime) {
        long time = (endTime.getTime() - startTime.getTime());
        return time >= 0 ? time : -time;
    }

    /**
     * 毫秒转为天
     *
     * @param millisecond 毫秒
     * @return 天
     */
    public static int millisecondToDay(long millisecond) {
        return (int) (millisecond / (1000 * 60 * 60 * 24));
    }

    /**
     * 毫秒转为年
     *
     * @param millisecond 毫秒
     * @return 年
     */
    public static int millisecondToYear(long millisecond) {
        return (int) (millisecond / (1000 * 60 * 60 * 24) / 365);
    }

    /**
     * 返回格式 [YYYY-MM-DD, YYYY-MM-MAX(DD)]
     *
     * @param n (0 表示当月)
     * @return
     */
    public static StringPair getBeforeMonth(int n) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -n);
        int startYear = cal.get(Calendar.YEAR);
        int startMonthOfYear = cal.get(Calendar.MONTH) + 1;
        String beginDate = String.format("%d-%02d-%02d", startYear, startMonthOfYear, 1);
        if (n != 0) {
            cal.add(Calendar.MONTH, 1);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        int endYear = cal.get(Calendar.YEAR);
        int endMonthOfYear = cal.get(Calendar.MONTH) + 1;
        String endDate = String.format("%d-%02d-%02d", endYear, endMonthOfYear, cal.get(Calendar.DAY_OF_MONTH));
        return new StringPair(beginDate, endDate);
    }

    /**
     *
     * @param args
     */
    public static void main(String... args) {
        System.out.println(DateUtil.formatForDate1(new Date()));
        System.out.println(DateUtil.formatForDate2(new Date()));
        System.out.println(DateUtil.formatForDatetime1(new Date()));
        Date brithDate1 = DateUtil.getDate1("1988-11-15");
        System.out.println(DateUtil.millisecondToDay(DateUtil.timeDiffer(brithDate1, new Date())));
        System.out.println(DateUtil.millisecondToYear(DateUtil.timeDiffer(brithDate1, new Date())));
        Date brithDate2 = DateUtil.getDate2("19881115");
        System.out.println(DateUtil.millisecondToDay(DateUtil.timeDiffer(brithDate2, new Date())));
        System.out.println(DateUtil.millisecondToYear(DateUtil.timeDiffer(brithDate2, new Date())));
    }
}
