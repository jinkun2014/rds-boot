package me.jinkun.rds.core.page;


/**
 * 简单分页
 * @author liangzhongqiu789@sina.com
 * @date 2017-05-26
 * @time 16:07
 */
public class SimplePage implements IPage {

    protected Integer pageNo;//页码

    protected Integer pageSize;//页容量，每页显示多少条

    protected int totalRecord;//总记录行数

    public SimplePage(){super();}

    public SimplePage(int pageNo,int pageSize){
        this();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    @Override
    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getTotalRecord() {
        return totalRecord;
    }

    @Override
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
