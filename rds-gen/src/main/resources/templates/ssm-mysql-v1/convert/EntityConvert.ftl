package ${EntityInfo.packageInfo.convert};

import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName};
import ${EntityInfo.packageInfo.form}.${EntityInfo.entityName}Form;
import ${EntityInfo.packageInfo.result}.${EntityInfo.entityName}Result;
import ${EntityInfo.packageInfo.convertBase}.BaseConvert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: ${EntityInfo.entityName}转换类！ <br/>
 * @Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
 */
public class ${EntityInfo.entityName}Convert extends BaseConvert {

    public static ${EntityInfo.entityName} formToEntity(${EntityInfo.entityName}Form form) {
        if (form != null) {
            ${EntityInfo.entityName} entity = new ${EntityInfo.entityName}();
    <#-- 属性 -->
    <#list EntityInfo.fieldInfoList as fieldInfo>
        <#if fieldInfo.type == "Date">
            entity.set${fieldInfo.name ? cap_first}(stringToDate(form.get${fieldInfo.name ? cap_first}()));
        <#elseif fieldInfo.type == "boolean">
            entity.set${fieldInfo.name ? cap_first}(form.is${fieldInfo.name ? cap_first}());
        <#else>
            entity.set${fieldInfo.name ? cap_first}(form.get${fieldInfo.name ? cap_first}());
        </#if>
    </#list>
            return entity;
        }
        return null;
    }

    public static List<${EntityInfo.entityName}> formListToEntityList(List<${EntityInfo.entityName}Form> formList) {
        List<${EntityInfo.entityName}> entityList = new ArrayList<${EntityInfo.entityName}>();
        if (formList != null && formList.size() > 0) {
            for (${EntityInfo.entityName}Form form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static ${EntityInfo.entityName}Result entityToResult(${EntityInfo.entityName} entity) {
        if (entity != null) {
            ${EntityInfo.entityName}Result result = new ${EntityInfo.entityName}Result();
    <#list EntityInfo.fieldInfoList as fieldInfo>
        <#if fieldInfo.type == "Date">
            result.set${fieldInfo.name ? cap_first}(dateToString(entity.get${fieldInfo.name ? cap_first}()));
        <#elseif fieldInfo.type == "boolean" >
            result.set${fieldInfo.name ? cap_first}(entity.is${fieldInfo.name ? cap_first}());
        <#else>
            result.set${fieldInfo.name ? cap_first}(entity.get${fieldInfo.name ? cap_first}());
        </#if>
    </#list>
            return result;
        }
        return null;
    }

    public static List<${EntityInfo.entityName}Result> entityListToResultList(List<${EntityInfo.entityName}> entityList) {
        List<${EntityInfo.entityName}Result> resultList = new ArrayList<${EntityInfo.entityName}Result>();
        if (entityList != null && entityList.size() > 0) {
            for (${EntityInfo.entityName} entity : entityList) {
                resultList.add(entityToResult(entity));
            }
            return resultList;
        }
        return resultList;
    }
}
