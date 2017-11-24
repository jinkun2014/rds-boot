package me.jinkun.rds.sys.convert;

import me.jinkun.rds.sys.domain.SysResource;
import me.jinkun.rds.sys.web.form.SysResourceForm;
import me.jinkun.rds.sys.web.result.SysResourceResult;
import me.jinkun.rds.core.base.BaseConvert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysResource转换类！ <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class SysResourceConvert extends BaseConvert {

    public static SysResource formToEntity(SysResourceForm form) {
        if (form != null) {
            SysResource entity = new SysResource();
            entity.setId(form.getId());
            entity.setName(form.getName());
            entity.setUrl(form.getUrl());
            entity.setOpenMode(form.getOpenMode());
            entity.setDescription(form.getDescription());
            entity.setIcon(form.getIcon());
            entity.setPid(form.getPid());
            entity.setSeq(form.getSeq());
            entity.setStatus(form.getStatus());
            entity.setResourceType(form.getResourceType());
            entity.setIsLeaf(form.getIsLeaf());
            entity.setDelFlag(form.getDelFlag());
            entity.setUpdateTime(stringToDate(form.getUpdateTime()));
            entity.setCreateTime(stringToDate(form.getCreateTime()));
            return entity;
        }
        return null;
    }

    public static List<SysResource> formListToEntityList(List<SysResourceForm> formList) {
        List<SysResource> entityList = new ArrayList<SysResource>();
        if (formList != null && formList.size() > 0) {
            for (SysResourceForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysResourceResult entityToResult(SysResource entity) {
        if (entity != null) {
            SysResourceResult result = new SysResourceResult();
            result.setId(entity.getId());
            result.setName(entity.getName());
            result.setUrl(entity.getUrl());
            result.setOpenMode(entity.getOpenMode());
            result.setDescription(entity.getDescription());
            result.setIcon(entity.getIcon());
            result.setPid(entity.getPid());
            result.setSeq(entity.getSeq());
            result.setStatus(entity.getStatus());
            result.setResourceType(entity.getResourceType());
            result.setIsLeaf(entity.getIsLeaf());
            result.setDelFlag(entity.getDelFlag());
            result.setUpdateTime(dateToString(entity.getUpdateTime()));
            result.setCreateTime(dateToString(entity.getCreateTime()));
            return result;
        }
        return null;
    }

    public static List<SysResourceResult> entityListToResultList(List<SysResource> entityList) {
        List<SysResourceResult> resultList = new ArrayList<SysResourceResult>();
        if (entityList != null && entityList.size() > 0) {
            for (SysResource entity : entityList) {
                resultList.add(entityToResult(entity));
            }
            return resultList;
        }
        return resultList;
    }
}
