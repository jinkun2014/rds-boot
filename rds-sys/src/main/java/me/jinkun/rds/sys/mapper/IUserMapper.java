package me.jinkun.rds.sys.mapper;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * 用户-Dao接口
 *
 * @author JinKun
 * @date 2017-11-25
 * @time 15:46
 */
@Mapper
@Repository
public interface IUserMapper {

    /**
     * 新增用户
     *
     * @param user
     */
    int insert(@Param("user") User user);

    /**
     * 编辑用户
     *
     * @param user
     */
    int update(@Param("user") User user);

    /**
     * 删除用户
     *
     * @param user
     */
    int delete(@Param("user") User user);

    /**
     * 根据ID批量删除
     *
     * @param ids
     */
    int deleteByIds(@Param("ids") Set<Long> ids);

    /**
     * 查询用户记录数
     *
     * @param user
     * @return
     */
    int loadCount(@Param("user") User user);

    /**
     * 根据id查询用户
     *
     * @param id     用户id
     * @param fields 查询属性
     * @return
     */
    User loadByPK(
            @Param("id") Long id,
            @Param("fields") Set<String> fields
    );

    /**
     * 查询用户
     *
     * @param user    用户
     * @param fields  查询属性
     * @param sortSet 排序器
     * @param page    分页
     * @return
     */
    List<User> loads(
            @Param("user") User user,
            @Param("fields") Set<String> fields,
            @Param("sorts") Set<ISort> sortSet,
            @Param("page") IPage page
    );

    List<User> loadsByIds(
            @Param("ids") Set<Long> ids,
            @Param("fields") Set<String> fields,
            @Param("sorts") Set<ISort> sortSet,
            @Param("page") IPage page
    );
}