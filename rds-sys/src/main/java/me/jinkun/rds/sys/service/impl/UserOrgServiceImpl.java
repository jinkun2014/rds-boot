package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.mapper.IUserOrgMapper;
import me.jinkun.rds.sys.entity.UserOrg;
import me.jinkun.rds.sys.service.IUserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * 用户-机构-业务实现
 * @author JinKun
 * @date 2017-11-25
 * @time 16:09
 */
@Service
public class UserOrgServiceImpl implements IUserOrgService {

    @Autowired
    IUserOrgMapper iUserOrgMapper;

    @Override
    public Optional<UserOrg> loadByPK(Long id, Set<String> fields){
        UserOrg userOrg = iUserOrgMapper.loadByPK(id,fields);
        if (Objects.isNull(userOrg)){
            return Optional.empty();
        }
        return Optional.of(userOrg);
    }

    public List<UserOrg> loads(UserOrg userOrg, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return iUserOrgMapper.loads(userOrg,fields,sortSet,page);
    }

    public int loadCount(UserOrg userOrg) {
        return iUserOrgMapper.loadCount(userOrg);
    }

    public boolean saveOrUpdate(UserOrg userOrg) {
        boolean save = Objects.isNull(userOrg.getId());
        if(save){
            return iUserOrgMapper.insert(userOrg) > 0;
        }
        return iUserOrgMapper.update(userOrg) > 0;
    }

    public boolean deleteByIds(Set<Long> ids) {
        return iUserOrgMapper.deleteByIds(ids) > 0;
    }

    @Override
    public Set<Long> loadsUserId(UserOrg userOrg) {
        return iUserOrgMapper.loadsUserId(userOrg);
    }
}