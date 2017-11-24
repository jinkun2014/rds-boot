package me.jinkun.rds.sys.web.form;

import me.jinkun.rds.core.base.BaseForm;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 封装表单数据! <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysUserRoleForm extends BaseForm {

    //=====================实体属性=========================
    @ApiModelProperty(value = "主键id")
    //主键id，数据库字段为：sys_user_role.id
    private Long id;

    @ApiModelProperty(value = "用户id")
    //用户id，数据库字段为：sys_user_role.user_id
    private Long userId;

    @ApiModelProperty(value = "角色id")
    //角色id，数据库字段为：sys_user_role.role_id
    private Long roleId;

    //=====================其它属性=========================


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

}
