package paging;

import sort.Sorter;

public class Pageble implements IPageble{
    private Integer page;
    private Integer maxPageItem;
    private Integer totalItem;
    private Sorter sorter;

    public Sorter getSorter() {
        return sorter;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getMaxPageItem() {
        return maxPageItem;
    }

    public void setMaxPageItem(Integer maxPageItem) {
        this.maxPageItem = maxPageItem;
    }

    public Integer getTotalPage() {
        return (int) Math.ceil((double) this.getTotalItem() / this.getMaxPageItem());
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    @Override
    public Integer getOffset() {
        return (this.page != null) ? (this.page - 1) * this.maxPageItem : null;
    }
}
