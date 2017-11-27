package me.jinkun.rds.sys.service;

import me.jinkun.rds.sys.entity.UserRole;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import java.util.*;


/**
 *
 * 用户-角色-业务接口
 * @author JinKun
 * @date 2017-11-27
 * @time 17:07
 */
public interface IUserRoleService {

    /**
     * 根据id查询用户-角色
     * @param id
     * @param fields
     * @return
     */
    Optional<UserRole> loadByPK(Long id, Set<String> fields);

    /**
     * 查询用户-角色列表
     * @param userRole 用户-角色
     * @param sortSet 排序器
     * @param page 分页
     * @return
     */
    List<UserRole> loads(
        UserRole userRole,
        Set<String> fields,
        Set<ISort> sortSet,
        IPage page
    );

    /**
     * 查询用户-角色记录数
     * @param userRole 用户-角色
     * @return
     */
    int loadCount(UserRole userRole);

    /**
     * 新增/编辑用户-角色
     * @param userRole
     * @return
     */
    boolean saveOrUpdate(UserRole userRole);

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(Set<Long> ids);
}