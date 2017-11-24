package me.jinkun.rds.sys.web.result;

import me.jinkun.rds.core.base.BaseResult;

/**
 * @Description: 封装返回数据！ <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysRoleResourceResult extends BaseResult {

    //=====================实体属性=========================
    //主键id，数据库字段为：sys_role_resource.id
    private Long id;

    //角色id，数据库字段为：sys_role_resource.role_id
    private Long roleId;

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
