package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.utils.UtilBean;
import me.jinkun.rds.sys.entity.User;
import me.jinkun.rds.sys.entity.UserOrg;
import me.jinkun.rds.sys.mapper.IUserMapper;
import me.jinkun.rds.sys.mapper.IUserOrgMapper;
import me.jinkun.rds.sys.model.UserExtend;
import me.jinkun.rds.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户-业务实现
 *
 * @author JinKun
 * @date 2017-11-25
 * @time 15:46
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserMapper iUserMapper;
    @Autowired
    IUserOrgMapper iUserOrgMapper;

    @Override
    public Optional<User> loadByPK(Long id, Set<String> fields) {
        User user = iUserMapper.loadByPK(id, fields);
        if (Objects.isNull(user)) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    public List<User> loads(User user, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return iUserMapper.loads(user, fields, sortSet, page);
    }

    public int loadCount(User user) {
        return iUserMapper.loadCount(user);
    }

    public boolean saveOrUpdate(User user) {
        boolean save = Objects.isNull(user.getId());
        Date now = new Date();
        if (save) {
            user.setUpdateTime(now);
            user.setCreateTime(now);
            return iUserMapper.insert(user) > 0;
        }
        user.setUpdateTime(now);
        return iUserMapper.update(user) > 0;
    }

    public boolean deleteByIds(Set<Long> ids) {
        int count = iUserMapper.deleteByIds(ids);
        boolean flag = count > 0;
        if (flag) {
            //删除中间表
            iUserOrgMapper.deleteByUserIds(ids);
        }
        return flag;
    }

    @Override
    public List<User> loadsByIds(Set<Long> ids, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return iUserMapper.loadsByIds(ids, fields, sortSet, page);
    }

    @Override
    public Optional<UserExtend> loadUserExtendByPK(Long id, Set<String> fields) {
        User user = iUserMapper.loadByPK(id, fields);
        if (Objects.isNull(user)) {
            return Optional.empty();
        }

        UserExtend ue = new UserExtend();
        UtilBean.copyPropertiesIgnoreNull(user, ue);

        UserOrg userOrg = new UserOrg();
        userOrg.setUserId(id);
        Set<Long> orgIds = iUserOrgMapper.loadsOrgId(userOrg);
        ue.setOrgIds(orgIds);

        return Optional.of(ue);
    }

    @Override
    public boolean saveOrUpdate(UserExtend userExtend) {
        //保存用户
        User user = new User();
        UtilBean.copyPropertiesIgnoreNull(userExtend, user);
        boolean flag = saveOrUpdate(user);
        if (!flag) {
            return false;
        }

        //保存组织关系
        Set<Long> orgIds = userExtend.getOrgIds();
        if (Objects.nonNull(orgIds) && !orgIds.isEmpty()) {
            UserOrg userOrg = new UserOrg();
            userOrg.setUserId(user.getId());
            iUserOrgMapper.delete(userOrg);
            return iUserOrgMapper.insertBatch(user.getId(), orgIds) > 0;
        }

        return true;
    }

}