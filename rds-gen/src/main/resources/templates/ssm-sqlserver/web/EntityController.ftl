package ${EntityInfo.packageInfo.web};

import cn.com.header.core.util.Page;
import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName};
import ${EntityInfo.packageInfo.service}.${EntityInfo.entityName}Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * ${EntityInfo.remarks}-控制器
 * @author JinKun
 * @date ${EntityInfo.createTime}
 * @time ${EntityInfo.time}
 */
@Controller
@RequestMapping("${EntityInfo.tableName ? replace("_","/")}")
public class ${EntityInfo.entityName}Controller {

    @Resource
    ${EntityInfo.entityName}Service ${EntityInfo.entityName ? uncap_first }Service;

<#if EntityInfo.primaryKey??>
    @RequestMapping(value = "list")
    public String list(HttpServletRequest request, HttpServletResponse response) {
        return "${EntityInfo.tableName ? replace("_","/")}/list";
    }

    @RequestMapping(value = "context")
    public String context(@RequestParam(defaultValue = "1") Integer pageNo, HttpServletRequest request) {
        Page page = new Page(pageNo);
        ${EntityInfo.entityName ? uncap_first }Service.query(page);
        request.setAttribute("page", page);
        request.getSession().setAttribute("${EntityInfo.entityName ? uncap_first}Page", page);
        return "${EntityInfo.tableName ? replace("_","/")}/context";
    }

    @RequestMapping(value = "edit")
    public String edit(${EntityInfo.primaryKey.type} id, HttpServletRequest request) {
        ${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first} = new ${EntityInfo.entityName}();
        if (id != null) {
            ${EntityInfo.entityName ? uncap_first} = ${EntityInfo.entityName ? uncap_first}Service.get(id);
        }
        request.setAttribute("vo", ${EntityInfo.entityName ? uncap_first});
        return "${EntityInfo.tableName ? replace("_","/")}/edit";
    }

    @RequestMapping(value = "save")
    public String save(${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first}, HttpServletRequest request) {
        ${EntityInfo.entityName ? uncap_first}Service.save(${EntityInfo.entityName ? uncap_first});
        return "redirect:list.do";
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public Object delete(${EntityInfo.primaryKey.type} id) {
        Map<String,Object> map = new HashMap<>();

        try {
            ${EntityInfo.entityName ? uncap_first}Service.delete(id);
            map.put("flag", 1);
        } catch (Exception e) {
            map.put("flag", 0);
        }
        return map;
    }

    @RequestMapping(value = "deleteAll")
    @ResponseBody
    public Object deleteAll(${EntityInfo.primaryKey.type}[] ids) {
        Map<String,Object> map = new HashMap<>();

        try {
            ${EntityInfo.entityName ? uncap_first}Service.deleteAll(ids);
            map.put("flag", 1);
        } catch (Exception e) {
            map.put("flag", 0);
        }
        return map;
    }
</#if>

}
