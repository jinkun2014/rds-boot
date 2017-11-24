package me.jinkun.rds.sys.web.form;

import me.jinkun.rds.core.base.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import me.jinkun.rds.core.poi.ExcelAttribute;

/**
 * @Description: 封装表单数据! <br/>
 * @Autor: Created by Jin Kun on 2017-05-27.
 */
public class SysLogForm extends BaseForm {

    //=====================实体属性=========================
    @ApiModelProperty(value = "主键")
    //主键，数据库字段为：sys_log.id
    private Long id;

    @ExcelAttribute(columnName = "时间", column = "A")
    @ApiModelProperty(value = "时间")
    //时间，数据库字段为：sys_log.time
    private String time;

    @ExcelAttribute(columnName = "IP地址", column = "B")
    @ApiModelProperty(value = "IP地址")
    //IP地址，数据库字段为：sys_log.ip
    private String ip;

    @ExcelAttribute(columnName = "登录名", column = "C")
    @ApiModelProperty(value = "登录名")
    //登录名，数据库字段为：sys_log.login_name
    private String loginName;

    @ExcelAttribute(columnName = "访问链接", column = "D")
    @ApiModelProperty(value = "访问链接")
    //访问链接，数据库字段为：sys_log.url
    private String url;

    @ExcelAttribute(columnName = "访问的类", column = "E")
    @ApiModelProperty(value = "访问的类")
    //访问的类，数据库字段为：sys_log.clazz
    private String clazz;

    @ExcelAttribute(columnName = "访问的方法", column = "F")
    @ApiModelProperty(value = "访问的方法")
    //访问的方法，数据库字段为：sys_log.method
    private String method;

    @ExcelAttribute(columnName = "模块", column = "G")
    @ApiModelProperty(value = "模块")
    //模块，数据库字段为：sys_log.moudle
    private String moudle;

    @ExcelAttribute(columnName = "功能", column = "H")
    @ApiModelProperty(value = "功能")
    //功能，数据库字段为：sys_log.function
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
