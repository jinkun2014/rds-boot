package me.jinkun.rds.core;

/**
 * @author JinKun
 * @date 2017-11-14
 * @time 10:34
 */
public class DBConstant {
    public static String getDriver(String dialect) {
        String driver = "";
        if ("MYSQL".equals(dialect)) {
            driver = "com.mysql.jdbc.Driver";
        }
        if ("SQLSERVER".equals(dialect)) {
            driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        }
        if ("ORACLE".equals(dialect)) {
            driver = "oracle.jdbc.driver.OracleDriver";
        }

        if ("".equals(driver)) {
            throw new RuntimeException("请配置正确的dialect,例如：MYSQL/SQLSERVER/ORACLE");
        }
        return driver;
    }
}
