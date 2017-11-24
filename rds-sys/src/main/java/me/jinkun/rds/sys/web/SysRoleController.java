package me.jinkun.rds.sys.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.jinkun.rds.authorization.annotation.Authorization;
import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.service.SysRoleService;
import me.jinkun.rds.sys.web.form.SysRoleForm;
import me.jinkun.rds.sys.web.result.SysRoleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: 角色, 数据库表为： sys_role<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Api(description = "角色")
@Authorization
@Controller
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;

    @ApiOperation(value = "页面跳转", notes = "页面跳转:例如sys-role-{ui}.html")
    @RequestMapping(value = "/sys/roles/ui/{ui}.html", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "sys/sys-role/sys-role-" + ui;
    }

    @ApiOperation(value = "新增或更新角色", notes = "新增或更新角色,主要是方便给不支持PUT的Form调用")
    @RequestMapping(value = "/sys/roles/save", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysRoleResult> save(SysRoleForm form) {
        return sysRoleService.save(form);
    }

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @RequestMapping(value = "/sys/roles", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysRoleResult> add(SysRoleForm form) {
        return sysRoleService.add(form);
    }

    @ApiOperation(value = "更新角色", notes = "更新角色")
    @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "Long")
    @RequestMapping(value = "/sys/roles/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public RespResult<SysRoleResult> update(@PathVariable("id") Long id, SysRoleForm form) {
        return sysRoleService.update(id, form);
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @ApiImplicitParam(name = "ids", value = "例如：1 或者 1,2,3", required = true, dataType = "String")
    @RequestMapping(value = "/sys/roles/delete", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysRoleResult> delete(String ids) {
        return sysRoleService.deleteByIds(ids);
    }

    @ApiOperation(value = "获取角色列表", notes = "分页获取列表默认第1页10条数据")
    @RequestMapping(value = "/sys/roles", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<PageResponse<SysRoleResult>> list(PageRequest pageRequest, SysRoleForm form) {
        return sysRoleService.listPage(pageRequest, form);
    }

    @ApiOperation(value = "获取角色", notes = "获取角色")
    @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "Long")
    @RequestMapping(value = "/sys/roles/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<SysRoleResult> get(@PathVariable("id") Long id) {
        return sysRoleService.get(id);
    }

    @ApiOperation(value = "获取角色对应的资源", notes = "获取角色对应的资源")
    @RequestMapping(value = "/sys/roles/{id}/resources", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<SysRoleResult> getResources(@PathVariable("id") Long id) {
        return sysRoleService.getResources(id);
    }

    @ApiOperation(value = "保存角色对应的资源", notes = "保存角色对应的资源")
    @RequestMapping(value = "/sys/roles/{id}/resources", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysRoleResult> saveResources(@PathVariable("id") Long id, String ids) {
        return sysRoleService.saveResources(id, ids);
    }

    @ApiOperation(value = "获取全部角色列表", notes = "获取全部角色列表")
    @RequestMapping(value = "/sys/roles/all", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<List<SysRoleResult>> listAll(SysRoleForm form) {
        return sysRoleService.listAll(form);
    }
}
