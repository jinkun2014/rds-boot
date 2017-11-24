package me.jinkun.rds.sys.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * @Description: 日志,数据库表为： sys_log <br/>
 * @Autor: Created by Jin Kun on 2017-06-01.
 */
public class SysLog implements Serializable{
    //主键，数据库字段为：sys_log.id
    private Long id;

    //时间，数据库字段为：sys_log.time
    private Date time;

    //IP地址，数据库字段为：sys_log.ip
    private String ip;

    //登录名，数据库字段为：sys_log.login_name
    private String loginName;

    //访问链接，数据库字段为：sys_log.url
    private String url;

    //访问的类，数据库字段为：sys_log.clazz
    private String clazz;

    //访问的方法，数据库字段为：sys_log.method
    private String method;

    //模块，数据库字段为：sys_log.moudle
    private String moudle;

    //功能，数据库字段为：sys_log.function
    private String function;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
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

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFunction() {
        return this.function;
    }

}