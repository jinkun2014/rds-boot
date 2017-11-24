package me.jinkun.rds.core.poi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: Excel <br/>
 * Autor: Created by Administrator on 2017/5/31.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelAttribute {
    /**
     * 导出到Excel中列的名字.
     */
    public abstract String columnName();

    /**
     * 导出到Excel中列号.A B C ..
     */
    public abstract String column();

    /**
     * 导出到属性的值组成的key.例如：{"0","1"}
     */
    public abstract String[] key() default {};

    /**
     * 导出到属性的值转化的value.例如：{"启用","停用"}
     */
    public abstract String[] value() default {};

    /**
     * 是否导出数据,应对需求:有时我们需要导出一份模板,这是标题需要但内容需要用户手工填写.
     */
    public abstract boolean isExport() default true;
}
