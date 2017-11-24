package me.jinkun.rds.sys.convert;

import me.jinkun.rds.sys.domain.SysUser;
import me.jinkun.rds.sys.web.form.SysUserForm;
import me.jinkun.rds.sys.web.result.SysUserResult;
import me.jinkun.rds.core.base.BaseConvert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysUser转换类！ <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysUserConvert extends BaseConvert {

    public static SysUser formToEntity(SysUserForm form) {
        if (form != null) {
            SysUser entity = new SysUser();
            entity.setId(form.getId());
            entity.setLoginName(form.getLoginName());
            entity.setName(form.getName());
            entity.setPassword(form.getPassword());
            entity.setSex(form.getSex());
            entity.setAge(form.getAge());
            entity.setPhone(form.getPhone());
            entity.setUserType(form.getUserType());
            entity.setStatus(form.getStatus());
            return entity;
        }
        return null;
    }

    public static List<SysUser> formListToEntityList(List<SysUserForm> formList) {
        List<SysUser> entityList = new ArrayList<SysUser>();
        if (formList != null && formList.size() > 0) {
            for (SysUserForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysUserResult entityToResult(SysUser entity) {
        if (entity != null) {
            SysUserResult result = new SysUserResult();
            result.setId(entity.getId());
            result.setLoginName(entity.getLoginName());
            result.setName(entity.getName());
            result.setPassword(entity.getPassword());
            result.setSex(entity.getSex());
            result.setAge(entity.getAge());
            result.setPhone(entity.getPhone());
            result.setUserType(entity.getUserType());
            result.setStatus(entity.getStatus());
            result.setDelFlag(entity.getDelFlag());
            result.setUpdateTime(dateToString(entity.getUpdateTime()));
            result.setCreateTime(dateToString(entity.getCreateTime()));
            return result;
        }
        return null;
    }

    public static List<SysUserResult> entityListToResultList(List<SysUser> entityList) {
        List<SysUserResult> resultList = new ArrayList<SysUserResult>();
        if (entityList != null && entityList.size() > 0) {
            for (SysUser entity : entityList) {
                resultList.add(entityToResult(entity));
            }
            return resultList;
        }
        return resultList;
    }
}
