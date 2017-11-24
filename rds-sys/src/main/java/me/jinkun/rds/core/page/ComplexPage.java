package me.jinkun.rds.core.page;


/**
 * @author liangzhongqiu789@sina.com
 * @date 2017-05-26
 * @time 16:16
 */
public class ComplexPage extends SimplePage implements IComplexPage {

    private Integer previousPage;//上一页

    private Integer nextPage;//下一页

    private Integer totalPage;//总页数

    boolean previous;//是否存在上一页

    boolean next;//是否存在下一页

    public ComplexPage(int pageNo,int pageSize,int totalRecord){
        super(pageNo,pageSize);
        this.totalRecord = totalRecord;
        if(pageNo <= 0){
            throw new IllegalArgumentException("The pageNo should greater than zero,while given pageNo is "+pageNo);
        }
        if(pageSize < 0){
            throw new IllegalArgumentException("The pageSize should greater than zero,while given pageSize is "+pageSize);
        }
        int reminder = totalRecord % pageSize;
        int trade = totalRecord / pageSize;
        totalPage = trade + (reminder == 0 ? 0 : 1);
        if(pageNo < totalPage){
            next = true;
            nextPage = pageNo + 1;
        }
        if(pageNo > 1){
            previous = true;
            previousPage = pageNo-1;
        }
    }

    public ComplexPage(IPage paging){
        this(paging.getPageNo(),paging.getPageSize(),paging.getTotalRecord());
    }

    @Override
    public boolean hasPrevious() {
        return previous;
    }

    public Boolean getPrevious(){
        return hasPrevious();
    }

    @Override
    public Integer getPreviousPage() {
        return previousPage;
    }

    @Override
    public boolean hasNext() {
        return next;
    }

    public Boolean getNext(){
        return hasNext();
    }

    @Override
    public Integer getNextPage() {
        return nextPage;
    }

    @Override
    public Integer getTotalPage() {
        return totalPage;
    }
}
