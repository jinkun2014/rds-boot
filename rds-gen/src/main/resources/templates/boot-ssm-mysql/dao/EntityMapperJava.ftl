<#-- package -->
package ${EntityInfo.packageInfo.dao};

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName};
import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}Example;
<#-- import -->
<#list EntityInfo.importList as import>
import ${import};
</#list>


<#-- 注释 -->
/**
 * @Description: ${EntityInfo.remarks},数据库表为： ${EntityInfo.tableName} <br/>
 * @Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
 */
@Mapper
public interface ${EntityInfo.entityName}Mapper {<#-- 类名 -->
    long countByExample(${EntityInfo.entityName}Example example);

    int deleteByExample(${EntityInfo.entityName}Example example);

<#if EntityInfo.primaryKey??>
    int deleteByPrimaryKey(${EntityInfo.primaryKey.type} ${EntityInfo.primaryKey.name});
</#if>

    int insert(${EntityInfo.entityName} record);

    int insertSelective(${EntityInfo.entityName} record);

    List<${EntityInfo.entityName}> selectByExample(${EntityInfo.entityName}Example example);

    List<${EntityInfo.entityName}> selectPageByExample(${EntityInfo.entityName}Example example);

<#if EntityInfo.primaryKey??>
    ${EntityInfo.entityName} selectByPrimaryKey(${EntityInfo.primaryKey.type} ${EntityInfo.primaryKey.name});
</#if>

    int updateByExampleSelective(@Param("record") ${EntityInfo.entityName} record, @Param("example") ${EntityInfo.entityName}Example example);

    int updateByExample(@Param("record") ${EntityInfo.entityName} record, @Param("example") ${EntityInfo.entityName}Example example);

<#if EntityInfo.primaryKey??>
    int updateByPrimaryKeySelective(${EntityInfo.entityName} record);

    int updateByPrimaryKey(${EntityInfo.entityName} record);
</#if>
}