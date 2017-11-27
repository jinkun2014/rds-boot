package me.jinkun.rds.sys.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.jinkun.rds.sys.entity.User;

import java.util.Set;

/**
 * @author JinKun
 * @date 2017-11-25
 * @time 16:03
 */
@Getter
@Setter
@ToString
public class UserExtend extends User {
    private Set<Long> orgIds;
    private Set<Long> roleIds;
}
