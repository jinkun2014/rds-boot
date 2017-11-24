package me.jinkun.rds.engine.core.entity;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2016/12/15.
 */
public class Table {
    //表名
    private String tableName;
    //表注释
    private String tableRemarks;

    public Table() {
    }

    public Table(String tableName, String tableRemarks) {
        this.tableName = tableName;
        this.tableRemarks = tableRemarks;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableRemarks() {
        return tableRemarks;
    }

    public void setTableRemarks(String tableRemarks) {
        this.tableRemarks = tableRemarks;
    }
}
