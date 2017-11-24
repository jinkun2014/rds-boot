package me.jinkun.rds.sys.service;

import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.web.form.SysRoleForm;
import me.jinkun.rds.sys.web.result.SysRoleResult;

import java.util.List;

/**
 * @Description: 角色,数据库表为： sys_role<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public interface SysRoleService {

    /**
     * Description: 增加或更新，根据id判断 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysRoleResult> save(SysRoleForm form);


    /**
     * Description: 增加 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysRoleResult> add(SysRoleForm form);

    /**
     * Description: 更新 <br/>
     * 主键或其它条件<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysRoleResult> update(Long id,SysRoleForm form);

    /**
     * Description: 删除 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysRoleResult> delete(Long id);

    /**
     * Description: 删除多个 <br/>
     * ids: 1,2,3<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysRoleResult> deleteByIds(String ids);

    /**
     * Description: 查询 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysRoleResult> get(Long id);

    /**
     * Description: 查询列表 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<PageResponse<SysRoleResult>> listPage(PageRequest pageRequest, SysRoleForm form);

    RespResult<SysRoleResult> getResources(Long id);

    RespResult<SysRoleResult> saveResources(Long id, String ids);

    RespResult<List<SysRoleResult>> listAll(SysRoleForm form);
}