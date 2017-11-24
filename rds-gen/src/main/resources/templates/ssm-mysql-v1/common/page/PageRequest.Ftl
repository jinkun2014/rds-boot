package ${EntityInfo.packageInfo.page};

/**
 * @Description: 分页请求信息！ <br/>
 * @Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
 */
public class PageRequest {
    protected Long page = 1L;
    protected Long size = 10L;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
