package com.xct.media.queuing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/5/7 18:17.
 * @Description:
 */
public class DataUtils {

    public static String[] getFirstAndLastOfWeek(String dateStr) {
        String date[] = new String[2];
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周开始日期
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        // 所在周结束日期
        String date2 = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

        date[0] = date1;
        date[1] = date2;

        return date;
    }
}
