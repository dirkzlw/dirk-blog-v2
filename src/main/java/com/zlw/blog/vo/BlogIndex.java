package com.zlw.blog.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 用于展示在主页上的博客vo
 * @author Ranger
 * @create 2019-06-04 17:20
 */
@Getter
@Setter
public class BlogIndex {

    private Integer blogId;
    private String coverImgUrl;
    private String blogTitle;
    private String createTime;
    private String author;

    private BlogIndex blogIndex;

    protected BlogIndex() {
    }

    public BlogIndex(Integer blogId, String coverImgUrl, String blogTitle, String createTime, String author) {
        this.blogId = blogId;
        this.coverImgUrl = coverImgUrl;
        this.blogTitle = blogTitle;
        this.createTime = createTime;
        this.author = author;
    }

}
