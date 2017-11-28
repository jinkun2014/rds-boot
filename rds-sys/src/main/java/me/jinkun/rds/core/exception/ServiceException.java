package me.jinkun.rds.core.exception;

import me.jinkun.rds.core.EnumValue;
import me.jinkun.rds.core.support.web.ResultCode;

/**
 * @author JinKun
 * @date 2017-11-28
 * @time 11:22
 */
public class ServiceException extends BaseException {
    private ResultCode resultCode = ResultCode.SYSTEM_ERROR;

    @Override
    public EnumValue getEnumValue() {
        return this.resultCode;
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, ResultCode resultCode) {
        super(message);
        this.resultCode = resultCode;
    }

    public ServiceException(String message, Throwable cause, ResultCode resultCode) {
        super(message, cause);
        this.resultCode = resultCode;
    }
}
