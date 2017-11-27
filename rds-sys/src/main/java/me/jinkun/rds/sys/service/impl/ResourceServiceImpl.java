package me.jinkun.rds.sys.service.impl;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.sys.entity.Resource;
import me.jinkun.rds.sys.mapper.IResourceMapper;
import me.jinkun.rds.sys.mapper.IRoleResourceMapper;
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
    @Autowired
    IRoleResourceMapper iRoleResourceMapper;

    @Override
    public Optional<Resource> loadByPK(Long id, Set<String> fields) {
        Resource resource = iResourceMapper.loadByPK(id, fields);
        if (Objects.isNull(resource)) {
            return Optional.empty();
        }
        return Optional.of(resource);
    }

    @Override
    public List<Resource> loads(Resource resource, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return iResourceMapper.loads(resource, fields, sortSet, page);
    }

    @Override
    public int loadCount(Resource resource) {
        return iResourceMapper.loadCount(resource);
    }

    @Override
    public boolean saveOrUpdate(Resource resource) {
        boolean save = Objects.isNull(resource.getId());
        boolean flag;
        if (save) {
            flag = iResourceMapper.insert(resource) > 0;
        }
        flag = iResourceMapper.update(resource) > 0;
        //更新父机构状态
        if (flag && Objects.nonNull(resource.getPid())) {
            Resource parent = iResourceMapper.loadByPK(resource.getPid(), Sets.newHashSet("is_leaf"));
            parent.setIsLeaf(false);
            iResourceMapper.update(resource);
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
            List<Resource> childrenList = loadsByPid(id);
            if (Objects.nonNull(childrenList) && !childrenList.isEmpty()) {
                childrenList.stream().forEach(resource -> recursionDelete(resource.getId()));
            }
        }
    }

    /**
     * 更新当前节点的父节点的叶子情况
     *
     * @param id 当前节点的id
     */
    private void updateParent(Long id) {
        Optional<Resource> resourceOptional = loadByPK(id, Sets.newHashSet("pid"));
        if (resourceOptional.isPresent()) {
            Resource resource = resourceOptional.get();
            resource.setDelFlag(false);
            int count = loadCount(resource);
            if (count == 0) {
                Resource parent = new Resource();
                parent.setId(resource.getPid());
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
    private List<Resource> loadsByPid(Long id) {
        Resource resource = new Resource();
        resource.setPid(id);
        resource.setDelFlag(false);
        return loads(resource, Sets.newHashSet("id", "pid"), null, null);
    }

    /**
     * 逻辑删除
     *
     * @param id 资源id
     */
    private boolean logicDelete(Long id) {
        //逻辑删除组织
        Resource resource = new Resource();
        resource.setId(id);
        resource.setDelFlag(true);
        boolean flag = iResourceMapper.update(resource) > 0;

        //删除角色资源表
        if (flag) {
            iRoleResourceMapper.deleteByResourceIds(Sets.newHashSet(id));
        }
        return flag;
    }

    @Override
    public List<Tree> listTree() {
        Resource resource = new Resource();
        resource.setDelFlag(false);
        List<Resource> resourceList = iResourceMapper.loads(resource, null, Sets.newHashSet(new Sorter("seq", true)), null);
        return prepareTree(resourceList);
    }

    @Override
    public List<Resource> getByUserIdAndPid(Long userId, Long pid) {
        return iResourceMapper.getByUserIdAndPid(null, userId, pid);
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