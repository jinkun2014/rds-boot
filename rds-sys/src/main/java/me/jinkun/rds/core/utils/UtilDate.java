package me.jinkun.rds.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: 日期辅助类 <br/>
 * Autor: Created by jinkun on 2017/3/23.
 */
public class UtilDate {
    public final static String YYYYMMDD = "yyyyMMdd";
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private UtilDate() {
    }

    /**
     * 格式化日期为执行的格式
     *
     * @param date     日期
     * @param parttern 格式
     * @return 字符串
     */
    public static String formDate(Date date, String parttern) {
        SimpleDateFormat sdf = new SimpleDateFormat(parttern);
        return sdf.format(date);
    }
}
