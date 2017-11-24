package me.jinkun.rds.sys.web.form;

import me.jinkun.rds.core.base.BaseForm;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 封装表单数据! <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysRoleResourceForm extends BaseForm {

    //=====================实体属性=========================
    @ApiModelProperty(value = "主键id")
    //主键id，数据库字段为：sys_role_resource.id
    private Long id;

    @ApiModelProperty(value = "角色id")
    //角色id，数据库字段为：sys_role_resource.role_id
    private Long roleId;

    @ApiModelProperty(value = "资源id")
    //资源id，数据库字段为：sys_role_resource.resource_id
    private Long resourceId;

    //=====================其它属性=========================


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getResourceId() {
        return this.resourceId;
    }

}
