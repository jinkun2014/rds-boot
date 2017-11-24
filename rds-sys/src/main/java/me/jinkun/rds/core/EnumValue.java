package me.jinkun.rds.core;

import java.io.Serializable;

/**
 * 契约
 * 枚举值
 *
 * @author liangzhongqiu789@sina.com
 * @date 2017-05-22
 * @time 17:33
 */
public interface EnumValue<T extends Serializable> {

    T getValue();

}
