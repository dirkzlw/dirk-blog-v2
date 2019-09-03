package com.zlw.blog.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 热门文章类
 * @author Ranger
 * @create 2019-06-06 20:01
 */
@Entity
@Table(name = "t_hotBlog")
public class HotBlog {

    //主键id及生成策略
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotBlogId;

    private Integer blogId;
    private String blogTitle;
    private Integer viewNum;

    protected HotBlog() {
    }

    public HotBlog(Integer blogId, String blogTitle, Integer viewNum) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.viewNum = viewNum;
    }

    public Integer getHotBlogId() {
        return hotBlogId;
    }

    public void setHotBlogId(Integer hotBlogId) {
        this.hotBlogId = hotBlogId;
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

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }
}
