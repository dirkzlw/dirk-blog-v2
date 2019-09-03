package com.zlw.blog.vo;

/**
 * 用于展示在主页上的博客vo
 * @author Ranger
 * @create 2019-06-04 17:20
 */
public class BlogEdit {

    private Integer blogId;
    private String blogTitle;
    private String blogIntro;
    private String blogText;
    private Integer artType;
    private Integer blogType;
    private String coverImgUrl;

    protected BlogEdit() {
    }

    public BlogEdit(Integer blogId, String blogTitle, String blogIntro,
                    String blogText, Integer artType, Integer blogType,
                    String coverImgUrl) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogIntro = blogIntro;
        this.blogText = blogText;
        this.artType = artType;
        this.blogType = blogType;
        this.coverImgUrl = coverImgUrl;
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

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
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
}
