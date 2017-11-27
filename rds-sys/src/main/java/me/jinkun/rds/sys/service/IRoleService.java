package me.jinkun.rds.sys.service;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 *
 * 角色-业务接口
 * @author JinKun
 * @date 2017-11-27
 * @time 17:04
 */
public interface IRoleService {

    /**
     * 根据id查询角色
     * @param id
     * @param fields
     * @return
     */
    Optional<Role> loadByPK(Long id, Set<String> fields);

    /**
     * 查询角色列表
     * @param role 角色
     * @param sortSet 排序器
     * @param page 分页
     * @return
     */
    List<Role> loads(
        Role role,
        Set<String> fields,
        Set<ISort> sortSet,
        IPage page
    );

    /**
     * 查询角色记录数
     * @param role 角色
     * @return
     */
    int loadCount(Role role);

    /**
     * 新增/编辑角色
     * @param role
     * @return
     */
    boolean saveOrUpdate(Role role);

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(Set<Long> ids);

    /**
     * 根据角色返回资源
     * @param roleId
     * @return
     */
    Set<Long> getResourceIds(Long roleId);

    /**
     * 保存角色下的资源
     * @param id
     * @param resourceIds
     * @return
     */
    boolean saveResourceIds(Long id, Set<Long> resourceIds);
}