package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.mapper.IRoleResourceMapper;
import me.jinkun.rds.sys.entity.RoleResource;
import me.jinkun.rds.sys.service.IRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * 角色-资源-业务实现
 * @author JinKun
 * @date 2017-11-27
 * @time 18:06
 */
@Service
public class RoleResourceServiceImpl implements IRoleResourceService {

    @Autowired
    IRoleResourceMapper iRoleResourceMapper;

    @Override
    public Optional<RoleResource> loadByPK(Long id, Set<String> fields){
        RoleResource roleResource = iRoleResourceMapper.loadByPK(id,fields);
        if (Objects.isNull(roleResource)){
            return Optional.empty();
        }
        return Optional.of(roleResource);
    }

    @Override
    public List<RoleResource> loads(RoleResource roleResource, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return iRoleResourceMapper.loads(roleResource,fields,sortSet,page);
    }

    @Override
    public int loadCount(RoleResource roleResource) {
        return iRoleResourceMapper.loadCount(roleResource);
    }

    @Override
    public boolean saveOrUpdate(RoleResource roleResource) {
        boolean save = Objects.isNull(roleResource.getId());
        if(save){
            return iRoleResourceMapper.insert(roleResource) > 0;
        }
        return iRoleResourceMapper.update(roleResource) > 0;
    }

    @Override
    public boolean deleteByIds(Set<Long> ids) {
        return iRoleResourceMapper.deleteByIds(ids) > 0;
    }
}