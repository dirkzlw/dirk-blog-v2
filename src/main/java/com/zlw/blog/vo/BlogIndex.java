package com.zlw.blog.vo;

import java.util.Date;

/**
 * 用于展示在主页上的博客vo
 * @author Ranger
 * @create 2019-06-04 17:20
 */
public class BlogIndex {

    private Integer blogId;
    private String coverImgUrl;
    private String blogTitle;
    private String blogIntro;
    private String createTime;
    private String author;

    private BlogIndex blogIndex;

    protected BlogIndex() {
    }

    public BlogIndex(Integer blogId, String coverImgUrl, String blogTitle,String blogIntro, String createTime, String author) {
        this.blogId = blogId;
        this.coverImgUrl = coverImgUrl;
        this.blogTitle = blogTitle;
        this.blogIntro = blogIntro;
        this.createTime = createTime;
        this.author = author;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogIntro() {
        return blogIntro;
    }

    public void setBlogIntro(String blogIntro) {
        this.blogIntro = blogIntro;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BlogIndex getBlogIndex() {
        return blogIndex;
    }

    public void setBlogIndex(BlogIndex blogIndex) {
        this.blogIndex = blogIndex;
    }
}
