package me.jinkun.rds.core.page;

import java.util.ArrayList;
import java.util.List;
/**
 * @Description: 分页查询结果！ <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class PageResponse<T> {
    private Long pageNo = 1L;
    private Long pageSize = 10L;

    private Long total;
    private List<T> result = new ArrayList<>();

    public PageResponse() {
    }

    public PageResponse(Long pageNo, Long pageSize, Long total, List<T> result) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.result = result;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}