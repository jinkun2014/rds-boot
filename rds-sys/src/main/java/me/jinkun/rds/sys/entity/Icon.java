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
 * @date 2017-11-23
 * @time 22:00
 */
@Getter
@Setter
@ToString
public class Icon implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 链接
     */
    private String url;

    /**
     * 类型
     */
    private String type;

    /**
     * 删除标记
     */
    private Boolean delete;

    /**
     * 更新时间
     */
    private Date gmtUpdate;

    /**
     * 创建时间
     */
    private Date gmtCreate;

}