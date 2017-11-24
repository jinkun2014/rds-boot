package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.convert.SysUserRoleConvert;
import me.jinkun.rds.sys.dao.SysUserRoleMapper;
import me.jinkun.rds.sys.domain.SysUserRole;
import me.jinkun.rds.sys.domain.SysUserRoleExample;
import me.jinkun.rds.sys.service.SysUserRoleService;
import me.jinkun.rds.sys.web.form.SysUserRoleForm;
import me.jinkun.rds.sys.web.result.SysUserRoleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户-角色,数据库表为： sys_user_role<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Service
@Transactional
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public RespResult<SysUserRoleResult> save(SysUserRoleForm form) {
        if (form.getId() == null) {
            return add(form);
        } else {
            return update(form.getId(), form);
        }
    }

    @Override
    public RespResult<SysUserRoleResult> add(SysUserRoleForm form) {
        form.setId(null);
        SysUserRole entity = SysUserRoleConvert.formToEntity(form);
        sysUserRoleMapper.insertSelective(entity);
        return RespResult.ok("保存成功");
    }

    @Override
    public RespResult<SysUserRoleResult> update(Long id, SysUserRoleForm form) {
        form.setId(id);
        sysUserRoleMapper.updateByPrimaryKey(SysUserRoleConvert.formToEntity(form));
        return RespResult.ok("更新成功", form);
    }

    public RespResult<SysUserRoleResult> delete(Long id) {
        sysUserRoleMapper.deleteByPrimaryKey(id);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysUserRoleResult> deleteByIds(String ids) {
        List<Long> idList = idsToList(ids);
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andIdIn(idList);
        sysUserRoleMapper.deleteByExample(example);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysUserRoleResult> get(Long id) {
        SysUserRole sysUserRole = sysUserRoleMapper.selectByPrimaryKey(id);
        return RespResult.ok("查询成功", SysUserRoleConvert.entityToResult(sysUserRole));
    }

    @Override
    public RespResult<PageResponse<SysUserRoleResult>> listPage(PageRequest pageRequest, SysUserRoleForm form) {
        SysUserRoleExample example = new SysUserRoleExample();
        //设置分页
        example.setStart((pageRequest.getPage() - 1) * pageRequest.getSize());
        example.setSize(pageRequest.getSize());

        //查询条件
        if (form != null) {
            SysUserRoleExample.Criteria criteria = example.createCriteria();

            //其它条件

        }

        //查询总记录
        long count = sysUserRoleMapper.countByExample(example);
        //查询分页列表
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectPageByExample(example);

        List<SysUserRoleResult> result = SysUserRoleConvert.entityListToResultList(sysUserRoleList);

        //返回结果
        return RespResult.ok("查询成功", new PageResponse<>(pageRequest.getPage(), pageRequest.getSize(), count, result));
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long id) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(id);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(example);
        return toIdList(sysUserRoles);
    }

    @Override
    public void deleteByUserId(Long id) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(id);
        sysUserRoleMapper.deleteByExample(example);
    }

    @Override
    public void deleteByUserIdList(List<Long> idList) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdIn(idList);
        sysUserRoleMapper.deleteByExample(example);
    }

    private List<Long> toIdList(List<SysUserRole> sysUserRoles) {
        List<Long> idList = new ArrayList<>();
        if (sysUserRoles != null && sysUserRoles.size() > 0) {
            for (SysUserRole ur : sysUserRoles) {
                idList.add(ur.getRoleId());
            }
        }
        return idList;
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