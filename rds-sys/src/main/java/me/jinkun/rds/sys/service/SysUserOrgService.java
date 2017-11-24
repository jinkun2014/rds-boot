package me.jinkun.rds.sys.service;

import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.domain.SysUser;
import me.jinkun.rds.sys.domain.SysUserOrg;
import me.jinkun.rds.sys.web.form.SysUserOrgForm;
import me.jinkun.rds.sys.web.result.SysUserOrgResult;

import java.util.List;

/**
 * @Description: 用户-机构,数据库表为： sys_user_org<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public interface SysUserOrgService {

    /**
     * Description: 增加或更新，根据id判断 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserOrgResult> save(SysUserOrgForm form);


    /**
     * Description: 增加 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserOrgResult> add(SysUserOrgForm form);

    /**
     * Description: 更新 <br/>
     * 主键或其它条件<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserOrgResult> update(Long id, SysUserOrgForm form);

    /**
     * Description: 删除 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserOrgResult> delete(Long id);

    /**
     * Description: 删除多个 <br/>
     * ids: 1,2,3<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserOrgResult> deleteByIds(String ids);

    /**
     * Description: 查询 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserOrgResult> get(Long id);

    /**
     * Description: 查询列表 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<PageResponse<SysUserOrgResult>> listPage(PageRequest pageRequest, SysUserOrgForm form);

    List<SysUser> findUserListByOrgId(Long orgId);

    List<Long> findUserIdListByOrgId(Long orgId);

    SysUserOrg getByUserId(Long id);

    void deleteByUserIdList(List<Long> idList);

    void deleteByOrgIdList(List<Long> idList);

    void deleteByUserId(Long id);
}