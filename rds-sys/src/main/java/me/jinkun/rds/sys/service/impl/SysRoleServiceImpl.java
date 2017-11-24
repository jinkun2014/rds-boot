package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.convert.SysRoleConvert;
import me.jinkun.rds.sys.dao.SysRoleMapper;
import me.jinkun.rds.sys.domain.SysRole;
import me.jinkun.rds.sys.domain.SysRoleExample;
import me.jinkun.rds.sys.service.SysRoleResourceService;
import me.jinkun.rds.sys.service.SysRoleService;
import me.jinkun.rds.sys.web.form.SysRoleForm;
import me.jinkun.rds.sys.web.form.SysRoleResourceForm;
import me.jinkun.rds.sys.web.result.SysRoleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 角色, 数据库表为： sys_role<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysRoleResourceService sysRoleResourceService;

    @Override
    public RespResult<SysRoleResult> save(SysRoleForm form) {
        if (form.getId() == null) {
            return add(form);
        } else {
            return update(form.getId(), form);
        }
    }

    @Override
    public RespResult<SysRoleResult> add(SysRoleForm form) {
        SysRole entity = SysRoleConvert.formToEntity(form);
        entity.setId(null);
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        sysRoleMapper.insertSelective(entity);
        return RespResult.ok("保存成功");
    }

    @Override
    public RespResult<SysRoleResult> update(Long id, SysRoleForm form) {
        SysRole old = sysRoleMapper.selectByPrimaryKey(id);

        SysRole entity = SysRoleConvert.formToEntity(form);
        entity.setId(id);
        entity.setUpdateTime(new Date());
        entity.setCreateTime(old.getCreateTime());
        sysRoleMapper.updateByPrimaryKey(entity);
        return RespResult.ok("更新成功", form);
    }

    public RespResult<SysRoleResult> delete(Long id) {
        sysRoleMapper.deleteByPrimaryKey(id);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysRoleResult> deleteByIds(String ids) {
        List<Long> idList = idsToList(ids);
        SysRoleExample example = new SysRoleExample();
        example.createCriteria().andIdIn(idList);
        sysRoleMapper.deleteByExample(example);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysRoleResult> get(Long id) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        return RespResult.ok("查询成功", SysRoleConvert.entityToResult(sysRole));
    }

    @Override
    public RespResult<PageResponse<SysRoleResult>> listPage(PageRequest pageRequest, SysRoleForm form) {
        SysRoleExample example = new SysRoleExample();
        //设置分页
        example.setStart((pageRequest.getPage() - 1) * pageRequest.getSize());
        example.setSize(pageRequest.getSize());

        //查询条件
        if (form != null) {
            SysRoleExample.Criteria criteria = example.createCriteria();

            //其它条件

        }

        //查询总记录
        long count = sysRoleMapper.countByExample(example);
        //查询分页列表
        List<SysRole> sysRoleList = sysRoleMapper.selectPageByExample(example);

        List<SysRoleResult> result = SysRoleConvert.entityListToResultList(sysRoleList);

        //返回结果
        return RespResult.ok("查询成功", new PageResponse<>(pageRequest.getPage(), pageRequest.getSize(), count, result));
    }

    @Override
    public RespResult<SysRoleResult> getResources(Long id) {
        List<Long> idList = sysRoleResourceService.getResourcesIdsByRoleId(id);
        return RespResult.ok("请求成功", idList);
    }

    @Override
    public RespResult<SysRoleResult> saveResources(Long id, String ids) {
        //删除以前的数据
        sysRoleResourceService.deleteByRoleId(id);

        if (ids != null && !"".equals(ids)) {
            //保存新数据
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++) {
                SysRoleResourceForm roleResourceForm = new SysRoleResourceForm();
                roleResourceForm.setRoleId(id);
                roleResourceForm.setResourceId(Long.valueOf(idArr[i]));
                sysRoleResourceService.add(roleResourceForm);
            }
        }
        return RespResult.ok("保存成功");
    }

    @Override
    public RespResult<List<SysRoleResult>> listAll(SysRoleForm form) {
        SysRoleExample example = new SysRoleExample();
        example.createCriteria().andStatusEqualTo(0);
        return RespResult.ok("保存成功", SysRoleConvert.entityListToResultList(sysRoleMapper.selectByExample(example)));
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