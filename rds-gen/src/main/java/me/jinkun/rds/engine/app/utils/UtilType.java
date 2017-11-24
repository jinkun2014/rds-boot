package me.jinkun.rds.engine.app.utils;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2016/12/1.
 */
public class UtilType {

    public static String getJavaType(String columnType) {
        String javaType = "Void";
        if ("BIGINT".equals(columnType)) {
            javaType = "Long";
        }
        if ("VARCHAR".equals(columnType) || "TEXT".equals(columnType) || "LONGTEXT".equals(columnType) || "NVARCHAR".equals(columnType) || "CHAR".equals(columnType)) {
            javaType = "String";
        }
        if ("DATE".equals(columnType) || "TIMESTAMP".equals(columnType) || "DATETIME".equals(columnType)) {
            javaType = "Date";
        }
        if ("INTEGER".equals(columnType) || "BIT".equals(columnType) || "INT".equals(columnType)) {
            javaType = "Integer";
        }
        if ("TINYINT".equals(columnType)) {
            javaType = "Boolean";
        }
        return javaType;
    }

    public static String getJdbcType(String columnType) {
        String jdbcType = "";
        if ("DATETIME".equals(columnType)) {
            jdbcType = "TIMESTAMP";
        } else if ("INT".equals(columnType)) {
            jdbcType = "INTEGER";
        } else if ("TEXT".equals(columnType)) {
            jdbcType = "LONGVARCHAR";
        } else {
            jdbcType = columnType;
        }
        return jdbcType;
    }
}
