package me.jinkun.rds.sys.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.sys.service.SysLogService;
import me.jinkun.rds.sys.web.form.SysLogForm;
import me.jinkun.rds.sys.web.result.SysLogResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Description: 日志, 数据库表为： sys_log<br/>
 * @Autor: Created by Jin Kun on 2017-05-27.
 */
@Api(description = "日志")
@Controller
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

    @ApiOperation(value = "页面跳转", notes = "页面跳转:例如sys-log-{ui}.html")
    @RequestMapping(value = "/sys/logs/ui/{ui}.html", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "sys/sys-log/sys-log-" + ui;
    }

    @ApiOperation(value = "新增或更新日志", notes = "新增或更新日志,主要是方便给不支持PUT的Form调用")
    @RequestMapping(value = "/sys/logs/save", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysLogResult> save(SysLogForm form) {
        return sysLogService.save(form);
    }

    @ApiOperation(value = "新增日志", notes = "新增日志")
    @RequestMapping(value = "/sys/logs", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysLogResult> add(SysLogForm form) {
        return sysLogService.add(form);
    }

    @ApiOperation(value = "更新日志", notes = "更新日志")
    @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "Long")
    @RequestMapping(value = "/sys/logs/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public RespResult<SysLogResult> update(@PathVariable("id") Long id, SysLogForm form) {
        return sysLogService.update(id, form);
    }

    @ApiOperation(value = "删除日志", notes = "删除日志")
    @ApiImplicitParam(name = "ids", value = "例如：1 或者 1,2,3", required = true, dataType = "String")
    @RequestMapping(value = "/sys/logs/delete", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<SysLogResult> delete(String ids) {
        return sysLogService.deleteByIds(ids);
    }

    @ApiOperation(value = "获取日志列表", notes = "分页获取列表默认第1页10条数据")
    @RequestMapping(value = "/sys/logs", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<PageResponse<SysLogResult>> list(PageRequest pageRequest, SysLogForm form) {
        return sysLogService.listPage(pageRequest, form);
    }

    @ApiOperation(value = "获取日志", notes = "获取日志")
    @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "Long")
    @RequestMapping(value = "/sys/logs/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<SysLogResult> get(@PathVariable("id") Long id) {
        return sysLogService.get(id);
    }

    @ApiOperation(value = "导出日志", notes = "导出日志")
    @ApiImplicitParam(name = "ids", value = "例如：1 或者 1,2,3", required = true, dataType = "String")
    @RequestMapping(value = "/sys/logs/exportXls", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<String> exportXls(String ids) {
        return sysLogService.exportXls(ids);
    }

    @ApiOperation(value = "导入日志", notes = "导入日志")
    @RequestMapping(value = "/sys/logs/importXls", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<String> importXls(@RequestParam("file") MultipartFile file) {
        return sysLogService.importXls(file);
    }

    @ApiOperation(value = "PV 统计", notes = "PV 统计")
    @RequestMapping(value = "/sys/logs/pv", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<Map<String, Object>> pv() {
        return sysLogService.pv();
    }
}
