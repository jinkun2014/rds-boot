package me.jinkun.rds.sys.service.impl;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.entity.Role;
import me.jinkun.rds.sys.entity.RoleResource;
import me.jinkun.rds.sys.mapper.IRoleMapper;
import me.jinkun.rds.sys.mapper.IRoleResourceMapper;
import me.jinkun.rds.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 角色-业务实现
 *
 * @author JinKun
 * @date 2017-11-27
 * @time 17:04
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    IRoleMapper iRoleMapper;
    @Autowired
    IRoleResourceMapper iRoleResourceMapper;

    @Override
    public Optional<Role> loadByPK(Long id, Set<String> fields) {
        Role role = iRoleMapper.loadByPK(id, fields);
        if (Objects.isNull(role)) {
            return Optional.empty();
        }
        return Optional.of(role);
    }

    @Override
    public List<Role> loads(Role role, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return iRoleMapper.loads(role, fields, sortSet, page);
    }

    @Override
    public int loadCount(Role role) {
        return iRoleMapper.loadCount(role);
    }

    @Override
    public boolean saveOrUpdate(Role role) {
        boolean save = Objects.isNull(role.getId());
        Date now = new Date();
        if (save) {
            role.setDelFlag(false);
            role.setUpdateTime(now);
            role.setCreateTime(now);
            return iRoleMapper.insert(role) > 0;
        }
        role.setUpdateTime(now);
        return iRoleMapper.update(role) > 0;
    }

    @Override
    public boolean deleteByIds(Set<Long> ids) {
        boolean flag = iRoleMapper.deleteByIds(ids) > 0;
        if (flag) {
            iRoleResourceMapper.deleteByRoleIds(ids);
        }
        return flag;
    }

    @Override
    public Set<Long> getResourceIds(Long roleId) {
        RoleResource roleResource = new RoleResource();
        roleResource.setRoleId(roleId);
        return iRoleResourceMapper.loadsResourceId(roleResource);
    }

    @Override
    public boolean saveResourceIds(Long id, Set<Long> resourceIds) {
        iRoleResourceMapper.deleteByRoleIds(Sets.newHashSet(id));
        int count = iRoleResourceMapper.insertBatch(id, resourceIds);
        return count > 0;
    }
}