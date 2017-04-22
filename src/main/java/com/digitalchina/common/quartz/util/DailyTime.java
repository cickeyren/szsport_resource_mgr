package com.digitalchina.common.quartz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yang_ on 2017/4/21.
 */
public class DailyTime {
    public synchronized static String getYesterday(Date date,String format) {
        SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern(format);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-1);//昨天
        return sdf1.format(calendar.getTime());
    }

    public synchronized static String format(Date date,String format) {
        SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern(format);
        return sdf1.format(date);
    }
}
