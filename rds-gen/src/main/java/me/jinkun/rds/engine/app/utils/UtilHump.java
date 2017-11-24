package me.jinkun.rds.engine.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2016/12/1.
 */
public class UtilHump {
    /**
     * @param firstCharUpper 首字母是否大写，区别类名和属性名
     * @Description: 带下划线字符串转驼峰字符串 <br/>
     * @Autor: Created by jinkun on 2016/12/1.
     */
    public static String lineToHump(String str, boolean firstCharUpper, boolean removeFirstWord) {
        if (removeFirstWord) {
            int index = str.indexOf("_");
            if (index != -1) {
                str = str.substring(index + 1);
            }
        }
        Pattern linePattern = Pattern.compile("_(\\w)");
        if (firstCharUpper) {
            str = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
        }
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(lineToHump("role", false,true));
    }
}
