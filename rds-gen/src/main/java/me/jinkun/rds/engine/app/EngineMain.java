package me.jinkun.rds.engine.app;


import me.jinkun.rds.engine.app.info.EntityInfo;
import me.jinkun.rds.engine.app.info.FieldInfo;
import me.jinkun.rds.engine.app.template.Template;
import me.jinkun.rds.engine.app.utils.UtilHump;
import me.jinkun.rds.engine.app.utils.UtilType;
import me.jinkun.rds.engine.core.DataManager;
import me.jinkun.rds.engine.core.entity.Column;
import me.jinkun.rds.engine.core.entity.Table;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2016/12/15.
 */
public class EngineMain {
    private DataManager dataManager;
    private Template template;
    private boolean searching;
    private Connection conn;
    private String user;
    private String createTime;
    private String time;
    private String templatesDir;
    private String outDir;
    private String basePackage;
    private String module;
    private Map tablesMap;
    private Map noUIMap;
    private Map searchColumnsMap;
    private Map noDisplayColumnsMap;
    private Map noDisplayColumnsInputMap;
    private Map noNullColumns;

    public EngineMain(Builder builder) {
        this.dataManager = builder.dataManager;
        this.template = builder.template;
    }

    public List<FieldInfo> getFieldListByTableName(String tableName) {
        List<Column> columnList = dataManager.getColumnList(tableName);
        return columnListToFieldList(columnList);
    }

    private FieldInfo columnListToFieldList(Column column) {
        boolean primaryKey = column.getPrimaryKey();
        String columnName = column.getColumnName();
        String columnType = column.getColumnType();
        String columnRemarks = column.getColumnRemarks();
        String columnDefault = column.getColumnDefault();
        FieldInfo fieldInfo = new FieldInfo();
        fieldInfo.setPrimaryKey(primaryKey);
        fieldInfo.setColumnName(columnName);
        //由于Mybatis没有datetime类型，而我MySql的时间类型用的datetime,这里转换为mybatis的timestamp
        fieldInfo.setColumnType(UtilType.getJdbcType(columnType));
        fieldInfo.setDefaultValue(columnDefault);
        fieldInfo.setRemarks(columnRemarks);
        if (columnName.startsWith("is_")) {
            columnName = columnName.substring(3);
        }
        String name = UtilHump.lineToHump(columnName, false, false);
        fieldInfo.setName(name);
        fieldInfo.setType(UtilType.getJavaType(columnType));
        fieldInfo.setModifier("private");
        return fieldInfo;
    }

    public List<FieldInfo> columnListToFieldList(List<Column> columnList) {
        List<FieldInfo> fieldInfoList = new ArrayList<>();
        for (Column column : columnList) {
            FieldInfo fieldInfo = columnListToFieldList(column);
            fieldInfoList.add(fieldInfo);
        }
        return fieldInfoList;
    }

    public List<EntityInfo> getEntityListByName(String name) {
        List<EntityInfo> entityInfoList = new ArrayList<>();

        //得到所有的表
        List<Table> tableList = dataManager.getTableList();

        if (tableList != null && tableList.size() > 0) {
            for (Table table : tableList) {
                if (name == null || "".equals(name.trim())) {
                    entityInfoList.add(tableToEntityInfo(table));
                } else {
                    if (table.getTableName().toUpperCase().contains(name.toUpperCase())) {
                        entityInfoList.add(tableToEntityInfo(table));
                    }
                }
            }
        }
        return entityInfoList;
    }

    private EntityInfo tableToEntityInfo(Table table) {
        //表信息
        String tableName = table.getTableName();
        String tableRemarks = table.getTableRemarks();

        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setTableName(tableName);
        entityInfo.setEntityName(UtilHump.lineToHump(tableName, true, true));
        entityInfo.setRemarks(tableRemarks);
        return entityInfo;
    }

    public static class Builder {
        private DataManager dataManager;
        private Template template;

        public Builder dataManager(DataManager dm) {
            this.dataManager = dm;
            return this;
        }

        public Builder template(Template tmp) {
            this.template = tmp;
            return this;
        }

        public EngineMain builder() {
            return new EngineMain(this);
        }
    }
}
