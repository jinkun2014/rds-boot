package me.jinkun.rds.sys.service;

import me.jinkun.rds.sys.entity.Resource;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.model.Tree;

import java.util.*;


/**
 *
 * 资源-业务接口
 * @author JinKun
 * @date 2017-11-25
 * @time 15:26
 */
public interface IResourceService {

    /**
     * 根据id查询资源
     * @param id
     * @param fields
     * @return
     */
    Optional<Resource> loadByPK(Long id, Set<String> fields);

    /**
     * 查询资源列表
     * @param resource 资源
     * @param sortSet 排序器
     * @param page 分页
     * @return
     */
    List<Resource> loads(
            Resource resource,
            Set<String> fields,
            Set<ISort> sortSet,
            IPage page
    );

    /**
     * 查询资源记录数
     * @param resource 资源
     * @return
     */
    int loadCount(Resource resource);

    /**
     * 新增/编辑资源
     * @param resource
     * @return
     */
    boolean saveOrUpdate(Resource resource);

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(Set<Long> ids);

    List<Tree> listTree();
}