package me.jinkun.rds.sys.controller;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.SimplePage;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.core.support.web.CommonController;
import me.jinkun.rds.core.support.web.ResultCode;
import me.jinkun.rds.sys.entity.UserRole;
import me.jinkun.rds.sys.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 *
 * 用户-角色-控制器
 * @author JinKun
 * @date 2017-11-27
 * @time 17:07
 */
@RestController
@RequestMapping("/sys/user/role")
public class UserRoleController extends CommonController {

    @Autowired
    IUserRoleService iUserRoleService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(UserRole userRole) {
        boolean flag = iUserRoleService.saveOrUpdate(userRole);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") Long id, UserRole userRole) {
        userRole.setId(id);
        boolean flag = iUserRoleService.saveOrUpdate(userRole);
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
        boolean flag = iUserRoleService.deleteByIds(idSets);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        Optional<UserRole> userRoleOptional = iUserRoleService.loadByPK(id, null);
        if (userRoleOptional.isPresent()) {
            return setJsonViewData(userRoleOptional.get());
        }
        return setJsonViewData(ResultCode.NO_EXISTS);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       UserRole userRole) {
        int totalRecordCount = iUserRoleService.loadCount(userRole);
        List<UserRole> userRoleList = totalRecordCount == 0 ? Collections.EMPTY_LIST : iUserRoleService.loads(userRole,null, Sets.newHashSet(new Sorter("id", false)), new SimplePage(pageNo, pageSize));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", totalRecordCount);
        resultMap.put("list", userRoleList);
        return setJsonViewData(resultMap);
    }

}
