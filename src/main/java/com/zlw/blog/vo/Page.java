package com.zlw.blog.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回的分页信息
 *
 * @author Ranger
 * @create 2019-06-09 20:07
 */
@Getter
@Setter
public class Page {
    private Integer currentPage;
    private Integer totalPage;
    private Integer esize;

    protected Page() {
    }

    public Page(Integer currentPage, Integer totalPage, Integer esize) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.esize = esize;
    }
}
