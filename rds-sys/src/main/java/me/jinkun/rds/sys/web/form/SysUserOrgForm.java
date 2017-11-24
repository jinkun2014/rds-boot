package me.jinkun.rds.sys.web.form;

import me.jinkun.rds.core.base.BaseForm;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 封装表单数据! <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysUserOrgForm extends BaseForm {

    //=====================实体属性=========================
    @ApiModelProperty(value = "主键id")
    //主键id，数据库字段为：sys_user_org.id
    private Long id;

    @ApiModelProperty(value = "用户id")
    //用户id，数据库字段为：sys_user_org.user_id
    private Long userId;

    @ApiModelProperty(value = "机构id")
    //机构id，数据库字段为：sys_user_org.org_id
    private Long orgId;

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

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return this.orgId;
    }

}
