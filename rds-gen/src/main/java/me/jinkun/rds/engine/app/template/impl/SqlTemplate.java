package me.jinkun.rds.engine.app.template.impl;

import me.jinkun.rds.engine.app.info.EntityInfo;
import me.jinkun.rds.engine.app.info.FieldInfo;
import me.jinkun.rds.engine.app.info.PackageInfo;
import me.jinkun.rds.engine.app.template.Template;
import me.jinkun.rds.engine.app.utils.UtilFt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by PCuser on 2017/11/3.
 */
public class SqlTemplate implements Template {

    public SqlTemplate() {
    }

    @Override
    public void gen(EntityInfo entityInfo, PackageInfo packageInfo, List<FieldInfo> fieldInfoList) {

        //配置包信息
        String base = packageInfo.getBase();
        String module = packageInfo.getModule();
        PackageInfo pkgInfo =
                new PackageInfo.Builder()
                        .base(base)
                        .entity(base + ".model." + module)
                        .dao(base + ".mapper." + module)
                        .mapper(base + ".mapper." + module)
                        .service(base + ".service." + module)
                        .serviceImpl(base + ".service." + module)
                        .web(base + ".controller." + module)
                        .builder();

        //配置实体信息
        List<String> importList = new ArrayList<>();
        FieldInfo primaryField = null;
        for (FieldInfo fieldInfo : fieldInfoList) {
            if (fieldInfo.getType().equals("Date")) {
                importList.add("java.util.Date");
            }

            if (fieldInfo.primaryKey) {
                primaryField = fieldInfo;
            }
        }
        entityInfo.setPackageInfo(pkgInfo);
        entityInfo.setImportList(importList);
        entityInfo.setFieldInfoList(fieldInfoList);
        entityInfo.setPrimaryKey(primaryField);

        //文件临时存放目录
        String outDir = getOutDir();


        //模版初始化
        UtilFt ftUtil = new UtilFt();
        Map<String, Object> root = new HashMap<>();

        //准备数据
        root.put("EntityInfo", entityInfo);

        //生成Entity
        ftUtil.generateFile(getTempDir() + "/domain", "Entity.ftl", root, entityInfo.getPackageInfo().getEntity(), outDir, entityInfo.getEntityName() + ".java");

        //生成EntityMapperJava
        ftUtil.generateFile(getTempDir() + "/dao", "EntityMapperJava.ftl", root, entityInfo.getPackageInfo().getDao(), outDir, entityInfo.getEntityName() + "Dao.java");

        //生成EntityMapperXml
        ftUtil.generateFile(getTempDir() + "/mapper", "EntityMapperXml.ftl", root, entityInfo.getPackageInfo().getMapper(), outDir, entityInfo.getEntityName() + "Dao.xml");

        //生成EntityService
        ftUtil.generateFile(getTempDir() + "/service", "EntityService.ftl", root, entityInfo.getPackageInfo().getService(), outDir, entityInfo.getEntityName() + "Service.java");

        //生成EntityServiceImpl
        ftUtil.generateFile(getTempDir() + "/service/impl", "EntityServiceImpl.ftl", root, entityInfo.getPackageInfo().getServiceImpl(), outDir, entityInfo.getEntityName() + "ServiceImpl.java");

        //生成Controller
        ftUtil.generateFile(getTempDir() + "/web", "EntityController.ftl", root, entityInfo.getPackageInfo().getWeb(), outDir, entityInfo.getEntityName() + "Controller.java");

        //生成页面
        ftUtil.generateFile(getTempDir() + "/ui", "Entity-list.ftl", root, "views." + module + "." + entityInfo.getEntityName().toLowerCase(), outDir, "list.jsp");
        ftUtil.generateFile(getTempDir() + "/ui", "Entity-edit.ftl", root, "views." + module + "." + entityInfo.getEntityName().toLowerCase(), outDir, "edit.jsp");
        ftUtil.generateFile(getTempDir() + "/ui", "Entity-context.ftl", root, "views." + module + "." + entityInfo.getEntityName().toLowerCase(), outDir, "context.jsp");
        ftUtil.generateFile(getTempDir() + "/ui", "Entity-js.ftl", root, "js." + module + "." + entityInfo.getEntityName().toLowerCase(), outDir, "list.js");
    }

    @Override
    public String getTempDir() {
        return "/templates/ssm-sqlserver";
    }

    @Override
    public String getOutDir() {
        return "D:/temp";
    }
}
