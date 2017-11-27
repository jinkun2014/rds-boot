package me.jinkun.rds.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;
import java.util.Date;
import java.io.Serializable;

/**
 * 角色-实体,数据库表为： sys_role
 *
 * @author JinKun
 * @date 2017-11-27
 * @time 17:04
 */
@Getter
@Setter
@ToString
public class Role implements Serializable {
    /**
     * 主键id，column: id
     */
    private Long id;

    /**
     * 角色名，column: name
     */
    private String name;

    /**
     * 排序号，column: seq
     */
    private Integer seq;

    /**
     * 简介，column: description
     */
    private String description;

    /**
     * 状态，column: status
     */
    private Integer status;

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