<#-- package -->
package ${EntityInfo.packageInfo.entity};

<#-- import -->
import java.io.Serializable;
<#list EntityInfo.importList as import>
import ${import};
</#list>


<#-- 注释 -->
/**
 * @Description: ${EntityInfo.remarks}，数据库表为： ${EntityInfo.tableName} <br/>
 * @Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
 */
public class ${EntityInfo.entityName} implements Serializable{

<#-- 属性 -->
<#list EntityInfo.fieldInfoList as fieldInfo>
    /**
     * ${fieldInfo.remarks}，数据库字段为：${fieldInfo.columnName}
     */
    ${fieldInfo.modifier} ${fieldInfo.type} ${fieldInfo.name};
</#list>

<#-- setter和getter -->
<#list EntityInfo.fieldInfoList as fieldInfo>
    <#if fieldInfo.type != 'boolean'>
    public void set${fieldInfo.name ? cap_first}(${fieldInfo.type} ${fieldInfo.name}) {
        this.${fieldInfo.name} = ${fieldInfo.name};
    }

    public ${fieldInfo.type} get${fieldInfo.name ? cap_first}() {
        return this.${fieldInfo.name};
    }

    <#else>
    public void set${fieldInfo.name ? cap_first}(${fieldInfo.type} ${fieldInfo.name}) {
        this.${fieldInfo.name} = ${fieldInfo.name};
    }

    public ${fieldInfo.type} is${fieldInfo.name ? cap_first}() {
        return this.${fieldInfo.name};
    }

    </#if>
</#list>
}