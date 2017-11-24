package ${EntityInfo.packageInfo.web};

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
@Controller
public class ${EntityInfo.entityName}Controller {

    @Autowired
    ${EntityInfo.entityName}Service ${EntityInfo.entityName ? uncap_first }Service;

<#if EntityInfo.primaryKey??>
    @RequestMapping(value = "/${EntityInfo.packageInfo.module}/${(EntityInfo.tableName?substring((EntityInfo.tableName)?index_of("_")+1)) ? replace("_","/")}s/ui/{ui}.html", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "${EntityInfo.packageInfo.module}/${EntityInfo.tableName ? replace("_","-")}/${EntityInfo.tableName ? replace("_","-")}-" + ui;
    }

    @RequestMapping(value = "/${EntityInfo.packageInfo.module}/${(EntityInfo.tableName?substring((EntityInfo.tableName)?index_of("_")+1)) ? replace("_","/")}s/save", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<${EntityInfo.entityName}Result> save(${EntityInfo.entityName}Form form) {
        return ${EntityInfo.entityName ? uncap_first }Service.save(form);
    }

    @RequestMapping(value = "/${EntityInfo.packageInfo.module}/${(EntityInfo.tableName?substring((EntityInfo.tableName)?index_of("_")+1)) ? replace("_","/")}s", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<${EntityInfo.entityName}Result> add(${EntityInfo.entityName}Form form) {
        return ${EntityInfo.entityName ? uncap_first }Service.add(form);
    }

    @RequestMapping(value = "/${EntityInfo.packageInfo.module}/${(EntityInfo.tableName?substring((EntityInfo.tableName)?index_of("_")+1)) ? replace("_","/")}s/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public RespResult<${EntityInfo.entityName}Result> update(@PathVariable("id") ${EntityInfo.primaryKey.type} id, ${EntityInfo.entityName}Form form) {
        return ${EntityInfo.entityName ? uncap_first }Service.update(id,form);
    }

    @RequestMapping(value = "/${EntityInfo.packageInfo.module}/${(EntityInfo.tableName?substring((EntityInfo.tableName)?index_of("_")+1)) ? replace("_","/")}s/delete", method = RequestMethod.POST)
    @ResponseBody
    public RespResult<${EntityInfo.entityName}Result> delete(String ids) {
        return ${EntityInfo.entityName ? uncap_first }Service.deleteByIds(ids);
    }

    @RequestMapping(value = "/${EntityInfo.packageInfo.module}/${(EntityInfo.tableName?substring((EntityInfo.tableName)?index_of("_")+1)) ? replace("_","/")}s", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<PageResponse<${EntityInfo.entityName}Result>> list(PageRequest pageRequest,${EntityInfo.entityName}Form form) {
        return ${EntityInfo.entityName ? uncap_first }Service.listPage(pageRequest,form);
    }

    @RequestMapping(value = "/${EntityInfo.packageInfo.module}/${(EntityInfo.tableName?substring((EntityInfo.tableName)?index_of("_")+1)) ? replace("_","/")}s/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<${EntityInfo.entityName}Result> get(@PathVariable("id") ${EntityInfo.primaryKey.type} id) {
        return ${EntityInfo.entityName ? uncap_first }Service.get(id);
    }
</#if>
}
