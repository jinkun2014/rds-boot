package me.jinkun.rds.sys.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.jinkun.rds.authorization.annotation.Authorization;
import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.service.SysUserService;
import me.jinkun.rds.sys.web.form.SysUserForm;
import me.jinkun.rds.sys.web.result.SysUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 用户,数据库表为： sys_user<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Api(description = "用户")
@Authorization
@Controller
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @ApiOperation(value = "页面跳转", notes = "页面跳转:例如sys-user-{ui}.html")
    @RequestMapping(value = "/sys/users/ui/{ui}.html", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "sys/sys-user/sys-user-" + ui;
    }

    @ApiOperation(value = "新增或更新用户", notes = "新增或更新用户,主要是方便给不支持PUT的Form调用")
    @RequestMapping(value = "/sys/users/save", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysUserResult> save(SysUserForm form) {
        return sysUserService.save(form);
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @RequestMapping(value = "/sys/users", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysUserResult> add(SysUserForm form) {
        return sysUserService.add(form);
    }

    @ApiOperation(value = "更新用户", notes = "更新用户")
    @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "Long")
    @RequestMapping(value = "/sys/users/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public RespResult<SysUserResult> update(@PathVariable("id") Long id, SysUserForm form) {
        return sysUserService.update(id,form);
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParam(name = "ids", value = "例如：1 或者 1,2,3", required = true, dataType = "String")
    @RequestMapping(value = "/sys/users/delete", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysUserResult> delete(String ids) {
        return sysUserService.deleteByIds(ids);
    }

    @ApiOperation(value = "获取用户列表", notes = "分页获取列表默认第1页10条数据")
    @RequestMapping(value = "/sys/users", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<PageResponse<SysUserResult>> list(PageRequest pageRequest, SysUserForm form) {
        return sysUserService.listPage(pageRequest,form);
    }

    @ApiOperation(value = "获取用户", notes = "获取用户")
    @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "Long")
    @RequestMapping(value = "/sys/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<SysUserResult> get(@PathVariable("id") Long id) {
        return sysUserService.get(id);
    }
}
