package me.jinkun.rds.sys.controller;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.page.SimplePage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.core.support.web.CommonController;
import me.jinkun.rds.core.support.web.ResultCode;
import me.jinkun.rds.sys.entity.UserOrg;
import me.jinkun.rds.sys.service.IUserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 *
 * 用户-机构-控制器
 * @author JinKun
 * @date 2017-11-25
 * @time 16:09
 */
@RestController
@RequestMapping("/sys/user/org")
public class UserOrgController extends CommonController {

    @Autowired
    IUserOrgService iUserOrgService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(UserOrg userOrg) {
        boolean flag = iUserOrgService.saveOrUpdate(userOrg);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") Long id, UserOrg userOrg) {
        userOrg.setId(id);
        boolean flag = iUserOrgService.saveOrUpdate(userOrg);
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
        boolean flag = iUserOrgService.deleteByIds(idSets);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        Optional<UserOrg> userOrgOptional = iUserOrgService.loadByPK(id, null);
        if (userOrgOptional.isPresent()) {
            return setJsonViewData(userOrgOptional.get());
        }
        return setJsonViewData(ResultCode.NO_EXISTS);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       UserOrg userOrg) {
        int totalRecordCount = iUserOrgService.loadCount(userOrg);
        IPage page = new SimplePage(pageNo, pageSize);
        Set<ISort> sortSet = Sets.newHashSet(new Sorter("id", false));
        List<UserOrg> userOrgList = totalRecordCount == 0 ? Collections.EMPTY_LIST : iUserOrgService.loads(userOrg,null, sortSet, page);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", totalRecordCount);
        resultMap.put("list", userOrgList);
        return setJsonViewData(resultMap);
    }

}
