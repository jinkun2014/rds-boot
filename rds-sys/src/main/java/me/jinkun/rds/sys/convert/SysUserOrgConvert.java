package me.jinkun.rds.sys.convert;

import me.jinkun.rds.sys.domain.SysUserOrg;
import me.jinkun.rds.sys.web.form.SysUserOrgForm;
import me.jinkun.rds.sys.web.result.SysUserOrgResult;
import me.jinkun.rds.core.base.BaseConvert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysUserOrg转换类！ <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysUserOrgConvert extends BaseConvert {

    public static SysUserOrg formToEntity(SysUserOrgForm form) {
        if (form != null) {
            SysUserOrg entity = new SysUserOrg();
            entity.setId(form.getId());
            entity.setUserId(form.getUserId());
            entity.setOrgId(form.getOrgId());
            return entity;
        }
        return null;
    }

    public static List<SysUserOrg> formListToEntityList(List<SysUserOrgForm> formList) {
        List<SysUserOrg> entityList = new ArrayList<SysUserOrg>();
        if (formList != null && formList.size() > 0) {
            for (SysUserOrgForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysUserOrgResult entityToResult(SysUserOrg entity) {
        if (entity != null) {
            SysUserOrgResult result = new SysUserOrgResult();
            result.setId(entity.getId());
            result.setUserId(entity.getUserId());
            result.setOrgId(entity.getOrgId());
            return result;
        }
        return null;
    }

    public static List<SysUserOrgResult> entityListToResultList(List<SysUserOrg> entityList) {
        List<SysUserOrgResult> resultList = new ArrayList<SysUserOrgResult>();
        if (entityList != null && entityList.size() > 0) {
            for (SysUserOrg entity : entityList) {
                resultList.add(entityToResult(entity));
            }
            return resultList;
        }
        return resultList;
    }
}
