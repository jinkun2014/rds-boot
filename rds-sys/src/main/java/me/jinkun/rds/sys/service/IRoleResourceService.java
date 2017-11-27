package me.jinkun.rds.sys.service;

import me.jinkun.rds.sys.entity.RoleResource;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import java.util.*;


/**
 *
 * 角色-资源-业务接口
 * @author JinKun
 * @date 2017-11-27
 * @time 18:06
 */
public interface IRoleResourceService {

    /**
     * 根据id查询角色-资源
     * @param id
     * @param fields
     * @return
     */
    Optional<RoleResource> loadByPK(Long id, Set<String> fields);

    /**
     * 查询角色-资源列表
     * @param roleResource 角色-资源
     * @param sortSet 排序器
     * @param page 分页
     * @return
     */
    List<RoleResource> loads(
        RoleResource roleResource,
        Set<String> fields,
        Set<ISort> sortSet,
        IPage page
    );

    /**
     * 查询角色-资源记录数
     * @param roleResource 角色-资源
     * @return
     */
    int loadCount(RoleResource roleResource);

    /**
     * 新增/编辑角色-资源
     * @param roleResource
     * @return
     */
    boolean saveOrUpdate(RoleResource roleResource);

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(Set<Long> ids);
}