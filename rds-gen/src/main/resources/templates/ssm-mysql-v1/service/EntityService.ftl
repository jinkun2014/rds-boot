<#-- package -->
package ${EntityInfo.packageInfo.service};

import ${EntityInfo.packageInfo.page}.PageRequest;
import ${EntityInfo.packageInfo.page}.PageResponse;
import ${EntityInfo.packageInfo.resp}.RespResult;
import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName};
import ${EntityInfo.packageInfo.form}.${EntityInfo.entityName}Form;
import ${EntityInfo.packageInfo.result}.${EntityInfo.entityName}Result;
import java.util.List;

/**
 * @Description: ${EntityInfo.remarks},数据库表为： ${EntityInfo.tableName}<br/>
 * @Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
 */
public interface ${EntityInfo.entityName}Service {
<#if EntityInfo.primaryKey??>
    /**
     * Description: 根据ID查询 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    ${EntityInfo.entityName} findById(${EntityInfo.primaryKey.type} ${EntityInfo.primaryKey.name});

    /**
     * Description: 查询所有 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    List<${EntityInfo.entityName}> findAll();

    /**
     * Description: 查询第一个 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    ${EntityInfo.entityName} findFirstByForm(${EntityInfo.entityName}Form form);

    /**
     * Description: 根据条件查询列表 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    List<${EntityInfo.entityName}> findByForm(${EntityInfo.entityName}Form form);


    /**
     * Description: 增加或更新，根据id判断 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    RespResult<${EntityInfo.entityName}Result> save(${EntityInfo.entityName}Form form);

    /**
     * Description: 增加 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    RespResult<${EntityInfo.entityName}Result> add(${EntityInfo.entityName}Form form);

    /**
     * Description: 更新 <br/>
     * 主键或其它条件<p>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    RespResult<${EntityInfo.entityName}Result> update(${EntityInfo.primaryKey.type} ${EntityInfo.primaryKey.name},${EntityInfo.entityName}Form form);

    /**
     * Description: 删除 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    RespResult<${EntityInfo.entityName}Result> delete(${EntityInfo.primaryKey.type} ${EntityInfo.primaryKey.name});

    /**
     * Description: 删除多个 <br/>
     * ids: 1,2,3<p>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    RespResult<${EntityInfo.entityName}Result> deleteByIds(String ids);

    /**
     * Description: 查询 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    RespResult<${EntityInfo.entityName}Result> get(${EntityInfo.primaryKey.type} ${EntityInfo.primaryKey.name});

    /**
     * Description: 查询列表 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    RespResult<PageResponse<${EntityInfo.entityName}Result>> listPage(PageRequest pageRequest,${EntityInfo.entityName}Form form);
<#else>

</#if>
}