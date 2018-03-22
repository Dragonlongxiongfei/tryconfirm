package com.chenyou.admin.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {

    public static final String DATE_FORMAT_PATTERN_INT = "yyyyMMdd";

    public static int toSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static int toSeconds(Date date) {
        if (date != null) {
            return (int) (date.getTime() / 1000);
        }
        return 0;
    }


    public static Date floor(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date ceiling(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static String format(Date date, String formatString) {
        if (date == null) {
            date = new Date();
        }
        return new SimpleDateFormat(formatString).format(date);
    }

    public static int toInt(Date date) {
        if (date != null) {
            return Integer.parseInt(format(date, DATE_FORMAT_PATTERN_INT));
        }
        return 0;
    }

    public static Date now() {
        return new Date();
    }

}