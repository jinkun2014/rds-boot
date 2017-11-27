package me.jinkun.rds.sys.service.impl;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.sys.entity.Org;
import me.jinkun.rds.sys.mapper.IOrgMapper;
import me.jinkun.rds.sys.mapper.IUserOrgMapper;
import me.jinkun.rds.sys.model.Tree;
import me.jinkun.rds.sys.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 组织机构-业务实现
 *
 * @author JinKun
 * @date 2017-11-24
 * @time 11:37
 */
@Service
public class OrgServiceImpl implements IOrgService {

    @Autowired
    IOrgMapper iOrgMapper;
    @Autowired
    IUserOrgMapper iUserOrgMapper;

    @Override
    public Optional<Org> loadByPK(Long id, Set<String> fields) {
        Org org = iOrgMapper.loadByPK(id, fields);
        if (Objects.isNull(org)) {
            return Optional.empty();
        }
        return Optional.of(org);
    }

    @Override
    public List<Org> loads(Org org, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return iOrgMapper.loads(org, fields, sortSet, page);
    }

    @Override
    public int loadCount(Org org) {
        return iOrgMapper.loadCount(org);
    }

    @Override
    public boolean saveOrUpdate(Org org) {
        boolean save = Objects.isNull(org.getId());
        Date now = new Date();
        boolean flag = false;
        if (save) {
            org.setIsLeaf(true);
            org.setDelFlag(false);
            org.setUpdateTime(now);
            org.setCreateTime(now);
            flag = iOrgMapper.insert(org) > 0;
        }
        org.setUpdateTime(now);
        flag = iOrgMapper.update(org) > 0;
        //更新父机构状态
        if (flag && Objects.nonNull(org.getPid())) {
            Org parent = iOrgMapper.loadByPK(org.getPid(), Sets.newHashSet("id", "is_leaf"));
            if (parent.getIsLeaf()) {
                parent.setIsLeaf(false);
                saveOrUpdate(parent);
            }
        }
        return flag;
    }

    @Override
    public boolean deleteByIds(Set<Long> ids) {
        ids.stream().forEach(id -> {
            recursionDelete(id);
            updateParent(id);
        });
        return true;
    }

    private void recursionDelete(Long id) {
        boolean flag = logicDelete(id);
        if (flag) {
            List<Org> childrenList = loadsByPid(id);
            if (Objects.nonNull(childrenList) && !childrenList.isEmpty()) {
                childrenList.stream().forEach(org -> recursionDelete(org.getId()));
            }
        }
    }

    /**
     * 更新当前节点的父节点的叶子情况
     *
     * @param id 当前节点的id
     */
    private void updateParent(Long id) {
        Optional<Org> orgOptional = loadByPK(id, Sets.newHashSet("pid"));
        if (orgOptional.isPresent()) {
            Org org = orgOptional.get();
            org.setDelFlag(false);
            int count = loadCount(org);
            if (count == 0) {
                Org parent = new Org();
                parent.setId(org.getPid());
                parent.setIsLeaf(true);
                saveOrUpdate(parent);
            }
        }
    }

    /**
     * 根据pid加载列表
     *
     * @param id 父节点id
     * @return
     */
    private List<Org> loadsByPid(Long id) {
        Org org = new Org();
        org.setPid(id);
        org.setDelFlag(false);
        return loads(org, Sets.newHashSet("id", "pid"), null, null);
    }

    /**
     * 逻辑删除
     *
     * @param id 组织id
     */
    private boolean logicDelete(Long id) {
        //逻辑删除组织
        Org org = new Org();
        org.setId(id);
        org.setDelFlag(true);
        boolean flag = iOrgMapper.update(org) > 0;

        //更新中间表
        if (flag) {
            iUserOrgMapper.deleteByOrgIds(Sets.newHashSet(id));
        }
        return flag;
    }

    @Override
    public List<Tree> listTree() {
        Org org = new Org();
        org.setDelFlag(false);
        List<Org> orgList = iOrgMapper.loads(org, null, Sets.newHashSet(new Sorter("seq", true)), null);
        return prepareTree(orgList);
    }

    private List<Tree> prepareTree(List<Org> orgList) {
        List<Tree> allTreeList = orgListToTreeList(orgList);
        List<Tree> topList = allTreeList.stream()
                .filter(tree -> tree.getPid() == null)
                .map(tree -> {
                    tree.setChildren(prepareTreeChiled(tree.getId(), allTreeList));
                    return tree;
                })
                .collect(Collectors.toList());
        return topList;
    }

    private List<Tree> prepareTreeChiled(Long id, List<Tree> allTreeList) {
        // 子菜单
        List<Tree> childList =
                allTreeList.stream()
                        .filter(tree -> tree.getPid() != null && tree.getPid().equals(id))
                        .map(tree -> {
                            if (!tree.isLeaf()) {
                                // 把子菜单的子菜单再循环一遍
                                tree.setChildren(prepareTreeChiled(tree.getId(), allTreeList));
                            }
                            return tree;
                        })
                        .collect(Collectors.toList());

        // 递归退出条件
        if (Objects.isNull(childList) || childList.isEmpty()) {
            return null;
        }
        return childList;
    }

    private List<Tree> orgListToTreeList(List<Org> orgList) {
        return orgList.stream().map(org -> orgToTree(org)).collect(Collectors.toList());
    }

    private Tree orgToTree(Org org) {
        Tree tree = new Tree();
        tree.setId(org.getId());
        tree.setText(org.getName());
        tree.setIconCls(org.getIcon());
        tree.setLeaf(org.getIsLeaf());
        tree.setPid(org.getPid());
        return tree;
    }
}