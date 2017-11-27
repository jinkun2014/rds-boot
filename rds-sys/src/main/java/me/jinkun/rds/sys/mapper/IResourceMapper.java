package me.jinkun.rds.sys.mapper;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * 资源-Dao接口
 *
 * @author JinKun
 * @date 2017-11-25
 * @time 15:26
 */
@Mapper
@Repository
public interface IResourceMapper {

    /**
     * 新增资源
     *
     * @param resource
     */
    int insert(@Param("resource") Resource resource);

    /**
     * 编辑资源
     *
     * @param resource
     */
    int update(@Param("resource") Resource resource);

    /**
     * 删除资源
     *
     * @param resource
     */
    int delete(@Param("resource") Resource resource);

    /**
     * 根据ID批量删除
     *
     * @param ids
     */
    int deleteByIds(@Param("ids") Set<Long> ids);

    /**
     * 查询资源记录数
     *
     * @param resource
     * @return
     */
    int loadCount(@Param("resource") Resource resource);

    /**
     * 根据id查询资源
     *
     * @param id     资源id
     * @param fields 查询属性
     * @return
     */
    Resource loadByPK(
            @Param("id") Long id,
            @Param("fields") Set<String> fields
    );

    /**
     * 查询资源
     *
     * @param resource 资源
     * @param fields   查询属性
     * @param sortSet  排序器
     * @param page     分页
     * @return
     */
    List<Resource> loads(
            @Param("resource") Resource resource,
            @Param("fields") Set<String> fields,
            @Param("sorts") Set<ISort> sortSet,
            @Param("page") IPage page
    );

    List<Resource> getByUserIdAndPid(
            @Param("fields") Set<String> fields,
            @Param("userId") Long userId,
            @Param("pid") Long pid);
}