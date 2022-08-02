package com.hailong.fireshare.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 获取系统当前时间
     *
     * @return 系统当前时间
     */
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = smp.format(date);
        return format;
    }
}
