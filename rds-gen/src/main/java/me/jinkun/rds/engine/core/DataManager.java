package me.jinkun.rds.engine.core;

import me.jinkun.rds.engine.app.utils.UtilJdbc;
import me.jinkun.rds.engine.core.ds.DataSource;
import me.jinkun.rds.engine.core.ds.DataSourceMySql;
import me.jinkun.rds.engine.core.ds.DataSourceOracle;
import me.jinkun.rds.engine.core.ds.DataSourceSqlserver;
import me.jinkun.rds.engine.core.entity.Column;
import me.jinkun.rds.engine.core.entity.Table;

import java.sql.Connection;
import java.util.List;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by PCuser on 2017/11/3.
 */
public class DataManager {
    private String driver;
    private String url;
    private String username;
    private String password;

    public DataManager(Builder builder) {
        this.driver = builder.driver;
        this.url = builder.url;
        this.username = builder.username;
        this.password = builder.password;
    }

    public List<Table> getTableList() {
        try {
            DataSource ds = getDs(driver);
            //得到连接
            Connection conn = UtilJdbc.getConnect(driver, url, username, password);
            List<Table> tableList = ds.getTableList(conn);
            UtilJdbc.close(conn);
            return tableList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Column> getColumnList(String tableName) {
        DataSource ds = getDs(driver);
        try {
            //得到连接
            Connection conn = UtilJdbc.getConnect(driver, url, username, password);
            List<Column> columnList = ds.getColumnList(conn, tableName);
            UtilJdbc.close(conn);
            return columnList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private DataSource getDs(String driverName) {
        DataSource ds = null;
        if (driverName.contains("mysql")) {
            ds = new DataSourceMySql();
        } else if (driverName.contains("oracle")) {
            ds = new DataSourceOracle();
        } else {
            ds = new DataSourceSqlserver();
        }
        return ds;
    }

    public static class Builder {
        private String driver;
        private String url;
        private String username;
        private String password;

        public Builder driver(String driver) {
            this.driver = driver;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public DataManager builder() {
            return new DataManager(this);
        }
    }
}
