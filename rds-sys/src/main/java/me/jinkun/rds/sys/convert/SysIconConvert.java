package me.jinkun.rds.sys.convert;

import me.jinkun.rds.sys.domain.SysIcon;
import me.jinkun.rds.sys.web.form.SysIconForm;
import me.jinkun.rds.sys.web.result.SysIconResult;
import me.jinkun.rds.core.base.BaseConvert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysIcon转换类！ <br/>
 * @Autor: Created by Jin Kun on 2017-06-01.
 */
public class SysIconConvert extends BaseConvert {

    public static SysIcon formToEntity(SysIconForm form) {
        if (form != null) {
            SysIcon entity = new SysIcon();
            entity.setId(form.getId());
            entity.setName(form.getName());
            entity.setUrl(form.getUrl());
            entity.setType(form.getType());
            return entity;
        }
        return null;
    }

    public static List<SysIcon> formListToEntityList(List<SysIconForm> formList) {
        List<SysIcon> entityList = new ArrayList<SysIcon>();
        if (formList != null && formList.size() > 0) {
            for (SysIconForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysIconResult entityToResult(SysIcon entity) {
        if (entity != null) {
            SysIconResult result = new SysIconResult();
            result.setId(entity.getId());
            result.setName(entity.getName());
            result.setUrl(entity.getUrl());
            result.setType(entity.getType());
            return result;
        }
        return null;
    }

    public static List<SysIconResult> entityListToResultList(List<SysIcon> entityList) {
        List<SysIconResult> resultList = new ArrayList<SysIconResult>();
        if (entityList != null && entityList.size() > 0) {
            for (SysIcon entity : entityList) {
                resultList.add(entityToResult(entity));
            }
            return resultList;
        }
        return resultList;
    }
}
