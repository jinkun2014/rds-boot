package me.jinkun.rds.sys.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.jinkun.rds.authorization.annotation.Authorization;
import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.core.resp.Tree;
import me.jinkun.rds.sys.service.SysResourceService;
import me.jinkun.rds.sys.web.form.SysResourceForm;
import me.jinkun.rds.sys.web.result.SysResourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: 资源,数据库表为： sys_resource<br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Api(description = "资源")
@Authorization
@Controller
public class SysResourceController {

    @Autowired
    SysResourceService sysResourceService;

    @ApiOperation(value = "页面跳转", notes = "页面跳转:例如sys-resource-{ui}.html")
    @RequestMapping(value = "/sys/resources/ui/{ui}.html", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "sys/sys-resource/sys-resource-" + ui;
    }

    @ApiOperation(value = "新增或更新资源", notes = "新增或更新资源,主要是方便给不支持PUT的Form调用")
    @RequestMapping(value = "/sys/resources/save", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysResourceResult> save(SysResourceForm form) {
        return sysResourceService.save(form);
    }

    @ApiOperation(value = "新增资源", notes = "新增资源")
    @RequestMapping(value = "/sys/resources", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysResourceResult> add(SysResourceForm form) {
        return sysResourceService.add(form);
    }

    @ApiOperation(value = "更新资源", notes = "更新资源")
    @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "Long")
    @RequestMapping(value = "/sys/resources/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public RespResult<SysResourceResult> update(@PathVariable("id") Long id, SysResourceForm form) {
        return sysResourceService.update(id,form);
    }

    @ApiOperation(value = "删除资源", notes = "删除资源")
    @ApiImplicitParam(name = "ids", value = "例如：1 或者 1,2,3", required = true, dataType = "String")
    @RequestMapping(value = "/sys/resources/delete", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysResourceResult> delete(String ids) {
        return sysResourceService.deleteByIds(ids);
    }

    @ApiOperation(value = "获取资源列表", notes = "分页获取列表默认第1页10条数据")
    @RequestMapping(value = "/sys/resources", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<PageResponse<SysResourceResult>> list(PageRequest pageRequest, SysResourceForm form) {
        return sysResourceService.listPage(pageRequest,form);
    }

    @ApiOperation(value = "获取资源", notes = "获取资源")
    @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "Long")
    @RequestMapping(value = "/sys/resources/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<SysResourceResult> get(@PathVariable("id") Long id) {
        return sysResourceService.get(id);
    }

    @ApiOperation(value = "获取资源树", notes = "获取资源树型结构")
    @RequestMapping(value = "/sys/resources/tree", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<List<Tree>> tree(SysResourceForm form) {
        return sysResourceService.listTree(form);
    }
}
