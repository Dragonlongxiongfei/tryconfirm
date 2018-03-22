package com.chenyou.admin.Utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Shell Li on 2017/12/29.
 */
public class StringTools {

    public static String addMaskToString(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length();
        Random random = new Random();
        int count = random.nextInt(length);
        if (count == 0)
            count = 1;
        for (int i = 0; i < count; i++) {
            int index = random.nextInt(length);
            str = str.replace(str.charAt(index), '*');
        }

        return str;
    }

    /**
     * 字符串头尾之前的字符全部星号遮挡
     * @param str
     * @return
     */
    public static String trimeHeadAndTail(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || i == (str.length() - 1)) {
                continue;
            }
            str = str.replace(str.charAt(i), '*');
        }
        return str;
    }

    public static String dateToString(Date date, String format) {
        String result = null;
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            result = sdf.format(date);
        } catch (Exception e){

        }

        return result;
    }

    public static Date stringToDate(String dateString, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dateString);
            return  date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(dateToString(new Date(), "yyyyMMDDHHmmss"));
        }
    }
}
