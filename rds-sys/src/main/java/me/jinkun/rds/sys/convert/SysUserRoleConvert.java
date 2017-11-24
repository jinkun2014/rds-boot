package me.jinkun.rds.sys.convert;

import me.jinkun.rds.sys.domain.SysUserRole;
import me.jinkun.rds.sys.web.form.SysUserRoleForm;
import me.jinkun.rds.sys.web.result.SysUserRoleResult;
import me.jinkun.rds.core.base.BaseConvert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysUserRole转换类！ <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysUserRoleConvert extends BaseConvert {

    public static SysUserRole formToEntity(SysUserRoleForm form) {
        if (form != null) {
            SysUserRole entity = new SysUserRole();
            entity.setId(form.getId());
            entity.setUserId(form.getUserId());
            entity.setRoleId(form.getRoleId());
            return entity;
        }
        return null;
    }

    public static List<SysUserRole> formListToEntityList(List<SysUserRoleForm> formList) {
        List<SysUserRole> entityList = new ArrayList<SysUserRole>();
        if (formList != null && formList.size() > 0) {
            for (SysUserRoleForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysUserRoleResult entityToResult(SysUserRole entity) {
        if (entity != null) {
            SysUserRoleResult result = new SysUserRoleResult();
            result.setId(entity.getId());
            result.setUserId(entity.getUserId());
            result.setRoleId(entity.getRoleId());
            return result;
        }
        return null;
    }

    public static List<SysUserRoleResult> entityListToResultList(List<SysUserRole> entityList) {
        List<SysUserRoleResult> resultList = new ArrayList<SysUserRoleResult>();
        if (entityList != null && entityList.size() > 0) {
            for (SysUserRole entity : entityList) {
                resultList.add(entityToResult(entity));
            }
            return resultList;
        }
        return resultList;
    }
}
