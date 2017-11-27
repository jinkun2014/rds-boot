package me.jinkun.rds.sys.mapper;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * 用户-角色-Dao接口
 *
 * @author JinKun
 * @date 2017-11-27
 * @time 17:07
 */
@Mapper
@Repository
public interface IUserRoleMapper {

    /**
     * 新增用户-角色
     *
     * @param userRole
     */
    int insert(@Param("userRole") UserRole userRole);

    /**
     * 编辑用户-角色
     *
     * @param userRole
     */
    int update(@Param("userRole") UserRole userRole);

    /**
     * 删除用户-角色
     *
     * @param userRole
     */
    int delete(@Param("userRole") UserRole userRole);

    /**
     * 根据ID批量删除
     *
     * @param ids
     */
    int deleteByIds(@Param("ids") Set<Long> ids);

    /**
     * 查询用户-角色记录数
     *
     * @param userRole
     * @return
     */
    int loadCount(@Param("userRole") UserRole userRole);

    /**
     * 根据id查询用户-角色
     *
     * @param id     用户-角色id
     * @param fields 查询属性
     * @return
     */
    UserRole loadByPK(
            @Param("id") Long id,
            @Param("fields") Set<String> fields
    );

    /**
     * 查询用户-角色
     *
     * @param userRole 用户-角色
     * @param fields   查询属性
     * @param sortSet  排序器
     * @param page     分页
     * @return
     */
    List<UserRole> loads(
            @Param("userRole") UserRole userRole,
            @Param("fields") Set<String> fields,
            @Param("sorts") Set<ISort> sortSet,
            @Param("page") IPage page
    );

    /**
     * 根据用户id查询角色id列表
     *
     * @param userRole 用户id
     * @return
     */
    Set<Long> loadsRoleId(@Param("userRole") UserRole userRole);

    /**
     * 批量插入用户-角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    int insertBatch(
            @Param("userId") Long userId,
            @Param("roleIds") Set<Long> roleIds
    );

}