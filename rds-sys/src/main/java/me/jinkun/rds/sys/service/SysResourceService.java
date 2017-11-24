package me.jinkun.rds.sys.service;

import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.core.resp.Tree;
import me.jinkun.rds.sys.domain.SysResource;
import me.jinkun.rds.sys.web.form.SysResourceForm;
import me.jinkun.rds.sys.web.result.SysResourceResult;

import java.util.List;

/**
 * @Description: 资源, 数据库表为： sys_resource<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public interface SysResourceService {

    /**
     * Description: 增加或更新，根据id判断 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysResourceResult> save(SysResourceForm form);


    /**
     * Description: 增加 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysResourceResult> add(SysResourceForm form);

    /**
     * Description: 更新 <br/>
     * 主键或其它条件<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysResourceResult> update(Long id, SysResourceForm form);

    /**
     * Description: 删除 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysResourceResult> delete(Long id);

    /**
     * Description: 删除多个 <br/>
     * ids: 1,2,3<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysResourceResult> deleteByIds(String ids);

    /**
     * Description: 查询 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysResourceResult> get(Long id);

    /**
     * Description: 查询列表 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<PageResponse<SysResourceResult>> listPage(PageRequest pageRequest, SysResourceForm form);

    RespResult<List<Tree>> listTree(SysResourceForm form);

    List<SysResource> getByUserIdAndPid(Long userId, Long pid);
}