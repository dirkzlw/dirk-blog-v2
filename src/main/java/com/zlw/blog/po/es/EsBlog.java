package com.zlw.blog.po.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 通过es的博客
 * @author Ranger
 * @create 2019-04-08 19:35
 */
@Document(indexName = "blog_index",type = "blog")
public class EsBlog {
    /**
     * id对应blogid
     * 根据blog标题、前言和文章类型进行筛选
     */
    @Id
    private Integer blogId;
    private String blogTitle;
    private String blogIntro;
    private String blogType;
    private String author;
    private String createTime;
    private String coverImgUrl;

    protected EsBlog() {
    }

    public EsBlog(Integer blogId, String blogTitle, String blogIntro, String blogType, String author, String createTime, String coverImgUrl) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogIntro = blogIntro;
        this.blogType = blogType;
        this.author = author;
        this.createTime = createTime;
        this.coverImgUrl = coverImgUrl;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
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

    public String getBlogType() {
        return blogType;
    }

    public void setBlogType(String blogType) {
        this.blogType = blogType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    @Override
    public String toString() {
        return "EsBlog{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogIntro='" + blogIntro + '\'' +
                ", blogType='" + blogType + '\'' +
                ", author='" + author + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
