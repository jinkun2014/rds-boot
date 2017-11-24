package me.jinkun.rds.engine.app.template.impl;

import me.jinkun.rds.engine.app.info.EntityInfo;
import me.jinkun.rds.engine.app.info.FieldInfo;
import me.jinkun.rds.engine.app.info.PackageInfo;
import me.jinkun.rds.engine.app.template.Template;
import me.jinkun.rds.engine.app.utils.UtilFt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by PCuser on 2017/11/3.
 */
public class SsmWjdcTemplate implements Template {

    public SsmWjdcTemplate() {
    }

    @Override
    public void gen(EntityInfo etyInfo, PackageInfo pkgInfo, List<FieldInfo> fieldInfoList) {

        //配置包信息
        String base = pkgInfo.getBase();
        String module = pkgInfo.getModule();
        PackageInfo packageInfo =
                new PackageInfo.Builder()
                        .base(base)
                        .entity(base + "." + module + ".entity")
                        .dao(base + "." + module + ".mapper")
                        .mapper(base + "." + module + ".mapper.xml")
                        .service(base + "." + module + ".service")
                        .serviceImpl(base + "." + module + ".service.impl")
                        .web(base + "." + module + ".controller")
                        .builder();

        //配置实体信息
        EntityInfo entityInfo =
                new EntityInfo.Builder(etyInfo.getTableName(), etyInfo.getRemarks(), etyInfo.getEntityName(), etyInfo.getUser(), fieldInfoList, packageInfo)
                        .daoSuffix("Mapper")
                        .daoPrefix("I")
                        .servicePrefix("I")
                        .builder();

        //文件临时存放目录
        String outDir = getOutDir();


        //模版初始化
        UtilFt ftUtil = new UtilFt();
        //准备数据
        Map<String, Object> root = new HashMap<>();
        root.put("EntityInfo", entityInfo);

        //生成Entity
        ftUtil.generateFile(getTempDir() + "/domain", "Entity.ftl", root, entityInfo.getPackageInfo().getEntity(), outDir, entityInfo.getEntityName() + ".java");

        //生成EntityMapperJava
        ftUtil.generateFile(getTempDir() + "/dao", "EntityMapperJava.ftl", root, entityInfo.getPackageInfo().getDao(), outDir, entityInfo.getDaoName()+".java");

        //生成EntityMapperXml
        ftUtil.generateFile(getTempDir() + "/mapper", "EntityMapperXml.ftl", root, entityInfo.getPackageInfo().getMapper(), outDir, entityInfo.getDaoMapperName() + ".xml");

        //生成EntityService
        ftUtil.generateFile(getTempDir() + "/service", "EntityService.ftl", root, entityInfo.getPackageInfo().getService(), outDir, entityInfo.getServiceName() + ".java");

        //生成EntityServiceImpl
        ftUtil.generateFile(getTempDir() + "/service/impl", "EntityServiceImpl.ftl", root, entityInfo.getPackageInfo().getServiceImpl(), outDir, entityInfo.getServiceImplName() + ".java");

        //生成Controller
        ftUtil.generateFile(getTempDir() + "/web", "EntityController.ftl", root, entityInfo.getPackageInfo().getWeb(), outDir, entityInfo.getControllerName() + ".java");
    }

    @Override
    public String getTempDir() {
        return "/templates/ssm-wjdc";
    }

    @Override
    public String getOutDir() {
        return "D:/temp";
    }
}
