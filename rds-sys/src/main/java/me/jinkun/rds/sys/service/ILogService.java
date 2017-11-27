package me.jinkun.rds.sys.service;

import me.jinkun.rds.sys.entity.Log;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;


/**
 *
 * 日志-业务接口
 * @author JinKun
 * @date 2017-11-27
 * @time 20:27
 */
public interface ILogService {

    /**
     * 根据id查询日志
     * @param id
     * @param fields
     * @return
     */
    Optional<Log> loadByPK(Long id, Set<String> fields);

    /**
     * 查询日志列表
     * @param log 日志
     * @param sortSet 排序器
     * @param page 分页
     * @return
     */
    List<Log> loads(
        Log log,
        Set<String> fields,
        Set<ISort> sortSet,
        IPage page
    );

    /**
     * 查询日志记录数
     * @param log 日志
     * @return
     */
    int loadCount(Log log);

    /**
     * 新增/编辑日志
     * @param log
     * @return
     */
    boolean saveOrUpdate(Log log);

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(Set<Long> ids);

    /**
     *
     * @param file
     * @return
     */
    boolean importXls(MultipartFile file);

    String exportXls(Set<Long> ids);
}