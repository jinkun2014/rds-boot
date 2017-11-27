package me.jinkun.rds.sys.controller;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.page.SimplePage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.core.support.web.CommonController;
import me.jinkun.rds.core.support.web.ResultCode;
import me.jinkun.rds.sys.entity.RoleResource;
import me.jinkun.rds.sys.service.IRoleResourceService;
import cn.com.header.wjdc.web.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


/**
 *
 * 角色-资源-控制器
 * @author JinKun
 * @date 2017-11-27
 * @time 18:06
 */
@RestController
@RequestMapping("/sys/role/resource")
public class RoleResourceController extends CommonController {

    @Autowired
    IRoleResourceService iRoleResourceService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(RoleResource roleResource) {
        boolean flag = iRoleResourceService.saveOrUpdate(roleResource);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") Long id, RoleResource roleResource) {
        roleResource.setId(id);
        boolean flag = iRoleResourceService.saveOrUpdate(roleResource);
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
        boolean flag = iRoleResourceService.deleteByIds(idSets);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        Optional<RoleResource> roleResourceOptional = iRoleResourceService.loadByPK(id, null);
        if (roleResourceOptional.isPresent()) {
            return setJsonViewData(roleResourceOptional.get());
        }
        return setJsonViewData(ResultCode.NO_EXISTS);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       RoleResource roleResource) {
        int totalRecordCount = iRoleResourceService.loadCount(roleResource);
        List<RoleResource> roleResourceList = totalRecordCount == 0 ? Collections.EMPTY_LIST : iRoleResourceService.loads(roleResource,null, Sets.newHashSet(new Sorter("id", false)), new SimplePage(pageNo, pageSize));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", totalRecordCount);
        resultMap.put("list", roleResourceList);
        return setJsonViewData(resultMap);
    }

}
