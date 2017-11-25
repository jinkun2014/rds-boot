package me.jinkun.rds.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

/**
 * 用户-机构-实体,数据库表为： sys_user_org
 *
 * @author JinKun
 * @date 2017-11-25
 * @time 16:09
 */
@Getter
@Setter
@ToString
public class UserOrg implements Serializable {
    /**
     * 主键id，column: id
     */
    private Long id;

    /**
     * 用户id，column: user_id
     */
    private Long userId;

    /**
     * 机构id，column: org_id
     */
    private Long orgId;

}