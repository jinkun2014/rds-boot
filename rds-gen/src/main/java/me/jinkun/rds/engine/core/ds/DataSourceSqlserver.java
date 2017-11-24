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
 * Autor: Created by PCuser on 2017/11/2.
 */
public class DataSourceSqlserver implements DataSource {
    @Override
    public List<Table> getTableList(Connection conn) throws SQLException {
        List<Table> tableList = new ArrayList<>();
        String sql = "select 表名=CONVERT(varchar(1000), a.name),描述=CONVERT(varchar(1000), b.value) from (Select * FROM SysObjects Where XType='U') a left join (select * from sys.extended_properties where minor_id=0) b on a.id=b.major_id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String TABLE_NAME = rs.getString("表名");
            String COMMENTS = rs.getString("描述");

            Table table = new Table(TABLE_NAME, COMMENTS == null ? "" : COMMENTS);
            tableList.add(table);

            System.out.println("数据库【sqlserver】:" + "TABLE_NAME ==>" + TABLE_NAME + "  COMMENTS==>" + COMMENTS);
        }
        rs.close();
        ps.close();
        return tableList;
    }

    @Override
    public List<Column> getColumnList(Connection conn, String tableName) throws SQLException {
        List<Column> columnList = new ArrayList<>();

        String sql = "SELECT " +
                "    序号=CONVERT(varchar(1000),a.colorder)," +
                "    列名=CONVERT(varchar(1000),a.name)," +
                "    是否种子标识=case when COLUMNPROPERTY( a.id,a.name,'IsIdentity')=1 then 'yes'else '' end," +
                "    主键=case when exists(SELECT 1 FROM sysobjects where xtype='PK' and parent_obj=a.id and name in (" +
                "        SELECT name FROM sysindexes WHERE indid in(" +
                "            SELECT indid FROM sysindexkeys WHERE id = a.id AND colid=a.colid" +
                "        ))) then 'yes' else '' end," +
                "    类型=CONVERT(varchar(1000),b.name)," +
                "    设置的字节数=a.length," +
                "    长度=COLUMNPROPERTY(a.id,a.name,'PRECISION')," +
                "    小数位数=isnull(COLUMNPROPERTY(a.id,a.name,'Scale'),0)," +
                "    空=case when a.isnullable=1 then 'yes'else '' end," +
                "    默认值=CONVERT(varchar(1000),isnull(e.text,''))," +
                "    描述=CONVERT(varchar(1000),isnull(g.[value],''))" +
                "FROM syscolumns a" +
                "    left join systypes b on a.xusertype=b.xusertype" +
                "    inner join sysobjects d on a.id=d.id and d.xtype='U' and d.name<>'dtproperties'" +
                "    left join syscomments e on a.cdefault=e.id" +
                "    left join sys.extended_properties  g on A.ID=g.major_id   AND a.COLID=g.minor_id   " +
                "where d.name='" + tableName + "' " +
                "order by a.id,a.colorder";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            String 列名 = rs.getString("列名");

            String 类型 = rs.getString("类型").toUpperCase();

            Integer 长度 = Integer.valueOf(rs.getString("长度"));//200

            String 默认值 = rs.getString("默认值");
            默认值 = 默认值 != null ? 默认值 : "";

            String 描述 = rs.getString("描述");
            描述 = 描述 != null ? 描述 : "";

            Boolean 空 = "yes".equals(rs.getString("空")) ? true : false;

            Boolean 主键 = "yes".equals(rs.getString("主键")) ? true : false;

            Column column = new Column();
            column.setColumnName(列名);
            column.setColumnType(类型);
            column.setColumnLength(长度);
            column.setColumnDefault(默认值);
            column.setColumnRemarks(描述);
            column.setNullable(空);
            column.setPrimaryKey(主键);
            columnList.add(column);

            System.out.println("COLUMN_NAME ==>" + 列名 + "  DATA_TYPE==>" + 类型 + "  DATA_LENGTH==>" + 默认值 + " NULLABLE==>" + 空 + "  COMMENTS==>" + 描述 + " PRIMARY_KEY==>" + 主键);
        }
        rs.close();
        ps.close();
        return columnList;
    }
}
