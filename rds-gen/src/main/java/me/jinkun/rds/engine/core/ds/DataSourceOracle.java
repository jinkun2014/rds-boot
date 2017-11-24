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
public class DataSourceOracle implements DataSource {
    @Override
    public List<Table> getTableList(Connection conn) throws SQLException {
        List<Table> tableList = new ArrayList<>();

        String sql = "SELECT * FROM user_tab_comments WHERE table_type='TABLE'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String TABLE_NAME = rs.getString("TABLE_NAME");
            String COMMENTS = rs.getString("COMMENTS");

            Table table = new Table(TABLE_NAME.toUpperCase(), COMMENTS == null ? "" : COMMENTS);
            tableList.add(table);

            System.out.println("数据库【oracle】:" + "TABLE_NAME ==>" + TABLE_NAME.toUpperCase() + "  COMMENTS==>" + COMMENTS);
        }
        rs.close();
        ps.close();
        return tableList;
    }

    @Override
    public List<Column> getColumnList(Connection conn, String tableName) throws SQLException {
        List<Column> columnList = new ArrayList<>();

        String sql =
                "SELECT utc.table_name,utc.column_name,utc.data_type,utc.data_length,utc.data_default,utc.nullable,ucc.comments,p.PRIMARY_KEY " +
                        "FROM  user_tab_columns utc " +
                        "LEFT JOIN user_col_comments ucc " + //--查询注释
                        "ON utc.table_name = ucc.table_name " +
                        "AND  utc.column_name = ucc.column_name " +
                        "LEFT JOIN " + //--查询主键
                        "( SELECT col.table_name table_name, col.column_name column_name, CASE con.constraint_type WHEN 'P' THEN    'true' ELSE	'false' END PRIMARY_KEY " +
                        "FROM user_constraints con,user_cons_columns col " +
                        "WHERE con.constraint_name = col.constraint_name " +
                        "AND con.constraint_type = 'P') p " +
                        "ON utc.column_name = p.column_name " +
                        "AND p.table_name = utc.table_name " +
                        "WHERE utc.table_name = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tableName);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            String COLUMN_NAME = rs.getString("COLUMN_NAME");
            String DATA_TYPE = rs.getString("DATA_TYPE");//VARCHAR2
            Integer DATA_LENGTH = Integer.valueOf(rs.getString("DATA_LENGTH"));//200

            String data_default = rs.getString("DATA_DEFAULT");
            String DATA_DEFAULT = data_default==null? "" : data_default;

            Boolean NULLABLE = "N".equals(rs.getString("NULLABLE")) ? false : true;

            String comments = rs.getString("COMMENTS");
            String COMMENTS = comments==null? "" : comments;

            Boolean PRIMARY_KEY = "true".equals(rs.getString("PRIMARY_KEY")) ? true : false;

            Column column = new Column();
            column.setColumnName(COLUMN_NAME.toUpperCase());
            column.setColumnType(DATA_TYPE);
            column.setColumnLength(DATA_LENGTH);
            column.setColumnDefault(DATA_DEFAULT);
            column.setColumnRemarks(COMMENTS);
            column.setNullable(NULLABLE);
            column.setPrimaryKey(PRIMARY_KEY);
            columnList.add(column);

            System.out.println("COLUMN_NAME ==>" + COLUMN_NAME + "  DATA_TYPE==>" + DATA_TYPE + "  DATA_LENGTH==>" + DATA_LENGTH + " NULLABLE==>" + NULLABLE + "  COMMENTS==>" + COMMENTS + " PRIMARY_KEY==>" + PRIMARY_KEY);
        }
        rs.close();
        ps.close();
        return columnList;
    }
}
