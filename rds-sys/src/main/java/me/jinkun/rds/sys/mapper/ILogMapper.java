package me.jinkun.rds.sys.mapper;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * 日志-Dao接口
 *
 * @author JinKun
 * @date 2017-11-27
 * @time 20:27
 */
@Mapper
@Repository
public interface ILogMapper {

    /**
     * 新增日志
     *
     * @param log
     */
    int insert(@Param("log") Log log);

    /**
     * 编辑日志
     *
     * @param log
     */
    int update(@Param("log") Log log);

    /**
     * 删除日志
     *
     * @param log
     */
    int delete(@Param("log") Log log);

    /**
     * 根据ID批量删除
     *
     * @param ids
     */
    int deleteByIds(@Param("ids") Set<Long> ids);

    /**
     * 查询日志记录数
     *
     * @param log
     * @return
     */
    int loadCount(@Param("log") Log log);

    /**
     * 根据id查询日志
     *
     * @param id     日志id
     * @param fields 查询属性
     * @return
     */
    Log loadByPK(
            @Param("id") Long id,
            @Param("fields") Set<String> fields
    );

    /**
     * 查询日志
     *
     * @param log     日志
     * @param fields  查询属性
     * @param sortSet 排序器
     * @param page    分页
     * @return
     */
    List<Log> loads(
            @Param("log") Log log,
            @Param("fields") Set<String> fields,
            @Param("sorts") Set<ISort> sortSet,
            @Param("page") IPage page
    );

    /**
     * 根据id查询
     *
     * @param ids
     * @return
     */
    List<Log> loadByIds(
            @Param("fields") Set<String> fields,
            @Param("ids") Set<Long> ids);
}