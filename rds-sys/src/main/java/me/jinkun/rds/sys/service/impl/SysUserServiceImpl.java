package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.convert.SysUserConvert;
import me.jinkun.rds.sys.dao.SysUserMapper;
import me.jinkun.rds.sys.domain.SysUser;
import me.jinkun.rds.sys.domain.SysUserExample;
import me.jinkun.rds.sys.domain.SysUserOrg;
import me.jinkun.rds.sys.service.SysUserOrgService;
import me.jinkun.rds.sys.service.SysUserRoleService;
import me.jinkun.rds.sys.service.SysUserService;
import me.jinkun.rds.sys.web.form.SysUserForm;
import me.jinkun.rds.sys.web.form.SysUserOrgForm;
import me.jinkun.rds.sys.web.form.SysUserRoleForm;
import me.jinkun.rds.sys.web.result.SysUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 用户, 数据库表为： sys_user<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserOrgService sysUserOrgService;
    @Autowired
    SysUserRoleService sysUserRoleService;

    @Override
    public RespResult<SysUserResult> save(SysUserForm form) {
        if (form.getId() == null) {
            return add(form);
        } else {
            return update(form.getId(), form);
        }
    }

    @Override
    public RespResult<SysUserResult> add(SysUserForm form) {
        SysUser entity = SysUserConvert.formToEntity(form);
        entity.setId(null);
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        sysUserMapper.insertSelective(entity);

        //保存组织数据
        SysUserOrgForm userOrgForm = new SysUserOrgForm();
        userOrgForm.setUserId(entity.getId());
        userOrgForm.setOrgId(form.getOrgId());
        sysUserOrgService.add(userOrgForm);

        //保存角色数据
        for (Long roleId : form.getRoleId()) {
            SysUserRoleForm userRoleForm = new SysUserRoleForm();
            userRoleForm.setUserId(entity.getId());
            userRoleForm.setRoleId(roleId);
            sysUserRoleService.add(userRoleForm);
        }

        return RespResult.ok("保存成功");
    }

    @Override
    public RespResult<SysUserResult> update(Long id, SysUserForm form) {
        SysUser old = sysUserMapper.selectByPrimaryKey(id);

        SysUser entity = SysUserConvert.formToEntity(form);
        entity.setId(id);
        entity.setDelFlag(old.getDelFlag());
        entity.setUpdateTime(new Date());
        entity.setCreateTime(old.getCreateTime());
        sysUserMapper.updateByPrimaryKey(entity);

        //删除用户组织数据
        sysUserOrgService.deleteByUserId(id);

        //保存用户组织数据
        SysUserOrgForm userOrgForm = new SysUserOrgForm();
        userOrgForm.setUserId(id);
        userOrgForm.setOrgId(form.getOrgId());
        sysUserOrgService.add(userOrgForm);

        //删除用户角色数据
        sysUserRoleService.deleteByUserId(id);

        //保存用户角色数据
        for (Long roleId : form.getRoleId()) {
            SysUserRoleForm userRoleForm = new SysUserRoleForm();
            userRoleForm.setUserId(id);
            userRoleForm.setRoleId(roleId);
            sysUserRoleService.add(userRoleForm);
        }

        return RespResult.ok("更新成功", form);
    }

    public RespResult<SysUserResult> delete(Long id) {
        sysUserMapper.deleteByPrimaryKey(id);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysUserResult> deleteByIds(String ids) {
        List<Long> idList = idsToList(ids);
        SysUserExample example = new SysUserExample();
        example.createCriteria().andIdIn(idList);
        sysUserMapper.deleteByExample(example);

        //删除组织关联关系
        sysUserOrgService.deleteByUserIdList(idList);

        //删除角色关系
        sysUserRoleService.deleteByUserIdList(idList);

        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysUserResult> get(Long id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        SysUserResult result = SysUserConvert.entityToResult(sysUser);

        //回显组织
        SysUserOrg sysUserOrg = sysUserOrgService.getByUserId(id);
        if (sysUserOrg != null) {
            result.setOrgId(sysUserOrg.getOrgId());
        }

        //回显角色
        List<Long> roleIds = sysUserRoleService.getRoleIdsByUserId(id);
        result.setRoleId(roleIds);

        return RespResult.ok("查询成功", result);
    }

    @Override
    public RespResult<PageResponse<SysUserResult>> listPage(PageRequest pageRequest, SysUserForm form) {
        long count = 0;
        List<SysUser> sysUserList = null;


        //查询条件
        if (form != null) {
            //以组织来分页
            if (form.getOrgId() != null) {
                List<Long> userIdList = sysUserOrgService.findUserIdListByOrgId(form.getOrgId());
                if (userIdList != null) {
                    count = userIdList.size();

                    if (count > (pageRequest.getPage() * pageRequest.getSize())) {
                        SysUserExample example = new SysUserExample();
                        //long 转 int 可能会溢出，如果数据量非常大就不要用这种方法了
                        example.createCriteria().andIdIn(userIdList.subList((int) ((pageRequest.getPage() - 1) * pageRequest.getSize()), (int) (pageRequest.getPage() * pageRequest.getSize())));
                        sysUserList = sysUserMapper.selectByExample(example);
                    } else if (count > ((pageRequest.getPage() - 1) * pageRequest.getSize())) {
                        SysUserExample example = new SysUserExample();
                        //long 转 int 可能会溢出，如果数据量非常大就不要用这种方法了
                        example.createCriteria().andIdIn(userIdList.subList((int) ((pageRequest.getPage() - 1) * pageRequest.getSize()), (int) count));
                        sysUserList = sysUserMapper.selectByExample(example);
                    } else {
                        sysUserList = new ArrayList<>();
                    }

                }
            } else {
                SysUserExample example = new SysUserExample();
                //设置分页
                example.setStart((pageRequest.getPage() - 1) * pageRequest.getSize());
                example.setSize(pageRequest.getSize());

                //其它条件
                SysUserExample.Criteria criteria = example.createCriteria();
                if (form.getName() != null && !"".equals(form.getName())) {
                    criteria.andNameLike("%" + form.getName() + "%");
                }

                //查询总记录
                count = sysUserMapper.countByExample(example);
                //查询分页列表
                sysUserList = sysUserMapper.selectPageByExample(example);
            }
        }


        List<SysUserResult> result = SysUserConvert.entityListToResultList(sysUserList);
        //返回结果
        return RespResult.ok("查询成功", new PageResponse<>(pageRequest.getPage(), pageRequest.getSize(), count, result));
    }

    @Override
    public List<SysUser> findListByIds(List<Long> userIdList) {
        if (userIdList != null) {
            SysUserExample example = new SysUserExample();
            example.createCriteria().andIdIn(userIdList);
            return sysUserMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public SysUser findByLoginNameAndPassword(String loginName, String password) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(password);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if (sysUsers != null && sysUsers.size() == 1) {
            return sysUsers.get(0);
        }
        return null;
    }

    private List<Long> idsToList(String ids) {
        String[] id = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            idList.add(Long.parseLong(id[i]));
        }
        return idList;
    }
}