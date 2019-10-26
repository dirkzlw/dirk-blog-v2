package com.zlw.blog.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 页面展示的实体类
 * 需要get/set方法，不然无法解析
 * @author Ranger
 * @create 2019-06-04 16:59
 */
@Getter
@Setter
public class BlogInfo {
    private Integer blogId;
    //博客标题
    private String blogTitle;
    //博客正文
    private String blogText;
    //博客创建时间
    private String createTime;
    //文章类型:1--原创 2-转发 3-翻译
    private String artType;
    //博客分类:1-前端 2-后端 3-架构...
    private String blogTag;
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

    public BlogInfo(Integer blogId, String blogTitle,
                    String blogText, String createTime,
                    Integer artType, String blogTag,
                    String coverImgUrl, String author,Integer commNum,
                    Integer zanNum,Integer viewNum) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
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
        this.blogTag = blogTag;
        this.coverImgUrl = coverImgUrl;
        this.author = author;
        this.commNum = commNum;
        this.zanNum = zanNum;
        this.viewNum = viewNum;
    }

}
