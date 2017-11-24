package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.core.resp.Tree;
import me.jinkun.rds.sys.convert.SysOrgConvert;
import me.jinkun.rds.sys.dao.SysOrgMapper;
import me.jinkun.rds.sys.domain.SysOrg;
import me.jinkun.rds.sys.domain.SysOrgExample;
import me.jinkun.rds.sys.service.SysOrgService;
import me.jinkun.rds.sys.service.SysUserOrgService;
import me.jinkun.rds.sys.web.form.SysOrgForm;
import me.jinkun.rds.sys.web.result.SysOrgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 组织机构, 数据库表为： sys_org<br/>
 * @Autor: Created by Jin Kun on 2017-05-24.
 */
@Service
@Transactional
public class SysOrgServiceImpl implements SysOrgService {

    @Autowired
    SysOrgMapper sysOrgMapper;

    @Autowired
    SysUserOrgService sysUserOrgService;

    @Override
    public RespResult<SysOrgResult> save(SysOrgForm form) {
        if (form.getId() != null) {
            return update(form.getId(), form);
        } else {
            return add(form);
        }
    }

    @Override
    public RespResult<SysOrgResult> add(SysOrgForm form) {
        SysOrg entity = SysOrgConvert.formToEntity(form);
        entity.setId(null);
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        sysOrgMapper.insertSelective(entity);

        //更新父机构状态
        if (entity.getPid() != null) {
            SysOrg org = sysOrgMapper.selectByPrimaryKey(entity.getPid());
            org.setIsLeaf(1);
            sysOrgMapper.updateByPrimaryKey(org);
        }

        return RespResult.ok("保存成功");
    }

    @Override
    public RespResult<SysOrgResult> update(Long id, SysOrgForm form) {
        SysOrg old = sysOrgMapper.selectByPrimaryKey(id);

        SysOrg entity = SysOrgConvert.formToEntity(form);
        entity.setId(form.getId());
        entity.setUpdateTime(new Date());
        entity.setCreateTime(old.getCreateTime());
        sysOrgMapper.updateByPrimaryKey(entity);

        //更新父机构状态
        if (entity.getPid() != null) {
            SysOrg org = sysOrgMapper.selectByPrimaryKey(entity.getPid());
            org.setIsLeaf(1);
            sysOrgMapper.updateByPrimaryKey(org);
        }

        return RespResult.ok("更新成功", form);
    }

    public RespResult<SysOrgResult> delete(Long id) {
        sysOrgMapper.deleteByPrimaryKey(id);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysOrgResult> deleteByIds(String ids) {
        List<Long> idList = idsToList(ids);
        SysOrgExample example = new SysOrgExample();
        example.createCriteria().andIdIn(idList);
        sysOrgMapper.deleteByExample(example);

        //删除和用户的关联关系
        sysUserOrgService.deleteByOrgIdList(idList);

        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysOrgResult> get(Long id) {
        SysOrg sysOrg = sysOrgMapper.selectByPrimaryKey(id);
        return RespResult.ok("查询成功", SysOrgConvert.entityToResult(sysOrg));
    }

    @Override
    public RespResult<PageResponse<SysOrgResult>> listPage(PageRequest pageRequest, SysOrgForm form) {
        SysOrgExample example = new SysOrgExample();
        //设置分页
        example.setStart((pageRequest.getPage() - 1) * pageRequest.getSize());
        example.setSize(pageRequest.getSize());
        example.setOrderByClause("seq ASC");

        //查询条件
        if (form != null) {
            SysOrgExample.Criteria criteria = example.createCriteria();

            //其它条件

        }

        //查询总记录
        long count = sysOrgMapper.countByExample(example);
        //查询分页列表
        List<SysOrg> sysOrgList = sysOrgMapper.selectPageByExample(example);

        List<SysOrgResult> result = SysOrgConvert.entityListToResultList(sysOrgList);

        //返回结果
        return RespResult.ok("查询成功", new PageResponse<>(pageRequest.getPage(), pageRequest.getSize(), count, result));
    }

    @Override
    public RespResult<List<Tree>> listTree(SysOrgForm form) {
        SysOrgExample example = new SysOrgExample();
        example.setOrderByClause("seq ASC");
        example.createCriteria().andDelFlagEqualTo(0);
        List<SysOrg> orgList = sysOrgMapper.selectByExample(example);
        return RespResult.ok("查询成功", prepareTree(orgList));
    }

    private List<Tree> prepareTree(List<SysOrg> orgList) {
        List<Tree> allTreeList = orgListToTreeList(orgList);
        List<Tree> topList = new ArrayList<>();
        for (Tree tree : allTreeList) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (tree.getPid() == null) {
                tree.setChildren(prepareTreeChiled(tree.getId(), allTreeList));
                topList.add(tree);
            }
        }
        return topList;
    }

    private List<Tree> prepareTreeChiled(Long id, List<Tree> allTreeList) {
        // 子菜单
        List<Tree> childList = new ArrayList<>();
        for (Tree tree : allTreeList) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (tree.getPid() != null && tree.getPid().equals(id)) {
                childList.add(tree);
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Tree tree : childList) {
            if (tree.getIsLeaf() == 1) {
                tree.setChildren(prepareTreeChiled(tree.getId(), allTreeList));
            }
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    private List<Tree> orgListToTreeList(List<SysOrg> orgList) {
        List<Tree> treeList = new ArrayList<>();
        if (orgList != null && orgList.size() > 0) {
            for (SysOrg org : orgList) {
                treeList.add(orgToTree(org));
            }
        }
        return treeList;
    }

    private Tree orgToTree(SysOrg org) {
        Tree tree = new Tree();
        tree.setId(org.getId());
        tree.setText(org.getName());
        tree.setIconCls(org.getIcon());
        tree.setIsLeaf(org.getIsLeaf());
        tree.setPid(org.getPid());
        return tree;
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