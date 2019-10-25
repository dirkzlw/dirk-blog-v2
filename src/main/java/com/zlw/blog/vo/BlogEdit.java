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
public class BlogEdit {

    private Integer blogId;
    private String blogTitle;
    private String blogText;
    private Integer artType;
    private Integer blogTag;
    private String coverImgUrl;

    protected BlogEdit() {
    }

    public BlogEdit(Integer blogId, String blogTitle,
                    String blogText, Integer artType, Integer blogTag,
                    String coverImgUrl) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogText = blogText;
        this.artType = artType;
        this.blogTag = blogTag;
        this.coverImgUrl = coverImgUrl;
    }

}
