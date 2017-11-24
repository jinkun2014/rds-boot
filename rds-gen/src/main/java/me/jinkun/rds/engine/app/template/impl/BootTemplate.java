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
public class BootTemplate implements Template {
    private String outDir;
    private Map noUIMap;
    private String module;
    private String basePackage;


    public BootTemplate() {
    }

    public BootTemplate(String outDir, String module, Map noUIMap, String basePackage) {
        this.outDir = outDir;
        this.module = module;
        this.noUIMap = noUIMap;
        this.basePackage = basePackage;
    }

    @Override
    public void gen(EntityInfo entityInfo, PackageInfo packageInfo, List<FieldInfo> fieldInfoList) {
        //配置包信息
        entityInfo.setPackageInfo(new PackageInfo(packageInfo.getBase(), packageInfo.getModule()));

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

        //生成EntityExample
        ftUtil.generateFile(getTempDir() + "/domain", "EntityExample.ftl", root, entityInfo.getPackageInfo().getEntity(), outDir, entityInfo.getEntityName() + "Example.java");

        //生成EntityMapperJava
        ftUtil.generateFile(getTempDir() + "/dao", "EntityMapperJava.ftl", root, entityInfo.getPackageInfo().getDao(), outDir, entityInfo.getEntityName() + "Mapper.java");

        //生成EntityMapperXml
        ftUtil.generateFile(getTempDir() + "/mapper", "EntityMapperXml.ftl", root, entityInfo.getPackageInfo().getMapper(), outDir, entityInfo.getEntityName() + "Mapper.xml");

        //生成EntityService
        ftUtil.generateFile(getTempDir() + "/service", "EntityService.ftl", root, entityInfo.getPackageInfo().getService(), outDir, entityInfo.getEntityName() + "Service.java");

        //生成EntityServiceImpl
        ftUtil.generateFile(getTempDir() + "/service/impl", "EntityServiceImpl.ftl", root, entityInfo.getPackageInfo().getServiceImpl(), outDir, entityInfo.getEntityName() + "ServiceImpl.java");

        //生成Convert
        ftUtil.generateFile(getTempDir() + "/convert", "EntityConvert.ftl", root, entityInfo.getPackageInfo().getConvert(), outDir, entityInfo.getEntityName() + "Convert.java");

        //生成Controller
        ftUtil.generateFile(getTempDir() + "/web", "EntityController.ftl", root, entityInfo.getPackageInfo().getWeb(), outDir, entityInfo.getEntityName() + "Controller.java");

        //生成Form
        ftUtil.generateFile(getTempDir() + "/web/form", "EntityForm.ftl", root, entityInfo.getPackageInfo().getForm(), outDir, entityInfo.getEntityName() + "Form.java");

        //生成Result
        ftUtil.generateFile(getTempDir() + "/web/result", "EntityResult.ftl", root, entityInfo.getPackageInfo().getResult(), outDir, entityInfo.getEntityName() + "Result.java");


        //生成Base
        ftUtil.generateFile(getTempDir() + "/core/base", "BaseConvert.ftl", root, entityInfo.getPackageInfo().getConvertBase(), outDir, "BaseConvert.java");
        ftUtil.generateFile(getTempDir() + "/core/base", "BaseForm.ftl", root, entityInfo.getPackageInfo().getFormBase(), outDir, "BaseForm.java");
        ftUtil.generateFile(getTempDir() + "/core/base", "BaseResult.ftl", root, entityInfo.getPackageInfo().getResultBase(), outDir, "BaseResult.java");

        //生成Utils
        ftUtil.generateFile(getTempDir() + "/core/utils", "UtilFile.ftl", root, entityInfo.getPackageInfo().getUtils(), outDir, "UtilFile.java");

        //生成Page
        ftUtil.generateFile(getTempDir() + "/core/page", "PageRequest.ftl", root, entityInfo.getPackageInfo().getPage(), outDir, "PageRequest.java");
        ftUtil.generateFile(getTempDir() + "/core/page", "PageResponse.ftl", root, entityInfo.getPackageInfo().getPage(), outDir, "PageResponse.java");

        //生成Result
        ftUtil.generateFile(getTempDir() + "/core/resp", "RespResult.ftl", root, entityInfo.getPackageInfo().getResp(), outDir, "RespResult.java");


        if (noUIMap == null || noUIMap.get(entityInfo.getTableName()) == null) {
            //生成JSP
            ftUtil.generateFile(getTempDir() + "/ui", "Entity-Entity-list.ftl", root, "templates." + module + "." + entityInfo.getTableName().replace("_", "-"), outDir, entityInfo.getTableName().replace("_", "-") + "-list.jsp");
            ftUtil.generateFile(getTempDir() + "/ui", "Entity-input.ftl", root, "templates." + module + "." + entityInfo.getTableName().replace("_", "-"), outDir, entityInfo.getTableName().replace("_", "-") + "-input.html");
            ftUtil.generateFile(getTempDir() + "/ui", "Entity-js.ftl", root, "static.js." + module + "." + entityInfo.getTableName().replace("_", "-"), outDir, entityInfo.getTableName().replace("_", "-") + ".js");
        }
    }

    @Override
    public String getTempDir() {
        return "/templates/boot-ssm-mysql";
    }

    @Override
    public String getOutDir() {
        return null;
    }
}
