<#-- package -->
package ${EntityInfo.packageInfo.service};

import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName};
import me.jinkun.rds.core.IPage;
import me.jinkun.rds.core.ISort;
import java.util.*;


/**
 *
 * ${EntityInfo.remarks}-业务接口
 * @author JinKun
 * @date ${EntityInfo.createTime}
 * @time ${EntityInfo.time}
 */
public interface ${EntityInfo.serviceName} {

    /**
     * 根据id查询${EntityInfo.remarks}
     * @param id
     * @param fields
     * @return
     */
    Optional<${EntityInfo.entityName}> loadByPK(Long id, Set<String> fields);

    /**
     * 查询${EntityInfo.remarks}列表
     * @param ${EntityInfo.entityName ? uncap_first} ${EntityInfo.remarks}
     * @param sortSet 排序器
     * @param page 分页
     * @return
     */
    List<${EntityInfo.entityName}> loads(
        ${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first},
        Set<String> fields,
        Set<ISort> sortSet,
        IPage page
    );

    /**
     * 查询${EntityInfo.remarks}记录数
     * @param ${EntityInfo.entityName ? uncap_first} ${EntityInfo.remarks}
     * @return
     */
    int loadCount(${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first});

    /**
     * 新增/编辑${EntityInfo.remarks}
     * @param ${EntityInfo.entityName ? uncap_first}
     * @return
     */
    boolean saveOrUpdate(${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first});

    /**
     * 根据ids批量删除
     *
     * @param idSets
     * @return
     */
    boolean deleteByIds(Set<${EntityInfo.primaryKey.type}> ids);
}