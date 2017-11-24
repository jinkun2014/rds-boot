package me.jinkun.rds.engine.app.info;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 类信息 <br/>
 * @Autor: Created by jinkun on 2016/12/6.
 */
public class EntityInfo {
    public PackageInfo packageInfo;
    public List<String> importList;
    public String remarks;
    public String user;
    public String createTime;//2017/10/10
    public String time;//15:30
    public String entityName;
    public String tableName;
    public FieldInfo primaryKey;
    public String webPath;
    public List<FieldInfo> fieldInfoList;
    private boolean searching;
    private int displayCount;

    //文件名
    private String daoName;
    private String daoImplName;
    private String daoMapperName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;

    public EntityInfo(){}

    public EntityInfo(Builder builder) {
        this.packageInfo = builder.packageInfo;
        this.importList = builder.importList;
        this.remarks = builder.remarks;
        this.user = builder.user;
        this.createTime = builder.createTime;
        this.time = builder.time;
        this.entityName = builder.entityName;
        this.tableName = builder.tableName;
        this.primaryKey = builder.primaryKey;
        this.fieldInfoList = builder.fieldInfoList;
        this.daoName = builder.daoName;
        this.daoImplName = builder.daoImplName;
        this.daoMapperName = builder.daoMapperName;
        this.serviceName = builder.serviceName;
        this.serviceImplName = builder.serviceImplName;
        this.controllerName = builder.controllerName;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public List<String> getImportList() {
        return importList;
    }

    public void setImportList(List<String> importList) {
        this.importList = importList;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public FieldInfo getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(FieldInfo primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getWebPath() {
        return webPath;
    }

    public void setWebPath(String webPath) {
        this.webPath = webPath;
    }

    public List<FieldInfo> getFieldInfoList() {
        return fieldInfoList;
    }

    public void setFieldInfoList(List<FieldInfo> fieldInfoList) {
        this.fieldInfoList = fieldInfoList;
    }

    public boolean isSearching() {
        return searching;
    }

    public void setSearching(boolean searching) {
        this.searching = searching;
    }

    public int getDisplayCount() {
        return displayCount;
    }

    public void setDisplayCount(int displayCount) {
        this.displayCount = displayCount;
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String daoName) {
        this.daoName = daoName;
    }

    public String getDaoImplName() {
        return daoImplName;
    }

    public void setDaoImplName(String daoImplName) {
        this.daoImplName = daoImplName;
    }

    public String getDaoMapperName() {
        return daoMapperName;
    }

    public void setDaoMapperName(String daoMapperName) {
        this.daoMapperName = daoMapperName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public static class Builder {
        //表
        private String tableName;

        //注释
        private String remarks;
        private String createTime;
        public String time;
        private String user;

        //实体类信息
        private String entityName;
        private String daoName;
        private String daoImplName;
        private String daoMapperName;
        private String serviceName;
        private String serviceImplName;
        private String controllerName;

        //属性
        private FieldInfo primaryKey;
        private List<FieldInfo> fieldInfoList;

        //包信息
        private PackageInfo packageInfo;

        //Import
        private List<String> importList = new ArrayList<>();

        private String daoPrefix;
        private String daoSuffix;
        private String daoImplPrefix;
        private String daoImplSuffix;
        private String daoMapperPrefix;
        private String daoMapperSuffix;
        private String servicePrefix;
        private String serviceSuffix;
        private String serviceImplPrefix;
        private String serviceImplSuffix;
        private String controllerPrefix;
        private String controllerSuffix;

        public Builder(String tableName, String remarks, String entityName, String user, List<FieldInfo> fieldInfoList, PackageInfo packageInfo) {
            this.tableName = tableName;
            this.remarks = remarks;
            this.user = user;
            this.entityName = entityName;
            this.fieldInfoList = fieldInfoList;
            this.packageInfo = packageInfo;
        }

        public Builder entityName(String entityName) {
            this.entityName = entityName;
            return this;
        }

        public Builder daoPrefix(String daoPrefix) {
            this.daoPrefix = daoPrefix;
            return this;
        }

        public Builder daoSuffix(String daoSuffix) {
            this.daoSuffix = daoSuffix;
            return this;
        }

        public Builder daoImplPrefix(String daoImplPrefix) {
            this.daoImplPrefix = daoImplPrefix;
            return this;
        }

        public Builder daoImplSuffix(String daoImplSuffix) {
            this.daoImplSuffix = daoImplSuffix;
            return this;
        }

        public Builder daoMapperPrefix(String daoMapperPrefix) {
            this.daoMapperPrefix = daoMapperPrefix;
            return this;
        }

        public Builder daoMapperSuffix(String daoMapperSuffix) {
            this.daoMapperSuffix = daoMapperSuffix;
            return this;
        }

        public Builder servicePrefix(String servicePrefix) {
            this.servicePrefix = servicePrefix;
            return this;
        }

        public Builder serviceSuffix(String serviceSuffix) {
            this.serviceSuffix = serviceSuffix;
            return this;
        }

        public Builder serviceImplPrefix(String serviceImplPrefix) {
            this.serviceImplPrefix = serviceImplPrefix;
            return this;
        }

        public Builder serviceImplSuffix(String serviceImplSuffix) {
            this.serviceImplSuffix = serviceImplSuffix;
            return this;
        }

        public Builder controllerPrefix(String controllerPrefix) {
            this.controllerPrefix = controllerPrefix;
            return this;
        }

        public Builder controllerSuffix(String controllerSuffix) {
            this.controllerSuffix = controllerSuffix;
            return this;
        }

        public EntityInfo builder() {
            if (entityName == null || "".equals(entityName.trim())) {
                throw new RuntimeException("entityName必须设置");
            }

            daoName = configure(daoName, daoPrefix, daoSuffix, "Dao");
            daoImplName = configure(daoImplName, daoImplPrefix, daoImplSuffix, "DaoImpl");
            daoMapperName = configure(daoMapperName, daoMapperPrefix, daoMapperSuffix, "Mapper");
            serviceName = configure(serviceName, servicePrefix, serviceSuffix, "Service");
            serviceImplName = configure(serviceImplName, serviceImplPrefix, serviceImplSuffix, "ServiceImpl");
            controllerName = configure(controllerName, controllerPrefix, controllerSuffix, "Controller");

            if (tableName == null || "".equals(tableName)) {
                throw new RuntimeException("tableName必须设置");
            }

            this.createTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            this.time = new SimpleDateFormat("HH:mm").format(new Date());

            for (FieldInfo fieldInfo : fieldInfoList) {
                //配置Import
                if (fieldInfo.getType().equals("Date")) {
                    importList.add("java.util.Date");
                }

                //配置主键
                if (fieldInfo.primaryKey) {
                    this.primaryKey = fieldInfo;
                }
            }

            return new EntityInfo(this);
        }

        private String configure(String name, String prefix, String suffix, String defalult) {
            String str = entityName;
            if (name == null || "".equals(name.trim())) {
                if (prefix != null) {
                    str = prefix + str;
                }
                if (suffix != null) {
                    str = str + suffix;
                } else {
                    str = str + defalult;
                }
            }
            return str;
        }

    }
}
