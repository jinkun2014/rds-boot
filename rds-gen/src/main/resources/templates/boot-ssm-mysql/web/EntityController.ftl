package ${EntityInfo.packageInfo.web};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import ${EntityInfo.packageInfo.page}.PageRequest;
import ${EntityInfo.packageInfo.page}.PageResponse;
import ${EntityInfo.packageInfo.resp}.RespResult;
import ${EntityInfo.packageInfo.service}.${EntityInfo.entityName}Service;
import ${EntityInfo.packageInfo.form}.${EntityInfo.entityName}Form;
import ${EntityInfo.packageInfo.result}.${EntityInfo.entityName}Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @Description: ${EntityInfo.remarks},数据库表为： ${EntityInfo.tableName}<br/>
 * @Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
 */
@Api(description = "${EntityInfo.remarks}")
@Controller
public class ${EntityInfo.entityName}Controller {

    @Autowired
    ${EntityInfo.entityName}Service ${EntityInfo.entityName ? uncap_first }Service;
<#if EntityInfo.primaryKey??>
    @ApiOperation(value = "页面跳转", notes = "页面跳转:例如${EntityInfo.tableName ? replace("_","-")}-{ui}.html")
    @RequestMapping(value = "/${EntityInfo.tableName ? replace("_","/")}s/ui/{ui}.html", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "${EntityInfo.packageInfo.module}/${EntityInfo.tableName ? replace("_","-")}/${EntityInfo.tableName ? replace("_","-")}-" + ui;
    }

    @ApiOperation(value = "新增或更新${EntityInfo.remarks}", notes = "新增或更新${EntityInfo.remarks},主要是方便给不支持PUT的Form调用")
    @RequestMapping(value = "/${EntityInfo.tableName ? replace("_","/")}s/save", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<${EntityInfo.entityName}Result> save(${EntityInfo.entityName}Form form) {
        return ${EntityInfo.entityName ? uncap_first }Service.save(form);
    }

    @ApiOperation(value = "新增${EntityInfo.remarks}", notes = "新增${EntityInfo.remarks}")
    @RequestMapping(value = "/${EntityInfo.tableName ? replace("_","/")}s", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<${EntityInfo.entityName}Result> add(${EntityInfo.entityName}Form form) {
        return ${EntityInfo.entityName ? uncap_first }Service.add(form);
    }

    @ApiOperation(value = "更新${EntityInfo.remarks}", notes = "更新${EntityInfo.remarks}")
    @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "${EntityInfo.primaryKey.type}")
    @RequestMapping(value = "/${EntityInfo.tableName ? replace("_","/")}s/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public RespResult<${EntityInfo.entityName}Result> update(@PathVariable("id") ${EntityInfo.primaryKey.type} id, ${EntityInfo.entityName}Form form) {
        return ${EntityInfo.entityName ? uncap_first }Service.update(id,form);
    }

    @ApiOperation(value = "删除${EntityInfo.remarks}", notes = "删除${EntityInfo.remarks}")
    @ApiImplicitParam(name = "ids", value = "例如：1 或者 1,2,3", required = true, dataType = "String")
    @RequestMapping(value = "/${EntityInfo.tableName ? replace("_","/")}s/delete", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<${EntityInfo.entityName}Result> delete(String ids) {
        return ${EntityInfo.entityName ? uncap_first }Service.deleteByIds(ids);
    }

    @ApiOperation(value = "获取${EntityInfo.remarks}列表", notes = "分页获取列表默认第1页10条数据")
    @RequestMapping(value = "/${EntityInfo.tableName ? replace("_","/")}s", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<PageResponse<${EntityInfo.entityName}Result>> list(PageRequest pageRequest,${EntityInfo.entityName}Form form) {
        return ${EntityInfo.entityName ? uncap_first }Service.listPage(pageRequest,form);
    }

    @ApiOperation(value = "获取${EntityInfo.remarks}", notes = "获取${EntityInfo.remarks}")
    @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "${EntityInfo.primaryKey.type}")
    @RequestMapping(value = "/${EntityInfo.tableName ? replace("_","/")}s/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<${EntityInfo.entityName}Result> get(@PathVariable("id") ${EntityInfo.primaryKey.type} id) {
        return ${EntityInfo.entityName ? uncap_first }Service.get(id);
    }
</#if>
}
