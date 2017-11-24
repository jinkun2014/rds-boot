package me.jinkun.rds.engine.app.template;

import me.jinkun.rds.engine.app.info.EntityInfo;
import me.jinkun.rds.engine.app.info.FieldInfo;
import me.jinkun.rds.engine.app.info.PackageInfo;

import java.util.List;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by PCuser on 2017/11/3.
 */
public interface Template {
    void gen(EntityInfo entityInfo, PackageInfo packageInfo, List<FieldInfo> fieldInfoList);

    String getTempDir();

    String getOutDir();
}
