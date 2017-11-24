package me.jinkun.rds.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 组织机构-实体,数据库表为： sys_org
 *
 * @author JinKun
 * @date 2017-11-24
 * @time 13:56
 */
@Getter
@Setter
@ToString
public class Org implements Serializable {
    /**
     * 主键id，column: id
     */
    private Long id;

    /**
     * 组织名，column: name
     */
    private String name;

    /**
     * 地址，column: address
     */
    private String address;

    /**
     * 编号，column: code
     */
    private String code;

    /**
     * 图标，column: icon
     */
    private String icon;

    /**
     * 父级主键，column: pid
     */
    private Long pid;

    /**
     * 叶子节点，column: is_leaf
     */
    private Boolean isLeaf;

    /**
     * 排序，column: seq
     */
    private Integer seq;

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