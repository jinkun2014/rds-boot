package me.jinkun.rds.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

/**
 * 用户-角色-实体,数据库表为： sys_user_role
 *
 * @author JinKun
 * @date 2017-11-27
 * @time 17:07
 */
@Getter
@Setter
@ToString
public class UserRole implements Serializable {
    /**
     * 主键id，column: id
     */
    private Long id;

    /**
     * 用户id，column: user_id
     */
    private Long userId;

    /**
     * 角色id，column: role_id
     */
    private Long roleId;

}