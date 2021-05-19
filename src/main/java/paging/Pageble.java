package paging;

public class Pageble implements IPageble{
    private Integer page;
    private Integer maxPageItem;
    private Integer totalPage;
    private Integer totalItem;

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

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
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