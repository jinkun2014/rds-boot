package me.jinkun.rds.engine.app.info;

/**
 * @Description: 字段信息 <br/>
 * @Autor: Created by jinkun on 2016/12/6.
 */
public class FieldInfo {
    //是否是主键
    public boolean primaryKey;
    public String remarks;
    public String modifier;
    public String type;
    public String columnType;
    public String name;
    public String columnName;
    public String defaultValue;
    public boolean search;
    public boolean noDisplay;//List页面不显示
    public boolean noDisplayInput;//Input 页面不显示
    public boolean noNull;

    public FieldInfo() {
    }

    public FieldInfo(boolean primaryKey, String remarks, String type, String columnType, String name, String columnName) {
        this.primaryKey = primaryKey;
        this.remarks = remarks;
        this.type = type;
        this.columnType = columnType;
        this.name = name;
        this.columnName = columnName;
        this.modifier = "private";
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public boolean isNoDisplay() {
        return noDisplay;
    }

    public void setNoDisplay(boolean noDisplay) {
        this.noDisplay = noDisplay;
    }

    public boolean isNoNull() {
        return noNull;
    }

    public void setNoNull(boolean noNull) {
        this.noNull = noNull;
    }

    public boolean isNoDisplayInput() {
        return noDisplayInput;
    }

    public void setNoDisplayInput(boolean noDisplayInput) {
        this.noDisplayInput = noDisplayInput;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
