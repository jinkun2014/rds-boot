package me.jinkun.rds.core.support.exception;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by Administrator on 2017/11/23.
 */
public class ServiceException extends BaseException {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
