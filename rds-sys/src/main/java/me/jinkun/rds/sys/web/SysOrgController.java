package me.jinkun.rds.sys.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.jinkun.rds.authorization.annotation.Authorization;
import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.core.resp.Tree;
import me.jinkun.rds.sys.service.SysOrgService;
import me.jinkun.rds.sys.web.form.SysOrgForm;
import me.jinkun.rds.sys.web.result.SysOrgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: 组织机构, 数据库表为： sys_org<br/>
 * @Autor: Created by Jin Kun on 2017-05-24.
 */
@Api(description = "组织机构")
@Authorization
@Controller
public class SysOrgController {

    @Autowired
    SysOrgService sysOrgService;

    @ApiOperation(value = "页面跳转", notes = "页面跳转:例如sys-org-{ui}.html")
    @RequestMapping(value = "/sys/orgs/ui/{ui}.html", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "sys/sys-org/sys-org-" + ui;
    }

    @ApiOperation(value = "新增或更新组织机构", notes = "新增或更新组织机构,主要是方便给不支持PUT的Form调用")
    @RequestMapping(value = "/sys/orgs/save", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysOrgResult> save(SysOrgForm form) {
        return sysOrgService.save(form);
    }

    @ApiOperation(value = "新增组织机构", notes = "新增组织机构")
    @RequestMapping(value = "/sys/orgs", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysOrgResult> add(SysOrgForm form) {
        return sysOrgService.add(form);
    }

    @ApiOperation(value = "更新组织机构", notes = "更新组织机构")
    @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "Long")
    @RequestMapping(value = "/sys/orgs/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public RespResult<SysOrgResult> update(@PathVariable("id") Long id, SysOrgForm form) {
        return sysOrgService.update(id, form);
    }

    @ApiOperation(value = "删除组织机构", notes = "删除组织机构")
    @ApiImplicitParam(name = "ids", value = "例如：1 或者 1,2,3", required = true, dataType = "String")
    @RequestMapping(value = "/sys/orgs/delete", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysOrgResult> delete(String ids) {
        return sysOrgService.deleteByIds(ids);
    }

    @ApiOperation(value = "获取组织机构列表", notes = "分页获取列表默认第1页10条数据")
    @RequestMapping(value = "/sys/orgs", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<PageResponse<SysOrgResult>> list(PageRequest pageRequest, SysOrgForm form) {
        return sysOrgService.listPage(pageRequest, form);
    }

    @ApiOperation(value = "获取组织机构", notes = "获取组织机构")
    @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "Long")
    @RequestMapping(value = "/sys/orgs/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<SysOrgResult> get(@PathVariable("id") Long id) {
        return sysOrgService.get(id);
    }


    @ApiOperation(value = "获取组织机构树", notes = "获取组织机构树型结构")
    @RequestMapping(value = "/sys/orgs/tree", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<List<Tree>> tree(SysOrgForm form) {
        return sysOrgService.listTree(form);
    }
}
