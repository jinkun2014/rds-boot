package me.jinkun.rds.core;

import java.io.Serializable;

/**
 * 枚举接口
 *
 * @author JinKun
 * @date 2017/11/28
 * @time 10:16
 */
public interface EnumValue<T extends Serializable> {

    /**
     * @return 枚举的值
     */
    T getValue();

}
