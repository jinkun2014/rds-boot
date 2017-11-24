package me.jinkun.rds.sys.service;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.entity.Icon;

import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 *
 * 图标-业务接口
 * @author JinKun
 * @date 2017-11-23
 * @time 20:29
 */
public interface IIconService {

    /**
     * 根据id查询图标
     * @param id
     * @param fields
     * @return
     */
    Optional<Icon> loadByPK(Long id, Set<String> fields);

    /**
     * 查询图标列表
     * @param icon 图标
     * @param sortSet 排序器
     * @param page 分页
     * @return
     */
    List<Icon> loads(
        Icon icon,
        Set<String> fields,
        Set<ISort> sortSet,
        IPage page
    );

    /**
     * 查询图标记录数
     * @param icon 图标
     * @return
     */
    int loadCount(Icon icon);

    /**
     * 新增/编辑图标
     * @param icon
     * @return
     */
    boolean saveOrUpdate(Icon icon);

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(Set<Long> ids);

    /**
     * 初始化图表
     * @return
     */
    boolean load();
}