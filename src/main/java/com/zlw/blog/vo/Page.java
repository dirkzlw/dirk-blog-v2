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

    protected Page() {
    }

    public Page(Integer currentPage, Integer totalPage) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

}
