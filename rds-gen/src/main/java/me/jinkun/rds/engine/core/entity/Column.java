package me.jinkun.rds.engine.core.entity;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2016/12/15.
 */
public class Column {
    //列名
    private String columnName;
    //列类型
    private String columnType;
    //列注释
    private String columnRemarks;
    //列长度
    private Integer columnLength;
    //列默认值
    private String columnDefault;
    //列是否为NULL
    private Boolean nullable;
    //列是否是主键
    private Boolean primaryKey;

    public Column() {
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnRemarks() {
        return columnRemarks;
    }

    public void setColumnRemarks(String columnRemarks) {
        this.columnRemarks = columnRemarks;
    }

    public Integer getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(Integer columnLength) {
        this.columnLength = columnLength;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
}
