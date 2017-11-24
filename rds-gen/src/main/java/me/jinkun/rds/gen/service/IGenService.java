package me.jinkun.rds.gen.service;

import me.jinkun.rds.core.base.EUDataGridResult;
import me.jinkun.rds.engine.app.info.EntityInfo;
import me.jinkun.rds.engine.app.info.FieldInfo;
import me.jinkun.rds.engine.app.info.PackageInfo;
import me.jinkun.rds.gen.web.form.GenForm;

import java.util.List;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by Administrator on 2017/5/9.
 */
public interface IGenService {
    EUDataGridResult listTables(GenForm form);

    Object listColumns(GenForm form, String tableName);

    Object gen(GenForm form, EntityInfo entityInfo, List<FieldInfo> fieldInfoList, PackageInfo packageInfo);
}
