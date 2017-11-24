package me.jinkun.rds.sys.convert;

import me.jinkun.rds.sys.domain.SysOrg;
import me.jinkun.rds.sys.web.form.SysOrgForm;
import me.jinkun.rds.sys.web.result.SysOrgResult;
import me.jinkun.rds.core.base.BaseConvert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysOrg转换类！ <br/>
 * @Autor: Created by Jin Kun on 2017-05-24.
 */
public class SysOrgConvert extends BaseConvert {

    public static SysOrg formToEntity(SysOrgForm form) {
        if (form != null) {
            SysOrg entity = new SysOrg();
            entity.setId(form.getId());
            entity.setName(form.getName());
            entity.setAddress(form.getAddress());
            entity.setCode(form.getCode());
            entity.setIcon(form.getIcon());
            entity.setPid(form.getPid());
            entity.setIsLeaf(form.getIsLeaf());
            entity.setSeq(form.getSeq());
            entity.setDelFlag(form.getDelFlag());
            entity.setUpdateTime(stringToDate(form.getUpdateTime()));
            entity.setCreateTime(stringToDate(form.getCreateTime()));
            return entity;
        }
        return null;
    }

    public static List<SysOrg> formListToEntityList(List<SysOrgForm> formList) {
        List<SysOrg> entityList = new ArrayList<SysOrg>();
        if (formList != null && formList.size() > 0) {
            for (SysOrgForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysOrgResult entityToResult(SysOrg entity) {
        if (entity != null) {
            SysOrgResult result = new SysOrgResult();
            result.setId(entity.getId());
            result.setName(entity.getName());
            result.setAddress(entity.getAddress());
            result.setCode(entity.getCode());
            result.setIcon(entity.getIcon());
            result.setPid(entity.getPid());
            result.setIsLeaf(entity.getIsLeaf());
            result.setSeq(entity.getSeq());
            result.setDelFlag(entity.getDelFlag());
            result.setUpdateTime(dateToString(entity.getUpdateTime()));
            result.setCreateTime(dateToString(entity.getCreateTime()));
            return result;
        }
        return null;
    }

    public static List<SysOrgResult> entityListToResultList(List<SysOrg> entityList) {
        List<SysOrgResult> resultList = new ArrayList<SysOrgResult>();
        if (entityList != null && entityList.size() > 0) {
            for (SysOrg entity : entityList) {
                resultList.add(entityToResult(entity));
            }
            return resultList;
        }
        return resultList;
    }
}
