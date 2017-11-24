package me.jinkun.rds.sys.service;

import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.web.form.SysRoleResourceForm;
import me.jinkun.rds.sys.web.result.SysRoleResourceResult;
import java.util.List;

/**
 * @Description: 角色-资源,数据库表为： sys_role_resource<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public interface SysRoleResourceService {

    /**
     * Description: 增加或更新，根据id判断 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysRoleResourceResult> save(SysRoleResourceForm form);


    /**
     * Description: 增加 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysRoleResourceResult> add(SysRoleResourceForm form);

    /**
     * Description: 更新 <br/>
     * 主键或其它条件<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysRoleResourceResult> update(Long id,SysRoleResourceForm form);

    /**
     * Description: 删除 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysRoleResourceResult> delete(Long id);

    /**
     * Description: 删除多个 <br/>
     * ids: 1,2,3<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysRoleResourceResult> deleteByIds(String ids);

    /**
     * Description: 查询 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysRoleResourceResult> get(Long id);

    /**
     * Description: 查询列表 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<PageResponse<SysRoleResourceResult>> listPage(PageRequest pageRequest, SysRoleResourceForm form);

    List<Long> getResourcesIdsByRoleId(Long id);

    void deleteByRoleId(Long id);
}