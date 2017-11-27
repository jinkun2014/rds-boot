package me.jinkun.rds.sys.mapper;

import me.jinkun.rds.sys.entity.Role;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.Date;
import java.util.*;


/**
 *
 * 角色-Dao接口
 * @author JinKun
 * @date 2017-11-27
 * @time 17:04
 */
@Mapper
@Repository
public interface IRoleMapper {

    /**
     * 新增角色
     * @param role
     */
    int insert(@Param("role") Role role);

    /**
     * 编辑角色
     * @param role
     */
    int update(@Param("role") Role role);

    /**
     * 删除角色
     * @param role
     */
    int delete(@Param("role") Role role);

    /**
     * 根据ID批量删除
     * @param ids
     */
    int deleteByIds(@Param("ids") Set<Long> ids);

    /**
     * 查询角色记录数
     * @param role
     * @return
     */
    int loadCount(@Param("role") Role role);

    /**
     * 根据id查询角色
     * @param id 角色id
     * @param fields 查询属性
     * @return
     */
    Role loadByPK(
        @Param("id") Long id,
        @Param("fields") Set<String> fields
    );

    /**
     * 查询角色
     * @param role 角色
     * @param fields 查询属性
     * @param sortSet 排序器
     * @param page 分页
     * @return
     */
    List<Role> loads(
        @Param("role") Role role,
        @Param("fields") Set<String> fields,
        @Param("sorts") Set<ISort> sortSet,
        @Param("page") IPage page
    );
}