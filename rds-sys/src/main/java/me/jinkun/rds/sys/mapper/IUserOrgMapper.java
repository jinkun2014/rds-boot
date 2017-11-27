package me.jinkun.rds.sys.mapper;

import me.jinkun.rds.sys.entity.UserOrg;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.*;


/**
 * 用户-机构-Dao接口
 *
 * @author JinKun
 * @date 2017-11-25
 * @time 16:09
 */
@Mapper
@Repository
public interface IUserOrgMapper {

    /**
     * 新增用户-机构
     *
     * @param userOrg
     */
    int insert(@Param("userOrg") UserOrg userOrg);

    /**
     * 编辑用户-机构
     *
     * @param userOrg
     */
    int update(@Param("userOrg") UserOrg userOrg);

    /**
     * 删除用户-机构
     *
     * @param userOrg
     */
    int delete(@Param("userOrg") UserOrg userOrg);

    /**
     * 根据ID批量删除
     *
     * @param ids
     */
    int deleteByIds(@Param("ids") Set<Long> ids);

    /**
     * 查询用户-机构记录数
     *
     * @param userOrg
     * @return
     */
    int loadCount(@Param("userOrg") UserOrg userOrg);

    /**
     * 根据id查询用户-机构
     *
     * @param id     用户-机构id
     * @param fields 查询属性
     * @return
     */
    UserOrg loadByPK(
            @Param("id") Long id,
            @Param("fields") Set<String> fields
    );

    /**
     * 查询用户-机构
     *
     * @param userOrg 用户-机构
     * @param fields  查询属性
     * @param sortSet 排序器
     * @param page    分页
     * @return
     */
    List<UserOrg> loads(
            @Param("userOrg") UserOrg userOrg,
            @Param("fields") Set<String> fields,
            @Param("sorts") Set<ISort> sortSet,
            @Param("page") IPage page
    );

    /**
     * 查询用户id集合
     *
     * @param userOrg
     * @return
     */
    Set<Long> loadsUserId(@Param("userOrg") UserOrg userOrg);

    /**
     * 查询组织id集合
     *
     * @param userOrg
     * @return
     */
    Set<Long> loadsOrgId(@Param("userOrg") UserOrg userOrg);

    /**
     * 批量插入
     *
     * @param userId
     * @param orgIds
     * @return
     */
    int insertBatch(
            @Param("userId") Long userId,
            @Param("orgIds") Set<Long> orgIds
    );

    /**
     * 根据UserIds批量删除
     *
     * @param userIds
     */
    int deleteByUserIds(@Param("userIds") Set<Long> userIds);

    /**
     * 根据OrgIds批量删除
     *
     * @param orgIds
     */
    int deleteByOrgIds(@Param("orgIds") Set<Long> orgIds);
}