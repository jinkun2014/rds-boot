package me.jinkun.rds.core.page;

/**
 * 页码接口
 * @author liangzhongqiu789@sina.com
 * @date 2017-05-26
 * @time 15:54
 */
public interface IPage {

    Integer getPageNo();//页码

    Integer getPageSize();//页容量

    int getTotalRecord();//总计数

    void setTotalRecord(int totalRecord);

}
