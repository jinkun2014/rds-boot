package me.jinkun.rds.sys.web.result;

import me.jinkun.rds.core.base.BaseResult;

/**
 * @Description: 封装返回数据！ <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysUserRoleResult extends BaseResult {

    //=====================实体属性=========================
    //主键id，数据库字段为：sys_user_role.id
    private Long id;

    //用户id，数据库字段为：sys_user_role.user_id
    private Long userId;

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
