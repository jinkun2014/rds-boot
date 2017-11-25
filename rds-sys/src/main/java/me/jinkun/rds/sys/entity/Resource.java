package me.jinkun.rds.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源-实体,数据库表为： sys_resource
 *
 * @author JinKun
 * @date 2017-11-25
 * @time 15:26
 */
@Getter
@Setter
@ToString
public class Resource implements Serializable {
    /**
     * 主键，column: id
     */
    private Long id;

    /**
     * 资源名称，column: name
     */
    private String name;

    /**
     * 资源路径，column: url
     */
    private String url;

    /**
     * 打开方式 ajax,iframe，column: open_mode
     */
    private String openMode;

    /**
     * 资源介绍，column: description
     */
    private String description;

    /**
     * 资源图标，column: icon
     */
    private String icon;

    /**
     * 父级资源id，column: pid
     */
    private Long pid;

    /**
     * 排序，column: seq
     */
    private Integer seq;

    /**
     * 状态，column: status
     */
    private Integer status;

    /**
     * 资源类别，column: resource_type
     */
    private Integer resourceType;

    /**
     * 是否是叶子，column: is_leaf
     */
    private Boolean isLeaf;

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