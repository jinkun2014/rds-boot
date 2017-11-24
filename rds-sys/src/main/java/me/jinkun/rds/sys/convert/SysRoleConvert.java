package me.jinkun.rds.sys.convert;

import me.jinkun.rds.sys.domain.SysRole;
import me.jinkun.rds.sys.web.form.SysRoleForm;
import me.jinkun.rds.sys.web.result.SysRoleResult;
import me.jinkun.rds.core.base.BaseConvert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysRole转换类！ <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysRoleConvert extends BaseConvert {

    public static SysRole formToEntity(SysRoleForm form) {
        if (form != null) {
            SysRole entity = new SysRole();
            entity.setId(form.getId());
            entity.setName(form.getName());
            entity.setSeq(form.getSeq());
            entity.setDescription(form.getDescription());
            entity.setStatus(form.getStatus());
            entity.setDelFlag(form.getDelFlag());
            entity.setUpdateTime(stringToDate(form.getUpdateTime()));
            entity.setCreateTime(stringToDate(form.getCreateTime()));
            return entity;
        }
        return null;
    }

    public static List<SysRole> formListToEntityList(List<SysRoleForm> formList) {
        List<SysRole> entityList = new ArrayList<SysRole>();
        if (formList != null && formList.size() > 0) {
            for (SysRoleForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysRoleResult entityToResult(SysRole entity) {
        if (entity != null) {
            SysRoleResult result = new SysRoleResult();
            result.setId(entity.getId());
            result.setName(entity.getName());
            result.setSeq(entity.getSeq());
            result.setDescription(entity.getDescription());
            result.setStatus(entity.getStatus());
            result.setDelFlag(entity.getDelFlag());
            result.setUpdateTime(dateToString(entity.getUpdateTime()));
            result.setCreateTime(dateToString(entity.getCreateTime()));
            return result;
        }
        return null;
    }

    public static List<SysRoleResult> entityListToResultList(List<SysRole> entityList) {
        List<SysRoleResult> resultList = new ArrayList<SysRoleResult>();
        if (entityList != null && entityList.size() > 0) {
            for (SysRole entity : entityList) {
                resultList.add(entityToResult(entity));
            }
            return resultList;
        }
        return resultList;
    }
}
