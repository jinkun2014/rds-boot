<#-- package -->
package ${EntityInfo.packageInfo.entity};

<#-- import -->
import cn.com.header.core.BaseEntity;
<#list EntityInfo.importList as import>
import ${import};
</#list>


/**
 * ${EntityInfo.remarks}-实体,数据库表为： ${EntityInfo.tableName}
 *
 * @author JinKun
 * @date ${EntityInfo.createTime}
 * @time ${EntityInfo.time}
 */
public class ${EntityInfo.entityName} extends BaseEntity{<#-- 类名 -->

<#-- 属性 -->
<#list EntityInfo.fieldInfoList as fieldInfo>

<#if !fieldInfo.primaryKey>
<#if fieldInfo.remarks?? && fieldInfo.remarks != "">
    /*
     * ${fieldInfo.remarks}，数据库字段为：${fieldInfo.columnName}
     */
</#if>
    ${fieldInfo.modifier} ${fieldInfo.type} ${fieldInfo.name};
</#if>
</#list>

<#-- setter和getter -->
<#list EntityInfo.fieldInfoList as fieldInfo>
    <#if !fieldInfo.primaryKey>
    public void set${fieldInfo.name ? cap_first}(${fieldInfo.type} ${fieldInfo.name}) {
        this.${fieldInfo.name} = ${fieldInfo.name};
    }

    public ${fieldInfo.type} get${fieldInfo.name ? cap_first}() {
        return this.${fieldInfo.name};
    }

    </#if>
</#list>
}