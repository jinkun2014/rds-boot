package me.jinkun.rds.sys.domain;

import java.io.Serializable;


/**
 * @Description: 图标,数据库表为： sys_icon <br/>
 * @Autor: Created by Jin Kun on 2017-06-01.
 */
public class SysIcon implements Serializable{
    //主键，数据库字段为：sys_icon.id
    private Long id;

    //名称，数据库字段为：sys_icon.name
    private String name;

    //链接，数据库字段为：sys_icon.url
    private String url;

    //类型，数据库字段为：sys_icon.type
    private String type;

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