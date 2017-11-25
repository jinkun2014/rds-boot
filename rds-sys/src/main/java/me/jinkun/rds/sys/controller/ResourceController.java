package me.jinkun.rds.sys.controller;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.page.SimplePage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.core.support.web.CommonController;
import me.jinkun.rds.core.support.web.ResultCode;
import me.jinkun.rds.sys.entity.Resource;
import me.jinkun.rds.sys.model.Tree;
import me.jinkun.rds.sys.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 *
 * 资源-控制器
 * @author JinKun
 * @date 2017-11-25
 * @time 15:26
 */
@RestController
@RequestMapping("/sys/resource")
public class ResourceController extends CommonController {

    @Autowired
    IResourceService iResourceService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(Resource resource) {
        boolean flag = iResourceService.saveOrUpdate(resource);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") Long id, Resource resource) {
        resource.setId(id);
        boolean flag = iResourceService.saveOrUpdate(resource);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    /**
     * ids: 1,2,3或1
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(String ids) {
        Set<Long> idSets = idsToSets(ids);
        boolean flag = iResourceService.deleteByIds(idSets);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        Optional<Resource> resourceOptional = iResourceService.loadByPK(id, null);
        if (resourceOptional.isPresent()) {
            return setJsonViewData(resourceOptional.get());
        }
        return setJsonViewData(ResultCode.NO_EXISTS);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       Resource resource) {
        int totalRecordCount = iResourceService.loadCount(resource);
        IPage page = new SimplePage(pageNo, pageSize);
        Set<ISort> sortSet = Sets.newHashSet(new Sorter("id", false));
        List<Resource> resourceList = totalRecordCount == 0 ? Collections.EMPTY_LIST : iResourceService.loads(resource,null, sortSet, page);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", totalRecordCount);
        resultMap.put("list", resourceList);
        return setJsonViewData(resultMap);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public Object tree() {
        List<Tree> treeList = iResourceService.listTree();
        if(Objects.nonNull(treeList)){
            return setJsonViewData(treeList);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

}
