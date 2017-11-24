package me.jinkun.rds.sys.convert;

import me.jinkun.rds.sys.domain.SysRoleResource;
import me.jinkun.rds.sys.web.form.SysRoleResourceForm;
import me.jinkun.rds.sys.web.result.SysRoleResourceResult;
import me.jinkun.rds.core.base.BaseConvert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysRoleResource转换类！ <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysRoleResourceConvert extends BaseConvert {

    public static SysRoleResource formToEntity(SysRoleResourceForm form) {
        if (form != null) {
            SysRoleResource entity = new SysRoleResource();
            entity.setId(form.getId());
            entity.setRoleId(form.getRoleId());
            entity.setResourceId(form.getResourceId());
            return entity;
        }
        return null;
    }

    public static List<SysRoleResource> formListToEntityList(List<SysRoleResourceForm> formList) {
        List<SysRoleResource> entityList = new ArrayList<SysRoleResource>();
        if (formList != null && formList.size() > 0) {
            for (SysRoleResourceForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysRoleResourceResult entityToResult(SysRoleResource entity) {
        if (entity != null) {
            SysRoleResourceResult result = new SysRoleResourceResult();
            result.setId(entity.getId());
            result.setRoleId(entity.getRoleId());
            result.setResourceId(entity.getResourceId());
            return result;
        }
        return null;
    }

    public static List<SysRoleResourceResult> entityListToResultList(List<SysRoleResource> entityList) {
        List<SysRoleResourceResult> resultList = new ArrayList<SysRoleResourceResult>();
        if (entityList != null && entityList.size() > 0) {
            for (SysRoleResource entity : entityList) {
                resultList.add(entityToResult(entity));
            }
            return resultList;
        }
        return resultList;
    }
}
