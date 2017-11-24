package me.jinkun.rds.gen.web.form;

import me.jinkun.rds.engine.app.info.PackageInfo;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by Administrator on 2017/5/9.
 */
public class GenForm {

    //数据库表信息
    private String name;
    private String remarks;

    //分页信息
    private int page;
    private int rows;

    //数据源信息
    private String dialect;
    private String driver;
    private String username;
    private String password;
    private String url;

    //其它选项
    private String tables;
    private String noUI;
    private String searchColumns;
    private String noDisplayColumns;
    private String noDisplayColumnsInput;
    private String noNullColumns;

    //生成选项
    private String type;
    private String basePackage;
    private String module;
    private String user;

    private String realPath;
    private String downloadDir;


    private String entityInfo;
    private String fieldInfo;
    private PackageInfo packageInfo;


    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public String getNoUI() {
        return noUI;
    }

    public void setNoUI(String noUI) {
        this.noUI = noUI;
    }

    public String getSearchColumns() {
        return searchColumns;
    }

    public void setSearchColumns(String searchColumns) {
        this.searchColumns = searchColumns;
    }

    public String getNoDisplayColumns() {
        return noDisplayColumns;
    }

    public void setNoDisplayColumns(String noDisplayColumns) {
        this.noDisplayColumns = noDisplayColumns;
    }

    public String getNoDisplayColumnsInput() {
        return noDisplayColumnsInput;
    }

    public void setNoDisplayColumnsInput(String noDisplayColumnsInput) {
        this.noDisplayColumnsInput = noDisplayColumnsInput;
    }

    public String getNoNullColumns() {
        return noNullColumns;
    }

    public void setNoNullColumns(String noNullColumns) {
        this.noNullColumns = noNullColumns;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getDownloadDir() {
        return downloadDir;
    }

    public void setDownloadDir(String downloadDir) {
        this.downloadDir = downloadDir;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }


    public String getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(String entityInfo) {
        this.entityInfo = entityInfo;
    }

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }
}
