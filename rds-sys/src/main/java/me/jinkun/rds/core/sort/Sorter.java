package me.jinkun.rds.core.sort;


/**
 * 排序器
 * @author liangzhongqiu789@sina.com
 * @date 2017-05-26
 * @time 16:52
 */
public class Sorter implements ISort {

    private String field;

    private boolean asc = true;

    public Sorter(){
        super();
    }

    public Sorter(String field){
        this();
        this.field = field;
    }

    public Sorter(String field,boolean asc){
        this(field);
        this.asc = asc;
    }

    @Override
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }
}
