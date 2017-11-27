package me.jinkun.rds.core.support.web;


import me.jinkun.rds.core.EnumValue;

/**
 * @author liangzhongqiu789@sina.com
 * @date 2017-05-22
 * @time 16:53
 */
public enum  ResultCode implements EnumValue<Integer> {

    FAIL(0,"操作失败！"),

    SUCCESS(1,"操作成功！"),

    NO_LOGIN(2,"未登陆或登陆已超时！"),

    NO_EXISTS(3,"数据不存在！"),

    PARAM_ERROR(4,"参数错误！"),

    DUPLICATION(5,"数据重复！"),

    NO_AUTH(6,"无权限！"),

    SYSTEM_ERROR(7,"系统出错！");

    private int value;
    private String description;

    ResultCode(int value,String description){
        this.value = value;
        this.description = description;
    }

    @Override
    public Integer getValue(){
        return value;
    }

    public String getDescription(){
        return description;
    }

}
