package me.jinkun.rds.sys.web.form;

import me.jinkun.rds.core.base.BaseForm;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 封装表单数据! <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysUserForm extends BaseForm {

    //=====================实体属性=========================
    @ApiModelProperty(value = "主键id")
    //主键id，数据库字段为：sys_user.id
    private Long id;

    @ApiModelProperty(value = "登录名")
    //登录名，数据库字段为：sys_user.login_name
    private String loginName;

    @ApiModelProperty(value = "用户名")
    //用户名，数据库字段为：sys_user.name
    private String name;

    @ApiModelProperty(value = "密码")
    //密码，数据库字段为：sys_user.password
    private String password;

    @ApiModelProperty(value = "性别")
    //性别，数据库字段为：sys_user.sex
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    //年龄，数据库字段为：sys_user.age
    private Integer age;

    @ApiModelProperty(value = "手机号")
    //手机号，数据库字段为：sys_user.phone
    private String phone;

    @ApiModelProperty(value = "用户类别")
    //用户类别，数据库字段为：sys_user.user_type
    private Integer userType;

    @ApiModelProperty(value = "用户状态")
    //用户状态，数据库字段为：sys_user.status
    private Integer status;

    //=====================其它属性=========================
    @ApiModelProperty(value = "部门id", required = true)
    private Long orgId;

    @ApiModelProperty(value = "角色id", required = true)
    private Long[] roleId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long[] getRoleId() {
        return roleId;
    }

    public void setRoleId(Long[] roleId) {
        this.roleId = roleId;
    }
}
