package com.zlw.blog.vo;

import java.util.Date;

/**
 * 需要展示的评论信息
 * @author Ranger
 * @create 2019-06-05 10:59
 */
public class CommentInfo {

    private Integer commentId;
    private Integer userId;
    private String headImgUrl;
    private String username;
    private String createTime;
    private String content;

    protected CommentInfo() {
    }

    public CommentInfo(Integer commentId,Integer userId, String headImgUrl, String username, String createTime, String content) {
        this.commentId = commentId;
        this.userId = userId;
        this.headImgUrl = headImgUrl;
        this.username = username;
        this.createTime = createTime;
        this.content = content;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
