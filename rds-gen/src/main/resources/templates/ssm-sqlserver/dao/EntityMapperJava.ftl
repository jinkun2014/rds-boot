<#-- package -->
package ${EntityInfo.packageInfo.dao};

import java.util.List;
import java.util.Map;
import cn.com.header.core.util.Page;
import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName};
<#-- import -->
<#list EntityInfo.importList as import>
import ${import};
</#list>


<#-- 注释 -->
/**
 *
 * ${EntityInfo.remarks}-Dao接口
 * @author JinKun
 * @date ${EntityInfo.createTime}
 * @time ${EntityInfo.time}
 */
public interface ${EntityInfo.entityName}Dao {<#-- 类名 -->
    /**
     * 添加${EntityInfo.remarks}
     * @param ${EntityInfo.entityName ? uncap_first}
     */
    public void insert(${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first});

    /**
     * 修改${EntityInfo.remarks}
     * @param ${EntityInfo.entityName ? uncap_first}
     */
    public void update(${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first});

    /**
     * 根据id删除${EntityInfo.remarks}
     * @param id
     */
    public void delete(${EntityInfo.primaryKey.type} id);

    /**
     * 根据id查询${EntityInfo.remarks}
     * @param id
     * @return
     */
    public ${EntityInfo.entityName} selectById(${EntityInfo.primaryKey.type} id);

    /**
     * 分页查询${EntityInfo.remarks}
     * @param page
     * @return
     */
    public List<Map<String,Object>> query(Page page);

    /**
     * 条件查询${EntityInfo.remarks}
     * @param map
     * @return
     */
    public List<${EntityInfo.entityName}> findAllByParams(Map<String, Object> map);


    /**
     * 条件查询${EntityInfo.remarks}
     * @param map
     * @return
     */
    public ${EntityInfo.entityName} findByParams(Map<String, Object> map);
}