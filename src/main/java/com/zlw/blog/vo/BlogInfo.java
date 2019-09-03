package com.zlw.blog.vo;

import java.util.Date;

/**
 * 页面展示的实体类
 * 需要get/set方法，不然无法解析
 * @author Ranger
 * @create 2019-06-04 16:59
 */
public class BlogInfo {
    private Integer blogId;
    //博客标题
    private String blogTitle;
    //前言
    private String blogIntro;
    //博客正文
    private String blogText;
    //博客创建时间
    private String createTime;
    //文章类型:1--原创 2-转发 3-翻译
    private String artType;
    //博客分类:1-前端 2-后端 3-架构 4-Linux 5-数据库 6-编程语言 7-其他
    private String blogType;
    //封面的URL
    private String coverImgUrl;
    //作者
    private String author;
    //评论数量
    private Integer commNum;
    //点赞数量
    private Integer zanNum;
    //访问量
    private Integer viewNum;

    protected BlogInfo() {
    }

    public BlogInfo(Integer blogId, String blogTitle,String blogIntro,
                    String blogText, String createTime,
                    Integer artType, Integer blogType,
                    String coverImgUrl, String author,Integer commNum,
                    Integer zanNum,Integer viewNum) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogIntro = blogIntro;
        this.blogText = blogText;
        this.createTime = createTime;
        switch (artType){
            case 1:
                this.artType = "原创";
                break;
            case 2:
                this.artType = "转发";
                break;
            case 3:
                this.artType = "翻译";
                break;
            default:
                this.artType = "未定义";
                break;
        }
        switch (blogType){
            case 1:
                this.blogType = "前端";
                break;
            case 2:
                this.blogType = "后端";
                break;
            case 3:
                this.blogType = "架构";
                break;
            case 4:
                this.blogType = "Linux";
                break;
            case 5:
                this.blogType = "数据库";
                break;
            case 6:
                this.blogType = "编程语言";
                break;
            case 7:
                this.blogType = "其他";
                break;
            default:
                this.blogType = "未定义";
                break;
        }
        this.coverImgUrl = coverImgUrl;
        this.author = author;
        this.commNum = commNum;
        this.zanNum = zanNum;
        this.viewNum = viewNum;
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

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public String getBlogType() {
        return blogType;
    }

    public void setBlogType(String blogType) {
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

    public Integer getCommNum() {
        return commNum;
    }

    public void setCommNum(Integer commNum) {
        this.commNum = commNum;
    }

    public Integer getZanNum() {
        return zanNum;
    }

    public void setZanNum(Integer zanNum) {
        this.zanNum = zanNum;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }
}
