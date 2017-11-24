<#-- package -->
package ${EntityInfo.packageInfo.form};

import ${EntityInfo.packageInfo.formBase}.BaseForm;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 封装表单数据! <br/>
 * @Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
 */
public class ${EntityInfo.entityName}Form extends BaseForm {

    //=====================实体属性=========================
<#-- 属性 -->
<#list EntityInfo.fieldInfoList as fieldInfo>
    @ApiModelProperty(value = "${fieldInfo.remarks}")
    <#if fieldInfo.type == "Date">
    //${fieldInfo.remarks}，数据库字段为：${EntityInfo.tableName}.${fieldInfo.columnName}
    ${fieldInfo.modifier} String ${fieldInfo.name};
    <#else >
    <#-- 有默认值 -->
    <#if fieldInfo.defaultValue?? && fieldInfo.defaultValue!="">
    //${fieldInfo.remarks}，数据库字段为：${EntityInfo.tableName}.${fieldInfo.columnName}，默认值为：${fieldInfo.defaultValue}
        <#-- 默认值String -->
        <#if fieldInfo.type == "String">
    ${fieldInfo.modifier} ${fieldInfo.type} ${fieldInfo.name} = "${fieldInfo.defaultValue}";
        <#-- 默认值Long -->
        <#elseif fieldInfo.type == "Long" >
    ${fieldInfo.modifier} ${fieldInfo.type} ${fieldInfo.name} = ${fieldInfo.defaultValue}L;
        <#-- 默认值Integer -->
        <#elseif fieldInfo.type == "Integer" >
    ${fieldInfo.modifier} ${fieldInfo.type} ${fieldInfo.name} = ${fieldInfo.defaultValue};
        <#-- 默认值为其它类型 -->
        <#else >
    ${fieldInfo.modifier} ${fieldInfo.type} ${fieldInfo.name};
        </#if>
    <#-- 没有默认值 -->
    <#else >
    //${fieldInfo.remarks}，数据库字段为：${EntityInfo.tableName}.${fieldInfo.columnName}
    ${fieldInfo.modifier} ${fieldInfo.type} ${fieldInfo.name};
    </#if>
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
