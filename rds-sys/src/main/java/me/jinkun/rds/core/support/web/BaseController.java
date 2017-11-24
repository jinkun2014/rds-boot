package me.jinkun.rds.core.support.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 基本的Controller <br/>
 * Autor: Created by JinKun on 2017/11/22.
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    protected JsonViewData setJsonViewData(ResultCode resultCode) {
        return new JsonViewData(resultCode);
    }

    protected JsonViewData setJsonViewData(ResultCode resultCode, String message) {
        return new JsonViewData(resultCode, message);
    }

    protected JsonViewData setJsonViewData(Object data) {
        return new JsonViewData(data);
    }

}
