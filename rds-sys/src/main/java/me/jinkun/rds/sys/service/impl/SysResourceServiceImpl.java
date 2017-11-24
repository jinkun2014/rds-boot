package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.core.resp.Tree;
import me.jinkun.rds.sys.convert.SysResourceConvert;
import me.jinkun.rds.sys.dao.SysResourceMapper;
import me.jinkun.rds.sys.domain.SysResource;
import me.jinkun.rds.sys.domain.SysResourceExample;
import me.jinkun.rds.sys.service.SysResourceService;
import me.jinkun.rds.sys.web.form.SysResourceForm;
import me.jinkun.rds.sys.web.result.SysResourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 资源, 数据库表为： sys_resource<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Service
@Transactional
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    SysResourceMapper sysResourceMapper;

    @Override
    public RespResult<SysResourceResult> save(SysResourceForm form) {
        if (form.getId() == null) {
            return add(form);
        } else {
            return update(form.getId(), form);
        }
    }

    @Override
    public RespResult<SysResourceResult> add(SysResourceForm form) {
        SysResource entity = SysResourceConvert.formToEntity(form);
        entity.setId(null);
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        sysResourceMapper.insertSelective(entity);

        //更新父节点状态
        if (entity.getPid() != null) {
            SysResource parent = sysResourceMapper.selectByPrimaryKey(entity.getPid());
            parent.setIsLeaf(1);
            sysResourceMapper.updateByPrimaryKey(parent);
        }

        return RespResult.ok("保存成功");
    }

    @Override
    public RespResult<SysResourceResult> update(Long id, SysResourceForm form) {
        SysResource old = sysResourceMapper.selectByPrimaryKey(id);

        SysResource entity = SysResourceConvert.formToEntity(form);
        entity.setId(id);
        entity.setIsLeaf(old.getIsLeaf());
        entity.setDelFlag(old.getDelFlag());
        entity.setUpdateTime(new Date());
        entity.setCreateTime(old.getCreateTime());
        sysResourceMapper.updateByPrimaryKey(entity);

        //更新父节点状态
        if (entity.getPid() != null) {
            SysResource parent = sysResourceMapper.selectByPrimaryKey(entity.getPid());
            parent.setIsLeaf(1);
            sysResourceMapper.updateByPrimaryKey(parent);
        }

        return RespResult.ok("更新成功", form);
    }

    public RespResult<SysResourceResult> delete(Long id) {
        sysResourceMapper.deleteByPrimaryKey(id);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysResourceResult> deleteByIds(String ids) {
        List<Long> idList = idsToList(ids);
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andIdIn(idList);
        sysResourceMapper.deleteByExample(example);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysResourceResult> get(Long id) {
        SysResource sysResource = sysResourceMapper.selectByPrimaryKey(id);
        return RespResult.ok("查询成功", SysResourceConvert.entityToResult(sysResource));
    }

    @Override
    public RespResult<PageResponse<SysResourceResult>> listPage(PageRequest pageRequest, SysResourceForm form) {
        SysResourceExample example = new SysResourceExample();
        //设置分页
        example.setStart((pageRequest.getPage() - 1) * pageRequest.getSize());
        example.setSize(pageRequest.getSize());

        //排序
        example.setOrderByClause("seq ASC");

        //查询条件
        if (form != null) {
            SysResourceExample.Criteria criteria = example.createCriteria();

            //其它条件

        }

        //查询总记录
        long count = sysResourceMapper.countByExample(example);
        //查询分页列表
        List<SysResource> sysResourceList = sysResourceMapper.selectPageByExample(example);

        List<SysResourceResult> result = SysResourceConvert.entityListToResultList(sysResourceList);

        //返回结果
        return RespResult.ok("查询成功", new PageResponse<>(pageRequest.getPage(), pageRequest.getSize(), count, result));
    }

    @Override
    public RespResult<List<Tree>> listTree(SysResourceForm form) {
        SysResourceExample example = new SysResourceExample();
        example.setOrderByClause("seq ASC");
        example.createCriteria();
        List<SysResource> resourceList = sysResourceMapper.selectByExample(example);
        return RespResult.ok("查询成功", prepareTree(resourceList));
    }

    //SELECT * FROM sys_resource WHERE pid = -1 AND id IN (SELECT resource_id FROM sys_role_resource WHERE role_id IN (SELECT role_id FROM sys_user_role WHERE user_id = 38));
    @Override
    public List<SysResource> getByUserIdAndPid(Long userId, Long pid) {
        return sysResourceMapper.selectByUserIdAndPid(userId, pid);
    }


    private List<Tree> prepareTree(List<SysResource> resourceList) {
        List<Tree> allTreeList = resourceListToTreeList(resourceList);
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

    private List<Tree> resourceListToTreeList(List<SysResource> resourceList) {
        List<Tree> treeList = new ArrayList<>();
        if (resourceList != null && resourceList.size() > 0) {
            for (SysResource resource : resourceList) {
                treeList.add(resourceToTree(resource));
            }
        }
        return treeList;
    }

    private Tree resourceToTree(SysResource resource) {
        Tree tree = new Tree();
        tree.setId(resource.getId());
        tree.setText(resource.getName());
        tree.setIconCls(resource.getIcon());
        tree.setIsLeaf(resource.getIsLeaf());
        tree.setPid(resource.getPid());
        tree.setAttributes(resource);
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