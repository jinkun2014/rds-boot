package me.jinkun.rds.sys.service;

import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.domain.SysUser;
import me.jinkun.rds.sys.web.form.SysUserForm;
import me.jinkun.rds.sys.web.result.SysUserResult;
import java.util.List;

/**
 * @Description: 用户,数据库表为： sys_user<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public interface SysUserService {

    /**
     * Description: 增加或更新，根据id判断 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserResult> save(SysUserForm form);


    /**
     * Description: 增加 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserResult> add(SysUserForm form);

    /**
     * Description: 更新 <br/>
     * 主键或其它条件<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserResult> update(Long id,SysUserForm form);

    /**
     * Description: 删除 <br/>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserResult> delete(Long id);

    /**
     * Description: 删除多个 <br/>
     * ids: 1,2,3<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserResult> deleteByIds(String ids);

    /**
     * Description: 查询 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<SysUserResult> get(Long id);

    /**
     * Description: 查询列表 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-25.
     */
    RespResult<PageResponse<SysUserResult>> listPage(PageRequest pageRequest, SysUserForm form);

    List<SysUser> findListByIds(List<Long> userIdList);

    SysUser findByLoginNameAndPassword(String loginName,String password);
}