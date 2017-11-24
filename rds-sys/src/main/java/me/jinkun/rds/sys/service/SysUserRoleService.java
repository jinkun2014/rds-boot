package me.jinkun.rds.sys.service;

import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.web.form.SysUserRoleForm;
import me.jinkun.rds.sys.web.result.SysUserRoleResult;

import java.util.List;

/**
 * @Description: 用户-角色,数据库表为： sys_user_role<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public interface SysUserRoleService {

    /**
     * Description: 增加或更新，根据id判断 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserRoleResult> save(SysUserRoleForm form);


    /**
     * Description: 增加 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserRoleResult> add(SysUserRoleForm form);

    /**
     * Description: 更新 <br/>
     * 主键或其它条件<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserRoleResult> update(Long id,SysUserRoleForm form);

    /**
     * Description: 删除 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserRoleResult> delete(Long id);

    /**
     * Description: 删除多个 <br/>
     * ids: 1,2,3<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserRoleResult> deleteByIds(String ids);

    /**
     * Description: 查询 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserRoleResult> get(Long id);

    /**
     * Description: 查询列表 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<PageResponse<SysUserRoleResult>> listPage(PageRequest pageRequest, SysUserRoleForm form);

    List<Long> getRoleIdsByUserId(Long id);

    void deleteByUserId(Long id);

    void deleteByUserIdList(List<Long> idList);
}