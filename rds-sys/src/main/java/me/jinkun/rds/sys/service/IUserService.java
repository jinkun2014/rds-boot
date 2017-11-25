package me.jinkun.rds.sys.service;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.entity.User;
import me.jinkun.rds.sys.model.UserExtend;

import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * 用户-业务接口
 *
 * @author JinKun
 * @date 2017-11-25
 * @time 15:46
 */
public interface IUserService {

    /**
     * 根据id查询用户
     *
     * @param id
     * @param fields
     * @return
     */
    Optional<User> loadByPK(Long id, Set<String> fields);

    /**
     * 查询用户列表
     *
     * @param user    用户
     * @param sortSet 排序器
     * @param page    分页
     * @return
     */
    List<User> loads(
            User user,
            Set<String> fields,
            Set<ISort> sortSet,
            IPage page
    );

    /**
     * 查询用户记录数
     *
     * @param user 用户
     * @return
     */
    int loadCount(User user);

    /**
     * 新增/编辑用户
     *
     * @param user
     * @return
     */
    boolean saveOrUpdate(User user);

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(Set<Long> ids);


    /**
     * 查询用户列表
     * @param ids
     * @param fields
     * @param sortSet
     * @param page
     * @return
     */
    List<User> loadsByIds(
            Set<Long> ids,
            Set<String> fields,
            Set<ISort> sortSet,
            IPage page
    );

    /**
     * 根据id查询用户
     *
     * @param id
     * @param fields
     * @return
     */
    Optional<UserExtend> loadUserExtendByPK(Long id, Set<String> fields);

    /**
     * 新增/编辑用户
     *
     * @param userExtend
     * @return
     */
    boolean saveOrUpdate(UserExtend userExtend);
}