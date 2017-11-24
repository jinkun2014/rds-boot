<#-- package -->
package ${EntityInfo.packageInfo.serviceImpl};

import ${EntityInfo.packageInfo.page}.PageRequest;
import ${EntityInfo.packageInfo.page}.PageResponse;
import ${EntityInfo.packageInfo.resp}.RespResult;
import ${EntityInfo.packageInfo.convert}.${EntityInfo.entityName}Convert;
import ${EntityInfo.packageInfo.dao}.${EntityInfo.entityName}Mapper;
import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName};
import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}Example;
import ${EntityInfo.packageInfo.service}.${EntityInfo.entityName}Service;
import ${EntityInfo.packageInfo.form}.${EntityInfo.entityName}Form;
import ${EntityInfo.packageInfo.result}.${EntityInfo.entityName}Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: ${EntityInfo.remarks},数据库表为： ${EntityInfo.tableName}<br/>
 * @Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
 */
@Service
@Transactional
public class ${EntityInfo.entityName}ServiceImpl implements ${EntityInfo.entityName}Service {

    @Autowired
    ${EntityInfo.entityName}Mapper ${EntityInfo.entityName ? uncap_first}Mapper;
<#if EntityInfo.primaryKey??>
    @Override
    public ${EntityInfo.entityName} findById(${EntityInfo.primaryKey.type} ${EntityInfo.primaryKey.name}) {
        ${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first} = ${EntityInfo.entityName ? uncap_first}Mapper.selectByPrimaryKey(${EntityInfo.primaryKey.name});
        return ${EntityInfo.entityName ? uncap_first};
    }

    @Override
    public ${EntityInfo.entityName} findFirst() {
        ${EntityInfo.entityName}Example example = new ${EntityInfo.entityName}Example();
        //设置分页
        example.setStart(0L);
        example.setSize(1L);

        List<${EntityInfo.entityName}> ${EntityInfo.entityName ? uncap_first}List = ${EntityInfo.entityName ? uncap_first}Mapper.selectPageByExample(example);
        if(${EntityInfo.entityName ? uncap_first}List != null && ${EntityInfo.entityName ? uncap_first}List.size() > 0){
            return ${EntityInfo.entityName ? uncap_first}List.get(0);
        }
        return null;
    }

    @Override
    public List<${EntityInfo.entityName}> findAll() {
        ${EntityInfo.entityName}Example example = new ${EntityInfo.entityName}Example();
        List<${EntityInfo.entityName}> ${EntityInfo.entityName ? uncap_first}List = ${EntityInfo.entityName ? uncap_first}Mapper.selectByExample(example);
        return ${EntityInfo.entityName ? uncap_first}List;
    }

    @Override
    public List<${EntityInfo.entityName}> findByForm(${EntityInfo.entityName}Form form) {
        ${EntityInfo.entityName}Example example = new ${EntityInfo.entityName}Example();

        //查询条件
        if (form != null) {
            ${EntityInfo.entityName}Example.Criteria criteria = example.createCriteria();
            <#list EntityInfo.fieldInfoList as fieldInfo>
                <#if fieldInfo.search>
            //条件-${fieldInfo.remarks}
            if (form.get${fieldInfo.name?cap_first}() != null){
                criteria.and${fieldInfo.name?cap_first}Like("%" + form.get${fieldInfo.name?cap_first}() + "%");
            }
                </#if>
            </#list>

            //其它条件

        }

        //返回结果
        return ${EntityInfo.entityName ? uncap_first}Mapper.selectByExample(example);
    }

    @Override
    public RespResult<${EntityInfo.entityName}Result> save(${EntityInfo.entityName}Form form) {
        if (form.get${EntityInfo.primaryKey.name ? cap_first}() == null) {
            return add(form);
        } else {
            return update(form.get${EntityInfo.primaryKey.name ? cap_first}(),form);
        }
    }

