package me.jinkun.rds.core.exception;

import me.jinkun.rds.core.EnumValue;

/**
 * @author JinKun
 * @date 2017-11-28
 * @time 11:21
 */
public abstract class BaseException extends RuntimeException {

    public abstract EnumValue getEnumValue();

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
