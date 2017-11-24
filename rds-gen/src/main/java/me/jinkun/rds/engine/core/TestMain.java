package me.jinkun.rds.engine.core;

import me.jinkun.rds.engine.core.ds.DataSource;
import me.jinkun.rds.engine.core.ds.DataSourceMySql;
import me.jinkun.rds.engine.core.ds.DataSourceOracle;
import me.jinkun.rds.engine.core.ds.DataSourceSqlserver;
import me.jinkun.rds.engine.core.entity.Column;
import me.jinkun.rds.engine.core.entity.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2017/2/10.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {

//        testDsMySql();
        //testDsOracle();
        testDsSqlserver();
    }

    private static void testDsMySql() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/rds_sys_live", "root", "root");
        DataSource ds = new DataSourceMySql();

        List<Table> tableList = ds.getTableList(conn);
        for (Table t : tableList) {
            List<Column> columnList = ds.getColumnList(conn, t.getTableName());
            for (Column c : columnList) {
                System.out.println("c -> " + c.getColumnName());
            }
        }
        conn.close();
    }

    private static void testDsOracle() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "gzzjdfgs", "123");
        DataSource ds = new DataSourceOracle();

        List<Table> tableList = ds.getTableList(conn);
        for (Table t : tableList) {
            List<Column> columnList = ds.getColumnList(conn, t.getTableName());
            for (Column c : columnList) {

            }
        }
        conn.close();
    }

    private static void testDsSqlserver() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=zhpt_wx", "sa", "19931110");
        DataSource ds = new DataSourceSqlserver();

        List<Table> tableList = ds.getTableList(conn);
        for (Table t : tableList) {
            List<Column> columnList = ds.getColumnList(conn, t.getTableName());
            for (Column c : columnList) {
                System.out.println("c -> " + c.getColumnName());
            }
        }
        conn.close();
    }
}
