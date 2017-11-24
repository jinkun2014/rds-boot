package me.jinkun.rds.engine.core.ds;


import me.jinkun.rds.engine.core.entity.Column;
import me.jinkun.rds.engine.core.entity.Table;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2017/2/10.
 */
public interface DataSource {
    List<Table> getTableList(Connection conn) throws SQLException;

    List<Column> getColumnList(Connection conn, String tableName) throws SQLException;
}
