package me.jinkun.rds.sys.controller;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.page.SimplePage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.core.support.web.CommonController;
import me.jinkun.rds.core.support.web.ResultCode;
import me.jinkun.rds.sys.entity.Org;
import me.jinkun.rds.sys.model.Tree;
import me.jinkun.rds.sys.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 *
 * 组织机构-控制器
 * @author JinKun
 * @date 2017-11-24
 * @time 11:37
 */
@RestController
@RequestMapping("/sys/org")
public class OrgController extends CommonController {

    @Autowired
    IOrgService iOrgService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(Org org) {
        boolean flag = iOrgService.saveOrUpdate(org);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") Long id, Org org) {
        org.setId(id);
        boolean flag = iOrgService.saveOrUpdate(org);
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
        boolean flag = iOrgService.deleteByIds(idSets);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        Optional<Org> orgOptional = iOrgService.loadByPK(id, null);
        if (orgOptional.isPresent()) {
            return setJsonViewData(orgOptional.get());
        }
        return setJsonViewData(ResultCode.NO_EXISTS);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       Org org) {
        org.setDelFlag(false);
        int totalRecordCount = iOrgService.loadCount(org);
        IPage page = new SimplePage(pageNo, pageSize);
        Set<ISort> sortSet = Sets.newHashSet(new Sorter("seq", true));
        List<Org> orgList = totalRecordCount == 0 ? Collections.EMPTY_LIST : iOrgService.loads(org,null, sortSet, page);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", totalRecordCount);
        resultMap.put("list", orgList);
        return setJsonViewData(resultMap);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public Object tree() {
        List<Tree> treeList = iOrgService.listTree();
        if(Objects.nonNull(treeList)){
            return setJsonViewData(treeList);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }
}
