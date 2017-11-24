package me.jinkun.rds.gen.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.jinkun.rds.core.DBConstant;
import me.jinkun.rds.core.utils.UtilDate;
import me.jinkun.rds.engine.app.info.EntityInfo;
import me.jinkun.rds.engine.app.info.FieldInfo;
import me.jinkun.rds.gen.service.IGenService;
import me.jinkun.rds.gen.web.form.GenForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by Administrator on 2017/5/9.
 */
@Controller
public class GenController {

    @Autowired
    IGenService genService;

    @RequestMapping(value = "/gen/tables", method = RequestMethod.POST)
    @ResponseBody
    public Object tables(GenForm form) {
        form.setDriver(DBConstant.getDriver(form.getDialect()));
        return genService.listTables(form);
    }

    @RequestMapping(value = "/gen/{table}/columns", method = RequestMethod.POST)
    @ResponseBody
    public Object columns(GenForm form, @PathVariable("table") String table) {
        form.setDriver(DBConstant.getDriver(form.getDialect()));
        return genService.listColumns(form, table);
    }

    @RequestMapping(value = "/gen/zip", method = RequestMethod.POST)
    @ResponseBody
    public Object gen(GenForm form, HttpServletRequest request) {
        form.setDriver(DBConstant.getDriver(form.getDialect()));
        form.setRealPath(request.getServletContext().getRealPath("/"));
        form.setDownloadDir("download");

        Gson gson = new Gson();

        //实体
        EntityInfo entityInfo = gson.fromJson(form.getEntityInfo(), EntityInfo.class);
        entityInfo.setUser(form.getUser());
        entityInfo.setCreateTime(UtilDate.formDate(new Date(),UtilDate.yyyy_MM_dd));
        entityInfo.setTime(UtilDate.formDate(new Date(),UtilDate.HH_mm));

        //属性
        List<FieldInfo> fieldInfoList = gson.fromJson(form.getFieldInfo(), new TypeToken<List<FieldInfo>>() {
        }.getType());

        //包名

        return genService.gen(form, entityInfo, fieldInfoList,form.getPackageInfo());
    }
}
