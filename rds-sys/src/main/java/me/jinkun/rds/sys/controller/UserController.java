package me.jinkun.rds.sys.controller;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.SimplePage;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.core.support.web.CommonController;
import me.jinkun.rds.core.support.web.ResultCode;
import me.jinkun.rds.sys.entity.User;
import me.jinkun.rds.sys.entity.UserOrg;
import me.jinkun.rds.sys.model.UserExtend;
import me.jinkun.rds.sys.service.IUserOrgService;
import me.jinkun.rds.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 用户-控制器
 *
 * @author JinKun
 * @date 2017-11-25
 * @time 15:46
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends CommonController {

    @Autowired
    IUserService iUserService;
    @Autowired
    IUserOrgService iUserOrgService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(UserExtend userExtend) {
        boolean flag = iUserService.saveOrUpdate(userExtend);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") Long id, User user) {
        user.setId(id);
        boolean flag = iUserService.saveOrUpdate(user);
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
        boolean flag = iUserService.deleteByIds(idSets);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        Optional<UserExtend> userOptional = iUserService.loadUserExtendByPK(id, null);
        if (userOptional.isPresent()) {
            return setJsonViewData(userOptional.get());
        }
        return setJsonViewData(ResultCode.NO_EXISTS);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       User user, Long orgId) {
        if (orgId != null) {
            //查询userIds
            UserOrg userOrg = new UserOrg();
            userOrg.setOrgId(orgId);
            int totalRecordCount = iUserOrgService.loadCount(userOrg);
            Set<Long> ids = totalRecordCount == 0 ? Collections.EMPTY_SET : iUserOrgService.loadsUserId(userOrg);

            List<User> userList = totalRecordCount == 0 ? Collections.EMPTY_LIST : iUserService.loadsByIds(ids, null, Sets.newHashSet(new Sorter("id", false)), new SimplePage(pageNo, pageSize));
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", totalRecordCount);
            resultMap.put("list", userList);
            return setJsonViewData(resultMap);
        }


        int totalRecordCount = iUserService.loadCount(user);
        List<User> userList = totalRecordCount == 0 ? Collections.EMPTY_LIST : iUserService.loads(user, null, Sets.newHashSet(new Sorter("id", false)), new SimplePage(pageNo, pageSize));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", totalRecordCount);
        resultMap.put("list", userList);
        return setJsonViewData(resultMap);
    }

}
