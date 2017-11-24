package me.jinkun.rds.sys.convert;

import me.jinkun.rds.sys.domain.SysLog;
import me.jinkun.rds.sys.web.form.SysLogForm;
import me.jinkun.rds.sys.web.result.SysLogResult;
import me.jinkun.rds.core.base.BaseConvert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysLog转换类！ <br/>
 * @Autor: Created by Jin Kun on 2017-06-01.
 */
public class SysLogConvert extends BaseConvert {

    public static SysLog formToEntity(SysLogForm form) {
        if (form != null) {
            SysLog entity = new SysLog();
            entity.setId(form.getId());
            entity.setTime(stringToDate(form.getTime()));
            entity.setIp(form.getIp());
            entity.setLoginName(form.getLoginName());
            entity.setUrl(form.getUrl());
            entity.setClazz(form.getClazz());
            entity.setMethod(form.getMethod());
            entity.setMoudle(form.getMoudle());
            entity.setFunction(form.getFunction());
            return entity;
        }
        return null;
    }

    public static List<SysLog> formListToEntityList(List<SysLogForm> formList) {
        List<SysLog> entityList = new ArrayList<SysLog>();
        if (formList != null && formList.size() > 0) {
            for (SysLogForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysLogResult entityToResult(SysLog entity) {
        if (entity != null) {
            SysLogResult result = new SysLogResult();
            result.setId(entity.getId());
            result.setTime(dateToString(entity.getTime()));
            result.setIp(entity.getIp());
            result.setLoginName(entity.getLoginName());
            result.setUrl(entity.getUrl());
            result.setClazz(entity.getClazz());
            result.setMethod(entity.getMethod());
            result.setMoudle(entity.getMoudle());
            result.setFunction(entity.getFunction());
            return result;
        }
        return null;
    }

    public static List<SysLogResult> entityListToResultList(List<SysLog> entityList) {
        List<SysLogResult> resultList = new ArrayList<SysLogResult>();
        if (entityList != null && entityList.size() > 0) {
            for (SysLog entity : entityList) {
                resultList.add(entityToResult(entity));
            }
            return resultList;
        }
        return resultList;
    }
}
