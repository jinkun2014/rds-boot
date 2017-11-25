package me.jinkun.rds.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户-实体,数据库表为： sys_user
 *
 * @author JinKun
 * @date 2017-11-25
 * @time 15:46
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    /**
     * 主键id，column: id
     */
    private Long id;

    /**
     * 登录名，column: login_name
     */
    private String loginName;

    /**
     * 用户名，column: name
     */
    private String name;

    /**
     * 密码，column: password
     */
    private String password;

    /**
     * 性别，column: sex
     */
    private Integer sex;

    /**
     * 年龄，column: age
     */
    private Integer age;

    /**
     * 手机号，column: phone
     */
    private String phone;

    /**
     * 用户类别，column: user_type
     */
    private Integer userType;

    /**
     * 用户状态，column: status
     */
    private Integer status;

    /**
     * 删除标记，column: del_flag
     */
    private Boolean delFlag;

    /**
     * 更新时间，column: update_time
     */
    private Date updateTime;

    /**
     * 创建时间，column: create_time
     */
    private Date createTime;

}