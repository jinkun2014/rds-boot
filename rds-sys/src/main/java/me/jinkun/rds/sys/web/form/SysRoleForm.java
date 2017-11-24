package me.jinkun.rds.sys.web.form;

import me.jinkun.rds.core.base.BaseForm;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 封装表单数据! <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysRoleForm extends BaseForm {

    //=====================实体属性=========================
    @ApiModelProperty(value = "主键id")
    //主键id，数据库字段为：sys_role.id
    private Long id;

    @ApiModelProperty(value = "角色名")
    //角色名，数据库字段为：sys_role.name
    private String name;

    @ApiModelProperty(value = "排序号")
    //排序号，数据库字段为：sys_role.seq
    private Integer seq;

    @ApiModelProperty(value = "简介")
    //简介，数据库字段为：sys_role.description
    private String description;

    @ApiModelProperty(value = "状态")
    //状态，数据库字段为：sys_role.status
    private Integer status;

    @ApiModelProperty(value = "删除标记")
    //删除标记，数据库字段为：sys_role.del_flag
    private Integer delFlag;

    @ApiModelProperty(value = "更新时间")
    //更新时间，数据库字段为：sys_role.update_time
    private String updateTime;

    @ApiModelProperty(value = "创建时间")
    //创建时间，数据库字段为：sys_role.create_time
    private String createTime;

    //=====================其它属性=========================


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() {
        return this.delFlag;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

}
