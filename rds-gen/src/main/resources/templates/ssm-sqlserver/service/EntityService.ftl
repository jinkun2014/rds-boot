<#-- package -->
package ${EntityInfo.packageInfo.service};

import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName};
import java.util.List;
import java.util.Map;
import cn.com.header.core.util.Page;

/**
 *
 * ${EntityInfo.remarks}-业务接口
 * @author JinKun
 * @date ${EntityInfo.createTime}
 * @time ${EntityInfo.time}
 */
public interface ${EntityInfo.entityName}Service {
<#if EntityInfo.primaryKey??>
    /**
     * Description: 分页查询 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    Page query(Page page);

    /**
     * Description: 保存 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    void save(${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first});

    /**
     * Description: 删除 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    void delete(${EntityInfo.primaryKey.type} id);

    /**
     * Description: 根据ID获取 <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    ${EntityInfo.entityName} get(${EntityInfo.primaryKey.type} id);

    /**
     * Description: 删除指定ids[] <br/>
     * Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
     */
    void deleteAll(${EntityInfo.primaryKey.type}[] ids);

    /**
     * 条件查询
     * @param map
     * @return
     */
    List<${EntityInfo.entityName}> findAllByParams(Map<String, Object> map);


    /**
     * 条件查询
     * @param map
     * @return
     */
    ${EntityInfo.entityName} findByParams(Map<String, Object> map);
<#else>

</#if>
}