package me.jinkun.rds.core.support.exception;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by Administrator on 2017/11/22.
 */
public abstract class BaseException extends RuntimeException {
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
