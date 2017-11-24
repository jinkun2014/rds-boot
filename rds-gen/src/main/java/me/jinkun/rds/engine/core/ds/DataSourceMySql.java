package me.jinkun.rds.engine.core.ds;


import me.jinkun.rds.engine.core.entity.Column;
import me.jinkun.rds.engine.core.entity.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2017/2/10.
 */
public class DataSourceMySql implements DataSource {

    @Override
    public List<Table> getTableList(Connection conn) throws SQLException {
        List<Table> tableList = new ArrayList<>();

        String sql = "SHOW TABLE STATUS";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String TABLE_NAME = rs.getString("NAME");
            String COMMENTS = rs.getString("COMMENT");

            Table table = new Table(TABLE_NAME, COMMENTS == null ? "" : COMMENTS);
            tableList.add(table);

            System.out.println("数据库【mysql】:" + "TABLE_NAME ==>" + TABLE_NAME + "  COMMENTS==>" + COMMENTS);
        }
        rs.close();
        ps.close();
        return tableList;
    }

    @Override
    public List<Column> getColumnList(Connection conn, String tableName) throws SQLException {
        List<Column> columnList = new ArrayList<>();

        String sql = "SHOW FULL COLUMNS FROM " + tableName;
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String COLUMN_NAME = rs.getString("FIELD");
            String DATA_TYPE = null;
            Integer DATA_LENGTH = null;

            //这个主要是做列类型和列长度切分
            String TYPE = rs.getString("TYPE");//varchar(200)或datetime
            String[] typeArr = TYPE.split("\\(");
            if (typeArr.length > 1) {
                DATA_TYPE = typeArr[0].toUpperCase();
                try {
                    //TODO
                    DATA_LENGTH = null;//Integer.valueOf(typeArr[1].substring(0, typeArr[1].length() - 1));
                } catch (Exception e) {
                    DATA_LENGTH = null;
                    e.printStackTrace();
                }
            } else {
                DATA_TYPE = typeArr[0].toUpperCase();
                DATA_LENGTH = null;
            }

            String data_default = rs.getString("DEFAULT");
            String DATA_DEFAULT = data_default == null ? "" : data_default;

            Boolean NULLABLE = "NO".equals(rs.getString("NULL")) ? false : true;

            String comments = rs.getString("COMMENT");
            String COMMENTS = comments == null ? "" : comments;

            Boolean PRIMARY_KEY = "PRI".equals(rs.getString("KEY")) ? true : false;

            Column column = new Column();
            column.setColumnName(COLUMN_NAME);
            column.setColumnType(DATA_TYPE);
            column.setColumnLength(DATA_LENGTH);
            column.setColumnDefault(DATA_DEFAULT);
            column.setColumnRemarks(COMMENTS);
            column.setNullable(NULLABLE);
            column.setPrimaryKey(PRIMARY_KEY);
            columnList.add(column);

            System.out.println("数据库【mysql】:" + "COLUMN_NAME ==>" + COLUMN_NAME + "  DATA_TYPE==>" + DATA_TYPE + " DATA_DEFAULT==>" + DATA_DEFAULT + "  DATA_LENGTH==>" + DATA_LENGTH + " NULLABLE==>" + NULLABLE + "  COMMENTS==>" + COMMENTS + " PRIMARY_KEY==>" + PRIMARY_KEY);
        }
        rs.close();
        ps.close();
        return columnList;
    }
}
