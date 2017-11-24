package me.jinkun.rds.sys.service;

import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.core.resp.Tree;
import me.jinkun.rds.sys.web.form.SysOrgForm;
import me.jinkun.rds.sys.web.result.SysOrgResult;
import java.util.List;

/**
 * @Description: 组织机构,数据库表为： sys_org<br/>
 * @Autor: Created by Jin Kun on 2017-05-24.
 */
public interface SysOrgService {

    /**
     * Description: 增加或更新，根据id判断 <br/>
     * Autor: Created by Jin Kun on 2017-05-24.
     */
    RespResult<SysOrgResult> save(SysOrgForm form);


    /**
     * Description: 增加 <br/>
     * Autor: Created by Jin Kun on 2017-05-24.
     */
    RespResult<SysOrgResult> add(SysOrgForm form);

    /**
     * Description: 更新 <br/>
     * 主键或其它条件<p>
     * Autor: Created by Jin Kun on 2017-05-24.
     */
    RespResult<SysOrgResult> update(Long id,SysOrgForm form);

    /**
     * Description: 删除 <br/>
     * Autor: Created by Jin Kun on 2017-05-24.
     */
    RespResult<SysOrgResult> delete(Long id);

    /**
     * Description: 删除多个 <br/>
     * ids: 1,2,3<p>
     * Autor: Created by Jin Kun on 2017-05-24.
     */
    RespResult<SysOrgResult> deleteByIds(String ids);

    /**
     * Description: 查询 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-24.
     */
    RespResult<SysOrgResult> get(Long id);

    /**
     * Description: 查询列表 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-24.
     */
    RespResult<PageResponse<SysOrgResult>> listPage(PageRequest pageRequest, SysOrgForm form);

    RespResult<List<Tree>> listTree(SysOrgForm form);
}