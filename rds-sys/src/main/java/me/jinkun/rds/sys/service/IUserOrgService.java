package me.jinkun.rds.sys.service;

import me.jinkun.rds.sys.entity.UserOrg;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;

import java.util.*;


/**
 * 用户-机构-业务接口
 *
 * @author JinKun
 * @date 2017-11-25
 * @time 16:09
 */
public interface IUserOrgService {

    /**
     * 根据id查询用户-机构
     *
     * @param id
     * @param fields
     * @return
     */
    Optional<UserOrg> loadByPK(Long id, Set<String> fields);

    /**
     * 查询用户-机构列表
     *
     * @param userOrg 用户-机构
     * @param sortSet 排序器
     * @param page    分页
     * @return
     */
    List<UserOrg> loads(
            UserOrg userOrg,
            Set<String> fields,
            Set<ISort> sortSet,
            IPage page
    );

    /**
     * 查询用户-机构记录数
     *
     * @param userOrg 用户-机构
     * @return
     */
    int loadCount(UserOrg userOrg);

    /**
     * 新增/编辑用户-机构
     *
     * @param userOrg
     * @return
     */
    boolean saveOrUpdate(UserOrg userOrg);

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(Set<Long> ids);

    /**
     * 查询用户id集合
     * @param userOrg
     * @return
     */
    Set<Long> loadsUserId(UserOrg userOrg);
}