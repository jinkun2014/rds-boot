package me.jinkun.rds.sys.service;

import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.web.form.SysLogForm;
import me.jinkun.rds.sys.web.result.SysLogResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Description: 日志,数据库表为： sys_log<br/>
 * @Autor: Created by Jin Kun on 2017-05-27.
 */
public interface SysLogService {

    /**
     * Description: 增加或更新，根据id判断 <br/>
     * Autor: Created by Jin Kun on 2017-05-27.
     */
    RespResult<SysLogResult> save(SysLogForm form);


    /**
     * Description: 增加 <br/>
     * Autor: Created by Jin Kun on 2017-05-27.
     */
    RespResult<SysLogResult> add(SysLogForm form);

    /**
     * Description: 更新 <br/>
     * 主键或其它条件<p>
     * Autor: Created by Jin Kun on 2017-05-27.
     */
    RespResult<SysLogResult> update(Long id, SysLogForm form);

    /**
     * Description: 删除 <br/>
     * Autor: Created by Jin Kun on 2017-05-27.
     */
    RespResult<SysLogResult> delete(Long id);

    /**
     * Description: 删除多个 <br/>
     * ids: 1,2,3<p>
     * Autor: Created by Jin Kun on 2017-05-27.
     */
    RespResult<SysLogResult> deleteByIds(String ids);

    /**
     * Description: 查询 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-27.
     */
    RespResult<SysLogResult> get(Long id);

    /**
     * Description: 查询列表 <br/>
     * page: ture 分页 false 查询所有<p>
     * Autor: Created by Jin Kun on 2017-05-27.
     */
    RespResult<PageResponse<SysLogResult>> listPage(PageRequest pageRequest, SysLogForm form);

    RespResult<String> exportXls(String ids);

    RespResult<String> importXls(MultipartFile file);

    RespResult<Map<String,Object>> pv();
}