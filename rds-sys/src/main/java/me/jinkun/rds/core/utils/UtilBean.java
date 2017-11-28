package me.jinkun.rds.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * Bean工具
 *
 * @author Liangzhongqiu
 * @date 2017/6/4 004
 * @time 13:52
 */
public class UtilBean {

    private UtilBean() {
    }

    /**
     * 拷贝值非null属性到目标
     *
     * @param source           源对象
     * @param target           目标对象
     * @param ignoreProperties 待忽略的属性
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target, String... ignoreProperties) {
        try {
            copyProperties(source, target, true, ignoreProperties);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("拷贝属性出错");
        }
    }

    public static void copyProperties(Object source, Object target, boolean ignoreNull, String... ignoreProperties)
            throws BeansException, IntrospectionException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        PropertyDescriptor[] targetPds = Introspector.getBeanInfo(target.getClass()).getPropertyDescriptors();
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
        PropertyDescriptor[] sourcePds = Introspector.getBeanInfo(source.getClass()).getPropertyDescriptors();

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                for (PropertyDescriptor sourcePd : sourcePds) {
                    if (sourcePd != null && sourcePd.getName().equals(targetPd.getName())) {
                        Method readMethod = sourcePd.getReadMethod();
                        if (readMethod != null &&
                                ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                            try {
                                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                    readMethod.setAccessible(true);
                                }
                                Object value = readMethod.invoke(source);
                                if (!ignoreNull || value != null) {
                                    if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                        writeMethod.setAccessible(true);
                                    }
                                    writeMethod.invoke(target, value);
                                }

                            } catch (Throwable ex) {
                                throw new FatalBeanException(
                                        "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                            }
                        }
                    }
                }
            }
        }
    }
}
