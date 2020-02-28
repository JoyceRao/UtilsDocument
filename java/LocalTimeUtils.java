package java8Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * @author joyce
 * @version JDK1.8
 * @Date 2020/02/28
 * @description LocalDateTime工具类
 */

 public class LocalTimeUtils {
     public static final String TIME = "HH:mm:ss";
     public static final String TIME_SHORT = "HH:mm";
     public static final String DATE = "yyyy-MM-dd";
     public static final String DATE_SHORT = "yyyyMMdd";
     public static final String DATE_LOW = "yyMMdd";
     public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
     public static final String DATE_TIME_SHORT = "yyyy-MM-dd HH:mm";
     public static final String DATE_TIME_LONG = "yyyy-MM-dd HH:mm:ss SSS";
	private static LocalTime currentTime;

     /**
      *  获取指定时间格式。
      * @param pattern 指定的时间格式
      * @return DateTimeFormatter
      */
     public static DateTimeFormatter dateTimeFormatter(String pattern) {
         return DateTimeFormatter.ofPattern(pattern);
     }

     /**
      *  获取指定时间和时区的格式。
      * @param pattern 指定的时间格式
      * @param local  指定时区
      * @return DateTimeFormatter
      */
     public static DateTimeFormatter dateTimeFormatter(String pattern, Locale local) {
         return DateTimeFormatter.ofPattern(pattern, local);
     }

     /**
      * 获取指定格式的当前日期时间
      * @param pattern
      * @return
      */
     public static String getCurrentDateTimeFormatter(String pattern) {
        return getCurrentDateTime().format(dateTimeFormatter(pattern));
    }

    /**
     * 获取指定格式和地区的日期时间
     * @param pattern
     * @param locale
     * @return
     */
     public static String getCurrentDateTimeFormatter(String pattern, Locale locale) {
         return getCurrentDateTime().format(dateTimeFormatter(pattern, locale));
     }


     ///获取当前日期时间。
     public static LocalDateTime getCurrentDateTime() {
         return LocalDateTime.now();
     }

     public static LocalDate getCurrentDate() {
         return LocalDate.now();
     }

     ///获取当前时间
     public static LocalTime getCurrentTime() {
         return LocalTime.now();
     }

     //获取当前带格式的日期时间: yyyy-MM-dd HH:mm:ss
     public static String getCurrentFormatterDateTime() {
         return getCurrentDateTime().format(dateTimeFormatter(DATE_TIME));
     }

     //获取当前带格式的日期时间: yyyy-MM-dd
     public static String getCurrentFormatterDate() {
        return getCurrentDateTime().format(dateTimeFormatter(DATE));
    }

    //获取当前带格式的日期时间: HH:mm:ss
    public static String getCurrentFormatterTime() {
        return getCurrentDateTime().format(dateTimeFormatter(TIME));
    }

    //获取当前带格式的日期时间: yyyy-MM-dd HH:mm:ss SSS
    public static String getCurrentFormatterDateTimeLong() {
        return getCurrentDateTime().format(dateTimeFormatter(DATE_TIME_LONG));
    }

    //获取当前带格式的日期时间: HH:mm
    public static String getCurrentFormatterTimeShort() {
        return getCurrentDateTime().format(dateTimeFormatter(TIME_SHORT));
    }

    /**
     * 将时间转成LocalDate格式。
     * @param dateStr
     * @param pattern
     * @return
     */
    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, dateTimeFormatter(pattern));
    }

    /**
     * 将时间转成LocalDateTime
     * @param dateTimeStr
     * @param pattern
     * @return
     */
    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, dateTimeFormatter(pattern));
    }

    /**
     * 将时间转成LocalTime
     * @param timeStr
     * @param pattern
     * @return
    */
    public static LocalTime parseLocalTime(String timeStr, String pattern) {
        return LocalTime.parse(timeStr, dateTimeFormatter(pattern));
    }

    /**
     * 间隔毫秒数
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    public static long intervalEpochMilli(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return getEpochMilli(endDateTime) - getEpochMilli(startDateTime);
    }

    /**
     * 间隔秒数
     * @param startTime
     * @param endTime
     * @return
     */
    public static long intervalSeconds(LocalTime startTime, LocalTime endTime) {
        return Duration.between(startTime, endTime).get(ChronoUnit.SECONDS);
    }

    /**
     * 获取间隔天数
     * @param startDate
     * @param endDate
     * @return 
     */
    public static long intervalDays(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }
    /**
     * 获取间隔周数
     * @param startDate
     * @param endDate
     * @return 
     */
    public static long intervalWeeks(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.WEEKS);
    }

    /**
     * 获取毫秒数
     * @param dateTime 当前日期
     */
    public static long getEpochMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    //判断是否为闰年
    public static boolean isLeapYear(LocalDate date) {
        return date.isLeapYear();
    }

    //判断是否为当天
    public static boolean isToday(LocalDate date) {
        return getCurrentDate().equals(date);
    }

    public static void main(String[] args) {
        final LocalDateTime currentDateTime = getCurrentDateTime();
        final LocalDate currentDate = getCurrentDate();
        final LocalTime currentTime = getCurrentTime();
        System.out.println("获取当前时期： " + currentDateTime);
        System.out.println("获取当前日期： " + currentDate);
        System.out.println("获取当前时间： " + currentTime);
        System.out.println("获取当前毫秒数： " + getEpochMilli(currentDateTime));

        final String dateTimeStr = getCurrentDateTimeFormatter(DATE_LOW);
        getCurrentFormatterTimeShort();
        getCurrentFormatterDateTime();
        getCurrentFormatterDateTimeLong();
        getCurrentFormatterTime();
        final LocalDate parseLocalDate = parseLocalDate("2012-02-20", DATE);
        final LocalDateTime parseLocalDateTime = parseLocalDateTime("2019-03-12 12:33:22", DATE_TIME);
        final LocalTime parseLocalTime = parseLocalTime("09:33:11", TIME);
        System.out.println("指定格式的当前日期的时期：" + dateTimeStr);
        System.out.println("将日期转为Date: " + parseLocalDate);
        System.out.println("将日期时间转为DateTime: " + parseLocalDateTime);
        System.out.println("将时间转为Time: " + parseLocalTime);
        final boolean leapYear = isLeapYear(parseLocalDate);
        System.out.println("2020年是不是闰年？ " + leapYear);
        final long weeks = intervalWeeks(parseLocalDate, currentDate);
        System.out.printf("%s距离现在间隔%s周\n", parseLocalDate, weeks);
        System.out.printf("是不是当天？ %s \n", isToday(currentDate));
    }
 }