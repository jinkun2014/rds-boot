package me.jinkun.rds.core.page;

/**
 * 页码
 *   比较复杂
 * @author liangzhongqiu789@sina.com
 * @date 2017-05-26
 * @time 16:02
 */
public interface IComplexPage extends IPage {

    boolean hasPrevious();//是否有前一页

    Integer getPreviousPage();//前一页页码

    boolean hasNext();//是否有下一页

    Integer getNextPage();//下一页页码

    Integer getTotalPage();//总页码

}