    @Override
    public RespResult<${EntityInfo.entityName}Result> add(${EntityInfo.entityName}Form form) {
        ${EntityInfo.entityName} entity = ${EntityInfo.entityName}Convert.formToEntity(form);
        entity.set${EntityInfo.primaryKey.name ? cap_first}(null);
        ${EntityInfo.entityName ? uncap_first}Mapper.insertSelective(entity);
        return RespResult.ok("保存成功");
    }

    @Override
    public RespResult<${EntityInfo.entityName}Result> update(${EntityInfo.primaryKey.type} ${EntityInfo.primaryKey.name},${EntityInfo.entityName}Form form) {
        ${EntityInfo.entityName} entity = ${EntityInfo.entityName}Convert.formToEntity(form);
        entity.set${EntityInfo.primaryKey.name ? cap_first}(${EntityInfo.primaryKey.name});
        ${EntityInfo.entityName ? uncap_first}Mapper.updateByPrimaryKey(entity);
        return RespResult.ok("更新成功",form);
    }

    public RespResult<${EntityInfo.entityName}Result> delete(${EntityInfo.primaryKey.type} ${EntityInfo.primaryKey.name}) {
        ${EntityInfo.entityName ? uncap_first}Mapper.deleteByPrimaryKey(${EntityInfo.primaryKey.name});
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<${EntityInfo.entityName}Result> deleteByIds(String ids) {
        List<Long> idList = idsToList(ids);
        ${EntityInfo.entityName}Example example = new ${EntityInfo.entityName}Example();
        example.createCriteria().and${EntityInfo.primaryKey.name ? cap_first}In(idList);
        ${EntityInfo.entityName ? uncap_first}Mapper.deleteByExample(example);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<${EntityInfo.entityName}Result> get(${EntityInfo.primaryKey.type} ${EntityInfo.primaryKey.name}) {
        ${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first} = ${EntityInfo.entityName ? uncap_first}Mapper.selectByPrimaryKey(${EntityInfo.primaryKey.name});
        return RespResult.ok("查询成功", ${EntityInfo.entityName}Convert.entityToResult(${EntityInfo.entityName ? uncap_first}));
    }

    @Override
    public RespResult<PageResponse<${EntityInfo.entityName}Result>> listPage(PageRequest pageRequest,${EntityInfo.entityName}Form form) {
        ${EntityInfo.entityName}Example example = new ${EntityInfo.entityName}Example();
        //设置分页
        example.setStart((pageRequest.getPage() - 1) * pageRequest.getSize());
        example.setSize(pageRequest.getSize());

        //查询条件
        if (form != null) {
            ${EntityInfo.entityName}Example.Criteria criteria = example.createCriteria();
        <#list EntityInfo.fieldInfoList as fieldInfo>
            <#if fieldInfo.search>
            //条件-${fieldInfo.remarks}
            if (form.get${fieldInfo.name?cap_first}() != null){
                criteria.and${fieldInfo.name?cap_first}Like("%" + form.get${fieldInfo.name?cap_first}() + "%");
            }
            </#if>
        </#list>

            //其它条件

        }

        //查询总记录
        long count = ${EntityInfo.entityName ? uncap_first}Mapper.countByExample(example);
        //查询分页列表
        List<${EntityInfo.entityName}> ${EntityInfo.entityName ? uncap_first}List = ${EntityInfo.entityName ? uncap_first}Mapper.selectPageByExample(example);

        List<${EntityInfo.entityName}Result> result = ${EntityInfo.entityName}Convert.entityListToResultList(${EntityInfo.entityName ? uncap_first}List);

        //返回结果
        return RespResult.ok("查询成功",new PageResponse<>(pageRequest.getPage(),pageRequest.getSize(),count,result));
    }

    private List<${EntityInfo.primaryKey.type}> idsToList(String ids) {
        String[] id = ids.split(",");
        List<${EntityInfo.primaryKey.type}> idList = new ArrayList<>();
            for (int i = 0; i < id.length; i++) {
            idList.add(Long.parseLong(id[i]));
        }
        return idList;
    }
</#if>
}