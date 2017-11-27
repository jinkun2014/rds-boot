package me.jinkun.rds.sys.controller;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.SimplePage;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.core.support.web.CommonController;
import me.jinkun.rds.core.support.web.ResultCode;
import me.jinkun.rds.sys.entity.Role;
import me.jinkun.rds.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 角色-控制器
 *
 * @author JinKun
 * @date 2017-11-27
 * @time 17:04
 */
@RestController
@RequestMapping("/sys/role")
public class RoleController extends CommonController {

    @Autowired
    IRoleService iRoleService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(Role role) {
        boolean flag = iRoleService.saveOrUpdate(role);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") Long id, Role role) {
        role.setId(id);
        boolean flag = iRoleService.saveOrUpdate(role);
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
        boolean flag = iRoleService.deleteByIds(idSets);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        Optional<Role> roleOptional = iRoleService.loadByPK(id, null);
        if (roleOptional.isPresent()) {
            return setJsonViewData(roleOptional.get());
        }
        return setJsonViewData(ResultCode.NO_EXISTS);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       Role role) {
        role.setDelFlag(false);
        int totalRecordCount = iRoleService.loadCount(role);
        List<Role> roleList = totalRecordCount == 0 ? Collections.EMPTY_LIST : iRoleService.loads(role, null, Sets.newHashSet(new Sorter("id", false)), new SimplePage(pageNo, pageSize));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", totalRecordCount);
        resultMap.put("list", roleList);
        return setJsonViewData(resultMap);
    }

    /**
     * 查询所有角色列表
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Object list(Role role) {
        role.setDelFlag(false);
        int totalRecordCount = iRoleService.loadCount(role);
        List<Role> roleList = totalRecordCount == 0 ? Collections.EMPTY_LIST : iRoleService.loads(role, Sets.newHashSet("id", "name"), Sets.newHashSet(new Sorter("id", false)), null);
        return setJsonViewData(roleList);
    }

    /**
     * 根据角色id获取资源
     */
    @RequestMapping(value = "/{id}/resources", method = RequestMethod.GET)
    public Object getResources(@PathVariable("id") Long id) {
        return setJsonViewData(iRoleService.getResourceIds(id));
    }

    @RequestMapping(value = "{id}/resources", method = RequestMethod.POST)
    public Object saveResources(@PathVariable("id") Long id, String ids) {
        Set<Long> resourceIds = idsToSets(ids);
        boolean flag = iRoleService.saveResourceIds(id, resourceIds);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }
}
