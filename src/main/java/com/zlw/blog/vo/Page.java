package com.zlw.blog.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 返回的分页信息
 *
 * @author Ranger
 * @create 2019-06-09 20:07
 */
@Getter
@Setter
public class Page<T> {
    private List<T> tList;
    private Integer currentPage;
    private Integer totalPage;
    private Integer totalNum;
    private Integer esize;

    protected Page() {
    }

    public Page(List<T> tList, Integer currentPage, Integer totalPage, Integer totalNum, Integer esize) {
        this.tList = tList;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.totalNum = totalNum;
        this.esize = esize;
    }
}
