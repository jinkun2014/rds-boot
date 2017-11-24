package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.convert.SysRoleResourceConvert;
import me.jinkun.rds.sys.dao.SysRoleResourceMapper;
import me.jinkun.rds.sys.domain.SysRoleResource;
import me.jinkun.rds.sys.domain.SysRoleResourceExample;
import me.jinkun.rds.sys.service.SysRoleResourceService;
import me.jinkun.rds.sys.web.form.SysRoleResourceForm;
import me.jinkun.rds.sys.web.result.SysRoleResourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 角色-资源,数据库表为： sys_role_resource<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Service
@Transactional
public class SysRoleResourceServiceImpl implements SysRoleResourceService {

    @Autowired
    SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public RespResult<SysRoleResourceResult> save(SysRoleResourceForm form) {
        if (form.getId() == null) {
            return add(form);
        } else {
            return update(form.getId(), form);
        }
    }

    @Override
    public RespResult<SysRoleResourceResult> add(SysRoleResourceForm form) {
        SysRoleResource entity = SysRoleResourceConvert.formToEntity(form);
        entity.setId(null);
        sysRoleResourceMapper.insertSelective(entity);
        return RespResult.ok("保存成功");
    }

    @Override
    public RespResult<SysRoleResourceResult> update(Long id, SysRoleResourceForm form) {
        form.setId(id);
        sysRoleResourceMapper.updateByPrimaryKey(SysRoleResourceConvert.formToEntity(form));
        return RespResult.ok("更新成功", form);
    }

    public RespResult<SysRoleResourceResult> delete(Long id) {
        sysRoleResourceMapper.deleteByPrimaryKey(id);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysRoleResourceResult> deleteByIds(String ids) {
        List<Long> idList = idsToList(ids);
        SysRoleResourceExample example = new SysRoleResourceExample();
        example.createCriteria().andIdIn(idList);
        sysRoleResourceMapper.deleteByExample(example);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysRoleResourceResult> get(Long id) {
        SysRoleResource sysRoleResource = sysRoleResourceMapper.selectByPrimaryKey(id);
        return RespResult.ok("查询成功", SysRoleResourceConvert.entityToResult(sysRoleResource));
    }

    @Override
    public RespResult<PageResponse<SysRoleResourceResult>> listPage(PageRequest pageRequest, SysRoleResourceForm form) {
        SysRoleResourceExample example = new SysRoleResourceExample();
        //设置分页
        example.setStart((pageRequest.getPage() - 1) * pageRequest.getSize());
        example.setSize(pageRequest.getSize());

        //查询条件
        if (form != null) {
            SysRoleResourceExample.Criteria criteria = example.createCriteria();

            //其它条件

        }

        //查询总记录
        long count = sysRoleResourceMapper.countByExample(example);
        //查询分页列表
        List<SysRoleResource> sysRoleResourceList = sysRoleResourceMapper.selectPageByExample(example);

        List<SysRoleResourceResult> result = SysRoleResourceConvert.entityListToResultList(sysRoleResourceList);

        //返回结果
        return RespResult.ok("查询成功", new PageResponse<>(pageRequest.getPage(), pageRequest.getSize(), count, result));
    }

    @Override
    public List<Long> getResourcesIdsByRoleId(Long id) {
        SysRoleResourceExample example = new SysRoleResourceExample();
        example.createCriteria().andRoleIdEqualTo(id);
        List<SysRoleResource> sysRoleResourceList = sysRoleResourceMapper.selectByExample(example);
        return toIdList(sysRoleResourceList);
    }

    @Override
    public void deleteByRoleId(Long id) {
        SysRoleResourceExample example = new SysRoleResourceExample();
        example.createCriteria().andRoleIdEqualTo(id);
        sysRoleResourceMapper.deleteByExample(example);
    }

    private List<Long> toIdList(List<SysRoleResource> sysRoleResourceList) {
        List<Long> idList = new ArrayList<>();
        if (sysRoleResourceList != null && sysRoleResourceList.size() > 0) {
            for (SysRoleResource rr : sysRoleResourceList) {
                idList.add(rr.getResourceId());
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