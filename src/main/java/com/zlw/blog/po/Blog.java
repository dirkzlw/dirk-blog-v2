package com.zlw.blog.po;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ranger
 * @create 2019-04-03 21:51
 */
@Entity
@Table(name="t_blog")
public class Blog implements Serializable {
    //主键id及生成策略
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;
    //博客标题
    private String blogTitle;
    //博客正文
    @Column(length = 10000)
    private String blogText;
    //博客创建时间
    private String createTime;
    //文章类型:1--原创 2-转发 3-翻译
    private Integer artType;
    //博客分类:1-前端 2-后端 3-架构 4-Linux 5-数据库 6-编程语言 7-其他
    private Integer blogType;
    //封面的URL
    private String coverImgUrl;
    //作者
    private String author;
    //点赞的数量
    private Integer zanNum;
    //博客访问量
    private Integer viewNum;
    //博客评论  一对多
    @OneToMany(mappedBy = "cblog",cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    //JPA规范
    protected Blog() {
    }
    //有参构造

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

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getArtType() {
        return artType;
    }

    public void setArtType(Integer artType) {
        this.artType = artType;
    }

    public Integer getBlogType() {
        return blogType;
    }

    public void setBlogType(Integer blogType) {
        this.blogType = blogType;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getZanNum() {
        return zanNum;
    }

    public void setZanNum(Integer zanNum) {
        this.zanNum = zanNum;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogText='" + blogText + '\'' +
                ", createTime='" + createTime + '\'' +
                ", artType=" + artType +
                ", blogType=" + blogType +
                ", coverImgUrl='" + coverImgUrl + '\'' +
                ", author='" + author + '\'' +
                ", zanNum=" + zanNum +
                ", viewNum=" + viewNum +
                ", comments=" + comments +
                '}';
    }
}
