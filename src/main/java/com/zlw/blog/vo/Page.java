package com.zlw.blog.vo;

/**
 * 返回的分页信息
 *
 * @author Ranger
 * @create 2019-06-09 20:07
 */
public class Page {
    private Integer currentPage;
    private Integer totalPage;

    protected Page() {
    }

    public Page(Integer currentPage, Integer totalPage) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
