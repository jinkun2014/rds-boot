package me.jinkun.rds.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2017/3/23.
 */
public class UtilDate {
    public final static String yyyyMMdd = "yyyyMMdd";
    public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static String formDate(Date date, String parttern) {
        SimpleDateFormat sdf = new SimpleDateFormat(parttern);
        return sdf.format(date);
    }
}
