package me.jinkun.rds.sys.service;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.entity.Org;
import me.jinkun.rds.sys.model.Tree;

import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 *
 * 组织机构-业务接口
 * @author JinKun
 * @date 2017-11-24
 * @time 11:37
 */
public interface IOrgService {

    /**
     * 根据id查询组织机构
     * @param id
     * @param fields
     * @return
     */
    Optional<Org> loadByPK(Long id, Set<String> fields);

    /**
     * 查询组织机构列表
     * @param org 组织机构
     * @param sortSet 排序器
     * @param page 分页
     * @return
     */
    List<Org> loads(
            Org org,
            Set<String> fields,
            Set<ISort> sortSet,
            IPage page
    );

    /**
     * 查询组织机构记录数
     * @param org 组织机构
     * @return
     */
    int loadCount(Org org);

    /**
     * 新增/编辑组织机构
     * @param org
     * @return
     */
    boolean saveOrUpdate(Org org);

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(Set<Long> ids);

    List<Tree> listTree();
}