package me.jinkun.rds.sys.service.impl;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.sys.entity.Resource;
import me.jinkun.rds.sys.mapper.IResourceMapper;
import me.jinkun.rds.sys.model.Tree;
import me.jinkun.rds.sys.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 资源-业务实现
 *
 * @author JinKun
 * @date 2017-11-25
 * @time 15:26
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    IResourceMapper iResourceMapper;

    @Override
    public Optional<Resource> loadByPK(Long id, Set<String> fields) {
        Resource resource = iResourceMapper.loadByPK(id, fields);
        if (Objects.isNull(resource)) {
            return Optional.empty();
        }
        return Optional.of(resource);
    }

    public List<Resource> loads(Resource resource, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return iResourceMapper.loads(resource, fields, sortSet, page);
    }

    public int loadCount(Resource resource) {
        return iResourceMapper.loadCount(resource);
    }

    public boolean saveOrUpdate(Resource resource) {
        boolean save = Objects.isNull(resource.getId());
        if (save) {
            return iResourceMapper.insert(resource) > 0;
        }
        return iResourceMapper.update(resource) > 0;
    }

    public boolean deleteByIds(Set<Long> ids) {
        return iResourceMapper.deleteByIds(ids) > 0;
    }

    @Override
    public List<Tree> listTree() {
        Resource resource = new Resource();
        resource.setDelFlag(false);
        List<Resource> resourceList = iResourceMapper.loads(resource, null, Sets.newHashSet(new Sorter("seq", true)), null);
        return prepareTree(resourceList);
    }

    private List<Tree> prepareTree(List<Resource> resourceList) {
        List<Tree> allTreeList = resourceListToTreeList(resourceList);
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
                            if (tree.isLeaf()) {
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

    private List<Tree> resourceListToTreeList(List<Resource> resourceList) {
        return resourceList.stream().map(resource -> resourceToTree(resource)).collect(Collectors.toList());
    }

    private Tree resourceToTree(Resource resource) {
        Tree tree = new Tree();
        tree.setId(resource.getId());
        tree.setText(resource.getName());
        tree.setIconCls(resource.getIcon());
        tree.setLeaf(resource.getIsLeaf());
        tree.setPid(resource.getPid());
        return tree;
    }
}