package me.jinkun.rds.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 图标-实体,数据库表为： sys_icon
 *
 * @author JinKun
 * @date 2017-11-25
 * @time 09:57
 */
@Getter
@Setter
@ToString
public class Icon implements Serializable {
    /**
     * 主键，column: id
     */
    private Long id;

    /**
     * 名称，column: name
     */
    private String name;

    /**
     * 链接，column: url
     */
    private String url;

    /**
     * 类型，column: type
     */
    private String type;

    /**
     * 删除标记，column: del_mark
     */
    private Boolean delMark;

    /**
     * 更新时间，column: update_time
     */
    private Date updateTime;

    /**
     * 创建时间，column: create_time
     */
    private Date createTime;

}