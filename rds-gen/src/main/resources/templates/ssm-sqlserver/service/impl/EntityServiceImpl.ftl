<#-- package -->
package ${EntityInfo.packageInfo.serviceImpl};

import ${EntityInfo.packageInfo.dao}.${EntityInfo.entityName}Dao;
import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName};
import ${EntityInfo.packageInfo.service}.${EntityInfo.entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import cn.com.header.core.util.Page;

/**
 *
 * ${EntityInfo.remarks}-业务实现
 * @author JinKun
 * @date ${EntityInfo.createTime}
 * @time ${EntityInfo.time}
 */
@Service
public class ${EntityInfo.entityName}ServiceImpl implements ${EntityInfo.entityName}Service {

    @Autowired
    ${EntityInfo.entityName}Dao ${EntityInfo.entityName ? uncap_first}Dao;

<#if EntityInfo.primaryKey??>
    @Override
    public Page query(Page page) {
        page.setResult(${EntityInfo.entityName ? uncap_first}Dao.query(page));
        return page;
    }

    @Override
    public void save(${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first}) {
        if(${EntityInfo.entityName ? uncap_first}.getId()==null){
            ${EntityInfo.entityName ? uncap_first}Dao.insert(${EntityInfo.entityName ? uncap_first});
        }else{
            ${EntityInfo.entityName ? uncap_first}Dao.update(${EntityInfo.entityName ? uncap_first});
        }
    }

    @Override
    public void delete(${EntityInfo.primaryKey.type} id) {
        ${EntityInfo.entityName ? uncap_first}Dao.delete(id);
    }

    @Override
    public ${EntityInfo.entityName} get(${EntityInfo.primaryKey.type} id) {
        return ${EntityInfo.entityName ? uncap_first}Dao.selectById(id);
    }

    @Override
    public void deleteAll(${EntityInfo.primaryKey.type}[] ids) {
        for(${EntityInfo.primaryKey.type} id:ids){
            delete(id);
        }
    }

    @Override
    public List<${EntityInfo.entityName}> findAllByParams(Map<String, Object> map) {
        return ${EntityInfo.entityName ? uncap_first}Dao.findAllByParams(map);
    }

    @Override
    public ${EntityInfo.entityName} findByParams(Map<String, Object> map) {
        return ${EntityInfo.entityName ? uncap_first}Dao.findByParams(map);
    }
</#if>
}