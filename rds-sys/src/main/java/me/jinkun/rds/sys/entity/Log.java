package me.jinkun.rds.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.jinkun.rds.core.poi.ExcelAttribute;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志-实体,数据库表为： sys_log
 *
 * @author JinKun
 * @date 2017-11-27
 * @time 20:27
 */
@Getter
@Setter
@ToString
public class Log implements Serializable {
    /**
     * 主键，column: id
     */
    private Long id;

    /**
     * 时间，column: time
     */
    @ExcelAttribute(columnName = "时间", column = "A")
    private Date time;

    /**
     * IP地址，column: ip
     */
    @ExcelAttribute(columnName = "IP地址", column = "B")
    private String ip;

    /**
     * 登录名，column: login_name
     */
    @ExcelAttribute(columnName = "登录名", column = "C")
    private String loginName;

    /**
     * 访问链接，column: url
     */
    private String url;

    /**
     * 访问的类，column: clazz
     */
    private String clazz;

    /**
     * 访问的方法，column: method
     */
    private String method;

    /**
     * 模块，column: moudle
     */
    private String moudle;

    /**
     * 功能，column: function
     */
    private String function;

}