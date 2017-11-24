package me.jinkun.rds.sys.web.result;

import me.jinkun.rds.core.base.BaseResult;
import me.jinkun.rds.core.poi.ExcelAttribute;

/**
 * @Description: 封装返回数据！ <br/>
 * @Autor: Created by Jin Kun on 2017-05-27.
 */
public class SysLogResult extends BaseResult {

    //=====================实体属性=========================
    //主键，数据库字段为：sys_log.id
    private Long id;

    //时间，数据库字段为：sys_log.time
    @ExcelAttribute(columnName = "时间", column = "A")
    private String time;

    //IP地址，数据库字段为：sys_log.ip
    @ExcelAttribute(columnName = "IP地址", column = "B")
    private String ip;

    //登录名，数据库字段为：sys_log.login_name
    @ExcelAttribute(columnName = "登录名", column = "C")
    private String loginName;

    //访问链接，数据库字段为：sys_log.url
    @ExcelAttribute(columnName = "访问链接", column = "D")
    private String url;

    //访问的类，数据库字段为：sys_log.clazz
    @ExcelAttribute(columnName = "访问的类", column = "E")
    private String clazz;

    //访问的方法，数据库字段为：sys_log.method
    @ExcelAttribute(columnName = "访问的方法", column = "F")
    private String method;

    //模块，数据库字段为：sys_log.moudle
    @ExcelAttribute(columnName = "模块", column = "G")
    private String moudle;

    //功能，数据库字段为：sys_log.function
    @ExcelAttribute(columnName = "功能", column = "H")
    private String function;

    //=====================其它属性=========================


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getClazz() {
        return this.clazz;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMoudle(String moudle) {
        this.moudle = moudle;
    }

    public String getMoudle() {
        return this.moudle;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
