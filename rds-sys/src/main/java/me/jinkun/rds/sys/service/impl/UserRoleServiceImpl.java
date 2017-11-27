package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.mapper.IUserRoleMapper;
import me.jinkun.rds.sys.entity.UserRole;
import me.jinkun.rds.sys.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * 用户-角色-业务实现
 * @author JinKun
 * @date 2017-11-27
 * @time 17:07
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    IUserRoleMapper iUserRoleMapper;

    @Override
    public Optional<UserRole> loadByPK(Long id, Set<String> fields){
        UserRole userRole = iUserRoleMapper.loadByPK(id,fields);
        if (Objects.isNull(userRole)){
            return Optional.empty();
        }
        return Optional.of(userRole);
    }

    @Override
    public List<UserRole> loads(UserRole userRole, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return iUserRoleMapper.loads(userRole,fields,sortSet,page);
    }

    @Override
    public int loadCount(UserRole userRole) {
        return iUserRoleMapper.loadCount(userRole);
    }

    @Override
    public boolean saveOrUpdate(UserRole userRole) {
        boolean save = Objects.isNull(userRole.getId());
        if(save){
            return iUserRoleMapper.insert(userRole) > 0;
        }
        return iUserRoleMapper.update(userRole) > 0;
    }

    @Override
    public boolean deleteByIds(Set<Long> ids) {
        return iUserRoleMapper.deleteByIds(ids) > 0;
    }
}