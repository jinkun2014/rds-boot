<#-- package -->
package ${EntityInfo.packageInfo.result};

import ${EntityInfo.packageInfo.resultBase}.BaseResult;

/**
 * @Description: 封装返回数据！ <br/>
 * @Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
 */
public class ${EntityInfo.entityName}Result extends BaseResult {

    //=====================实体属性=========================
<#-- 属性 -->
<#list EntityInfo.fieldInfoList as fieldInfo>
    <#if fieldInfo.type == "Date">
    //${fieldInfo.remarks}，数据库字段为：${EntityInfo.tableName}.${fieldInfo.columnName}
    ${fieldInfo.modifier} String ${fieldInfo.name};
    <#else >
    //${fieldInfo.remarks}，数据库字段为：${EntityInfo.tableName}.${fieldInfo.columnName}
    ${fieldInfo.modifier} ${fieldInfo.type} ${fieldInfo.name};
    </#if>

</#list>
    //=====================其它属性=========================


<#-- setter和getter -->
<#list EntityInfo.fieldInfoList as fieldInfo>
    <#if fieldInfo.type == "Date">
    public void set${fieldInfo.name ? cap_first}(String ${fieldInfo.name}) {
        this.${fieldInfo.name} = ${fieldInfo.name};
    }

    public String get${fieldInfo.name ? cap_first}() {
        return this.${fieldInfo.name};
    }
    <#else >
    public void set${fieldInfo.name ? cap_first}(${fieldInfo.type} ${fieldInfo.name}) {
        this.${fieldInfo.name} = ${fieldInfo.name};
    }

    public ${fieldInfo.type} get${fieldInfo.name ? cap_first}() {
        return this.${fieldInfo.name};
    }
    </#if>

</#list>
}
