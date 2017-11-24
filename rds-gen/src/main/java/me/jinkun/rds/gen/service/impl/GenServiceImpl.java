package me.jinkun.rds.gen.service.impl;

import me.jinkun.rds.engine.app.template.TemplateFactory;
import me.jinkun.rds.core.base.BaseResult;
import me.jinkun.rds.core.base.EUDataGridResult;
import me.jinkun.rds.core.utils.UtilFile;
import me.jinkun.rds.core.utils.UtilZip;
import me.jinkun.rds.engine.app.EngineMain;
import me.jinkun.rds.engine.app.info.EntityInfo;
import me.jinkun.rds.engine.app.info.FieldInfo;
import me.jinkun.rds.engine.app.info.PackageInfo;
import me.jinkun.rds.engine.app.template.Template;
import me.jinkun.rds.engine.core.DataManager;
import me.jinkun.rds.engine.core.entity.Table;
import me.jinkun.rds.gen.service.IGenService;
import me.jinkun.rds.gen.web.form.GenForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by Administrator on 2017/5/9.
 */
@Service
public class GenServiceImpl implements IGenService {
    @Override
    public EUDataGridResult listTables(GenForm form) {

        DataManager dm = new DataManager.Builder()
                .driver(form.getDriver())
                .url(form.getUrl())
                .username(form.getUsername())
                .password(form.getPassword())
                .builder();

        EngineMain engineMain = new EngineMain.Builder()
                .dataManager(dm)
                .builder();

        List<EntityInfo> entityInfoList = engineMain.getEntityListByName(form.getName());


        List<Table> allTableList = dm.getTableList();
        List<Table> tableList = new ArrayList<>();

        if (form != null && form.getName() != null && !"".equals(form.getName())) {
            //转换为页面需要的数据类型
            List<GenForm> formList = new ArrayList<>();
            for (Table table : allTableList) {
                if (table.getTableName().toUpperCase().contains(form.getName().toUpperCase())) {
                    tableList.add(table);
                }
            }
        } else {
            tableList.addAll(allTableList);
        }

        //总数
        int total = tableList.size();

        //构造分页的数据
        List<Table> tables = null;
        if (form.getPage() * form.getRows() > total) {
            tables = tableList.subList((form.getPage() - 1) * form.getRows(), total);
        } else {
            tables = tableList.subList((form.getPage() - 1) * form.getRows(), form.getPage() * form.getRows());
        }


        //转换为页面需要的数据类型
        List<GenForm> formList = new ArrayList<>();
        for (Table table : tables) {
            if (form != null && form.getName() != null && !"".equals(form.getName())) {
                if (table.getTableName().toUpperCase().contains(form.getName().toUpperCase())) {
                    GenForm genFrom = new GenForm();
                    genFrom.setName(table.getTableName());
                    genFrom.setRemarks(table.getTableRemarks());
                    formList.add(genFrom);
                }
            } else {
                GenForm genFrom = new GenForm();
                genFrom.setName(table.getTableName());
                genFrom.setRemarks(table.getTableRemarks());
                formList.add(genFrom);
            }
        }

        return new EUDataGridResult(entityInfoList.size(), entityInfoList);
    }

    @Override
    public Object listColumns(GenForm form, String tableName) {
        DataManager dm = new DataManager.Builder()
                .driver(form.getDriver())
                .url(form.getUrl())
                .username(form.getUsername())
                .password(form.getPassword())
                .builder();

        EngineMain engineMain = new EngineMain.Builder()
                .dataManager(dm)
                .builder();

        List<FieldInfo> fieldInfoList = engineMain.getFieldListByTableName(tableName);
        return new EUDataGridResult(fieldInfoList.size(), fieldInfoList);
    }

    @Override
    public Object gen(GenForm form, EntityInfo entityInfo, List<FieldInfo> fieldInfoList, PackageInfo packageInfo) {


        Template tmp = TemplateFactory.getTemplate(form.getType());

        //创建临时文件夹-用于存放待打包的文件
        String outDir = tmp.getOutDir();
        UtilFile.mkDirs(outDir);

        //构建zip文件
        String zipFileName = new Date().getTime() + ".zip";
        String zipFilePath = form.getRealPath() + form.getDownloadDir();
        UtilFile.mkDirs(zipFilePath);

        //得到连接
        try {
            //处理不同模版类型
            tmp.gen(entityInfo, packageInfo, fieldInfoList);

            //压缩生成的文件夹
            UtilZip.zipMultiFile(outDir, zipFilePath + "/" + zipFileName, true);

            //删除临时文件
            UtilFile.delDirs(outDir);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return BaseResult.ok("生成完成", form.getDownloadDir() + "/" + zipFileName);
    }
}
