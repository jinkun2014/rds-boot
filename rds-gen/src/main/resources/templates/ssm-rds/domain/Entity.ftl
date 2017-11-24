<#-- package -->
package ${EntityInfo.packageInfo.entity};

<#-- import -->
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
<#list EntityInfo.importList as import>
import ${import};
</#list>
import java.io.Serializable;

/**
 * ${EntityInfo.remarks}-实体,数据库表为： ${EntityInfo.tableName}
 *
 * @author JinKun
 * @date ${EntityInfo.createTime}
 * @time ${EntityInfo.time}
 */
@Getter
@Setter
@ToString
public class ${EntityInfo.entityName} implements Serializable {<#-- 类名 -->

<#-- 属性 -->
<#list EntityInfo.fieldInfoList as fieldInfo>

<#if fieldInfo.remarks?? && fieldInfo.remarks != "">
    /**
     * ${fieldInfo.remarks}，column: ${fieldInfo.columnName}
     */
</#if>
    ${fieldInfo.modifier} ${fieldInfo.type} ${fieldInfo.name};
</#list>

<#-- setter和getter
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
-->
}