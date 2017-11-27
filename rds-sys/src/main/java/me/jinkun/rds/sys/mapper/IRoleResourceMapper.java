package me.jinkun.rds.sys.mapper;

import me.jinkun.rds.sys.entity.RoleResource;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.*;


/**
 * 角色-资源-Dao接口
 *
 * @author JinKun
 * @date 2017-11-27
 * @time 18:06
 */
@Mapper
@Repository
public interface IRoleResourceMapper {

    /**
     * 新增角色-资源
     *
     * @param roleResource
     */
    int insert(@Param("roleResource") RoleResource roleResource);

    /**
     * 编辑角色-资源
     *
     * @param roleResource
     */
    int update(@Param("roleResource") RoleResource roleResource);

    /**
     * 删除角色-资源
     *
     * @param roleResource
     */
    int delete(@Param("roleResource") RoleResource roleResource);

    /**
     * 根据ID批量删除
     *
     * @param ids
     */
    int deleteByIds(@Param("ids") Set<Long> ids);

    /**
     * 查询角色-资源记录数
     *
     * @param roleResource
     * @return
     */
    int loadCount(@Param("roleResource") RoleResource roleResource);

    /**
     * 根据id查询角色-资源
     *
     * @param id     角色-资源id
     * @param fields 查询属性
     * @return
     */
    RoleResource loadByPK(
            @Param("id") Long id,
            @Param("fields") Set<String> fields
    );

    /**
     * 查询角色-资源
     *
     * @param roleResource 角色-资源
     * @param fields       查询属性
     * @param sortSet      排序器
     * @param page         分页
     * @return
     */
    List<RoleResource> loads(
            @Param("roleResource") RoleResource roleResource,
            @Param("fields") Set<String> fields,
            @Param("sorts") Set<ISort> sortSet,
            @Param("page") IPage page
    );

    /**
     * 查询资源id集合
     *
     * @param roleResource
     * @return
     */
    Set<Long> loadsResourceId(@Param("roleResource") RoleResource roleResource);

    /**
     *
     * @param roleId
     * @param resourceIds
     * @return
     */
    int insertBatch(
            @Param("roleId") Long roleId,
            @Param("resourceIds") Set<Long> resourceIds
    );

    /**
     * 根据roleIds批量删除
     *
     * @param roleIds
     */
    int deleteByRoleIds(@Param("roleIds") Set<Long> roleIds);

    /**
     * 根据resourceIds批量删除
     *
     * @param resourceIds
     */
    int deleteByResourceIds(@Param("resourceIds") Set<Long> resourceIds);
}