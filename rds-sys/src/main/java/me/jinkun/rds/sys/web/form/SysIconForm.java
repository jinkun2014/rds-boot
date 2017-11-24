package me.jinkun.rds.sys.web.form;

import me.jinkun.rds.core.base.BaseForm;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 封装表单数据! <br/>
 * @Autor: Created by Jin Kun on 2017-06-01.
 */
public class SysIconForm extends BaseForm {

    //=====================实体属性=========================
    @ApiModelProperty(value = "主键")
    //主键，数据库字段为：sys_icon.id
    private Long id;

    @ApiModelProperty(value = "名称")
    //名称，数据库字段为：sys_icon.name
    private String name;

    @ApiModelProperty(value = "链接")
    //链接，数据库字段为：sys_icon.url
    private String url;

    @ApiModelProperty(value = "类型")
    //类型，数据库字段为：sys_icon.type
    private String type;

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

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
