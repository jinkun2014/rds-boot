<#-- package -->
package ${EntityInfo.packageInfo.dao};

import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName};
<#-- import -->
import cn.com.header.core.IPage;
import cn.com.header.core.ISort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
<#list EntityInfo.importList as import>
import ${import};
</#list>
import java.util.*;


<#-- 注释 -->
/**
 *
 * ${EntityInfo.remarks}-Dao接口
 * @author JinKun
 * @date ${EntityInfo.createTime}
 * @time ${EntityInfo.time}
 */
@Mapper
@Repository
public interface ${EntityInfo.daoName} {<#-- 类名 -->

    /**
     * 新增${EntityInfo.remarks}
     * @param ${EntityInfo.entityName ? uncap_first}
     */
    int insert(@Param("${EntityInfo.entityName ? uncap_first}") ${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first});

    /**
     * 编辑${EntityInfo.remarks}
     * @param ${EntityInfo.entityName ? uncap_first}
     */
    int update(@Param("${EntityInfo.entityName ? uncap_first}") ${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first});

    /**
     * 删除${EntityInfo.remarks}
     * @param ${EntityInfo.entityName ? uncap_first}
     */
    int delete(@Param("${EntityInfo.entityName ? uncap_first}") ${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first});

    /**
     * 根据ID批量删除
     * @param ids
     */
    int deleteByIds(@Param("ids") Set<${EntityInfo.primaryKey.type}> ids);

    /**
     * 查询${EntityInfo.remarks}记录数
     * @param ${EntityInfo.entityName ? uncap_first}
     * @return
     */
    int loadCount(@Param("${EntityInfo.entityName ? uncap_first}") ${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first});

    /**
     * 根据id查询${EntityInfo.remarks}
     * @param id ${EntityInfo.remarks}id
     * @param fields 查询属性
     * @return
     */
    ${EntityInfo.entityName} loadByPK(
        @Param("id") ${EntityInfo.primaryKey.type} ${EntityInfo.primaryKey.name},
        @Param("fields") Set<String> fields
    );

    /**
     * 查询${EntityInfo.remarks}
     * @param ${EntityInfo.entityName ? uncap_first} ${EntityInfo.remarks}
     * @param fields 查询属性
     * @param sortSet 排序器
     * @param page 分页
     * @return
     */
    List<${EntityInfo.entityName}> loads(
        @Param("${EntityInfo.entityName ? uncap_first}") ${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first},
        @Param("fields") Set<String> fields,
        @Param("sorts") Set<ISort> sortSet,
        @Param("page") IPage page
    );
}