package me.jinkun.rds.sys.service.impl;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.sys.entity.Org;
import me.jinkun.rds.sys.mapper.IOrgMapper;
import me.jinkun.rds.sys.model.Tree;
import me.jinkun.rds.sys.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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

    @Override
    public Optional<Org> loadByPK(Long id, Set<String> fields) {
        Org org = iOrgMapper.loadByPK(id, fields);
        if (Objects.isNull(org)) {
            return Optional.empty();
        }
        return Optional.of(org);
    }

    public List<Org> loads(Org org, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return iOrgMapper.loads(org, fields, sortSet, page);
    }

    public int loadCount(Org org) {
        return iOrgMapper.loadCount(org);
    }

    public boolean saveOrUpdate(Org org) {
        int count = 0;
        boolean save = Objects.isNull(org.getId());
        if (save) {
            count = iOrgMapper.insert(org);
        }
        count = iOrgMapper.update(org);
        //更新父机构状态
        if (count > 0 && Objects.nonNull(org.getPid())) {
            Org parent = iOrgMapper.loadByPK(org.getPid(), Sets.newHashSet("is_leaf"));
            parent.setIsLeaf(false);
            iOrgMapper.update(org);
        }
        return count > 0;
    }

    public boolean deleteByIds(Set<Long> ids) {
        return iOrgMapper.deleteByIds(ids) > 0;
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
                        .collect(Collectors.toList());

        childList.stream().map(tree -> {
            if (tree.isLeaf()) {
                // 把子菜单的子菜单再循环一遍
                allTreeList.removeAll(childList);
                tree.setChildren(prepareTreeChiled(tree.getId(), allTreeList));
            }
            return tree;
        });
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