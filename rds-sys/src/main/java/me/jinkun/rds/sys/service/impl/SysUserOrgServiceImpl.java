package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.convert.SysUserOrgConvert;
import me.jinkun.rds.sys.dao.SysUserOrgMapper;
import me.jinkun.rds.sys.domain.SysUser;
import me.jinkun.rds.sys.domain.SysUserOrg;
import me.jinkun.rds.sys.domain.SysUserOrgExample;
import me.jinkun.rds.sys.service.SysUserOrgService;
import me.jinkun.rds.sys.service.SysUserService;
import me.jinkun.rds.sys.web.form.SysUserOrgForm;
import me.jinkun.rds.sys.web.result.SysUserOrgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户-机构,数据库表为： sys_user_org<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Service
@Transactional
public class SysUserOrgServiceImpl implements SysUserOrgService {

    @Autowired
    SysUserOrgMapper sysUserOrgMapper;

    @Autowired
    SysUserService sysUserService;

    @Override
    public RespResult<SysUserOrgResult> save(SysUserOrgForm form) {
        if (form.getId() == null) {
            return add(form);
        } else {
            return update(form.getId(), form);
        }
    }

    @Override
    public RespResult<SysUserOrgResult> add(SysUserOrgForm form) {
        form.setId(null);
        SysUserOrg entity = SysUserOrgConvert.formToEntity(form);
        sysUserOrgMapper.insertSelective(entity);
        return RespResult.ok("保存成功");
    }

    @Override
    public RespResult<SysUserOrgResult> update(Long id, SysUserOrgForm form) {
        form.setId(id);
        sysUserOrgMapper.updateByPrimaryKey(SysUserOrgConvert.formToEntity(form));
        return RespResult.ok("更新成功", form);
    }

    public RespResult<SysUserOrgResult> delete(Long id) {
        sysUserOrgMapper.deleteByPrimaryKey(id);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysUserOrgResult> deleteByIds(String ids) {
        List<Long> idList = idsToList(ids);
        SysUserOrgExample example = new SysUserOrgExample();
        example.createCriteria().andIdIn(idList);
        sysUserOrgMapper.deleteByExample(example);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysUserOrgResult> get(Long id) {
        SysUserOrg sysUserOrg = sysUserOrgMapper.selectByPrimaryKey(id);
        return RespResult.ok("查询成功", SysUserOrgConvert.entityToResult(sysUserOrg));
    }

    @Override
    public RespResult<PageResponse<SysUserOrgResult>> listPage(PageRequest pageRequest, SysUserOrgForm form) {
        SysUserOrgExample example = new SysUserOrgExample();
        //设置分页
        example.setStart((pageRequest.getPage() - 1) * pageRequest.getSize());
        example.setSize(pageRequest.getSize());

        //查询条件
        if (form != null) {
            SysUserOrgExample.Criteria criteria = example.createCriteria();

            //其它条件

        }

        //查询总记录
        long count = sysUserOrgMapper.countByExample(example);
        //查询分页列表
        List<SysUserOrg> sysUserOrgList = sysUserOrgMapper.selectPageByExample(example);

        List<SysUserOrgResult> result = SysUserOrgConvert.entityListToResultList(sysUserOrgList);

        //返回结果
        return RespResult.ok("查询成功", new PageResponse<>(pageRequest.getPage(), pageRequest.getSize(), count, result));
    }

    @Override
    public List<SysUser> findUserListByOrgId(Long orgId) {
        List<Long> userIdList = findUserIdListByOrgId(orgId);
        if (userIdList != null) {
            return sysUserService.findListByIds(userIdList);
        }
        return null;
    }

    private List<Long> toUserIdList(List<SysUserOrg> userOrgList) {
        if (userOrgList != null && userOrgList.size() > 0) {
            List<Long> userIdList = new ArrayList<>();
            for (SysUserOrg uo : userOrgList) {
                userIdList.add(uo.getUserId());
            }
            return userIdList;
        }
        return null;
    }

    @Override
    public List<Long> findUserIdListByOrgId(Long orgId) {
        SysUserOrgExample example = new SysUserOrgExample();
        example.createCriteria().andOrgIdEqualTo(orgId);
        List<SysUserOrg> userOrgList = sysUserOrgMapper.selectByExample(example);
        return toUserIdList(userOrgList);
    }

    @Override
    public SysUserOrg getByUserId(Long userId) {
        SysUserOrgExample example = new SysUserOrgExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<SysUserOrg> userOrgList = sysUserOrgMapper.selectByExample(example);
        if (userOrgList != null && userOrgList.size() > 0) {
            return userOrgList.get(0);
        }
        return null;
    }

    @Override
    public void deleteByUserIdList(List<Long> idList) {
        SysUserOrgExample example = new SysUserOrgExample();
        example.createCriteria().andUserIdIn(idList);
        sysUserOrgMapper.deleteByExample(example);
    }

    @Override
    public void deleteByOrgIdList(List<Long> idList) {
        SysUserOrgExample example = new SysUserOrgExample();
        example.createCriteria().andOrgIdIn(idList);
        sysUserOrgMapper.deleteByExample(example);
    }

    @Override
    public void deleteByUserId(Long id) {
        SysUserOrgExample example = new SysUserOrgExample();
        example.createCriteria().andUserIdEqualTo(id);
        sysUserOrgMapper.deleteByExample(example);
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