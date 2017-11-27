<#-- package -->
package ${EntityInfo.packageInfo.serviceImpl};

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import ${EntityInfo.packageInfo.dao}.${EntityInfo.daoName};
import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName};
import ${EntityInfo.packageInfo.service}.${EntityInfo.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * ${EntityInfo.remarks}-业务实现
 * @author JinKun
 * @date ${EntityInfo.createTime}
 * @time ${EntityInfo.time}
 */
@Service
public class ${EntityInfo.serviceImplName} implements ${EntityInfo.serviceName} {

    @Autowired
    ${EntityInfo.daoName} ${EntityInfo.daoName ? uncap_first};

    @Override
    public Optional<${EntityInfo.entityName}> loadByPK(Long id, Set<String> fields){
        ${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first} = ${EntityInfo.daoName ? uncap_first}.loadByPK(id,fields);
        if (Objects.isNull(${EntityInfo.entityName ? uncap_first})){
            return Optional.empty();
        }
        return Optional.of(${EntityInfo.entityName ? uncap_first});
    }

    @Override
    public List<${EntityInfo.entityName}> loads(${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first}, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return ${EntityInfo.daoName ? uncap_first}.loads(${EntityInfo.entityName ? uncap_first},fields,sortSet,page);
    }

    @Override
    public int loadCount(${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first}) {
        return ${EntityInfo.daoName ? uncap_first}.loadCount(${EntityInfo.entityName ? uncap_first});
    }

    @Override
    public boolean saveOrUpdate(${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first}) {
        boolean save = Objects.isNull(${EntityInfo.entityName ? uncap_first}.getId());
        if(save){
            return ${EntityInfo.daoName ? uncap_first}.insert(${EntityInfo.entityName ? uncap_first}) > 0;
        }
        return ${EntityInfo.daoName ? uncap_first}.update(${EntityInfo.entityName ? uncap_first}) > 0;
    }

    @Override
    public boolean deleteByIds(Set<${EntityInfo.primaryKey.type}> ids) {
        return ${EntityInfo.daoName ? uncap_first}.deleteByIds(ids) > 0;
    }
}