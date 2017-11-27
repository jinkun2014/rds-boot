package me.jinkun.rds.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

/**
 * 角色-资源-实体,数据库表为： sys_role_resource
 *
 * @author JinKun
 * @date 2017-11-27
 * @time 18:06
 */
@Getter
@Setter
@ToString
public class RoleResource implements Serializable {
    /**
     * 主键id，column: id
     */
    private Long id;

    /**
     * 角色id，column: role_id
     */
    private Long roleId;

    /**
     * 资源id，column: resource_id
     */
    private Long resourceId;

}